package com.music.ui.activity.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.music.R;
import com.music.api.API;
import com.music.common.Constants;
import com.music.common.StatusCode;
import com.music.model.LoginRes;
import com.music.model.jsonbeen.GetAddressDefaulBeen;
import com.music.ui.activity.BaseActivity;
import com.music.ui.activity.MainActivity;
import com.music.ui.ihanlder.IChoiceAddressHanlder;
import com.music.utils.CacheUtil;
import com.music.utils.UIHelper;
import com.wega.library.loadingDialog.LoadingDialog;

import org.json.JSONObject;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 地址管理
 */
public class AddressManagementActivity extends BaseActivity implements IChoiceAddressHanlder{

    private Button submit;
    private EditText et_receiptName;
    private EditText et_mobile;
    private EditText et_street;
    private TextView et_address;
    private LinearLayout btn_address;

    private String province;
    private String city;
    private String district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_address);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        submit = findViewById(R.id.submit);
        et_receiptName = findViewById(R.id.et_receiptName);
        et_mobile = findViewById(R.id.et_mobile);
        et_street = findViewById(R.id.et_street);
        et_address = findViewById(R.id.et_address);
        btn_address = findViewById(R.id.btn_address);

        initJsonData();

        onBtnClick(submit);
        onBtnClick(btn_address);
    }

    public void onBtnClick(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_address:
                        //选择地址
                        ShowPickerView(AddressManagementActivity.this);
                        break;
                    case R.id.submit:
                        initData();
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGetData();
    }

    private void initData(){
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.show();
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("province", province);
        params.put("province_code", "51");
        params.put("city",city);
        params.put("city_code","07");
        params.put("district",district);
        params.put("district_code","23");
        params.put("region_id","");
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_UPDATESAVEUSERREGION)
                .tag(this)
                .upJson(jsonObject)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mLoadingDialog.loading(getString(R.string.login));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Response<String> stringResponse) {
                        String msg = stringResponse.body();
                        Log.e("Address",msg);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        UIHelper.showToast(mContext, getResources().getString(R.string.system_error));
                        mLoadingDialog.cancel();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                        mLoadingDialog.cancel();
                    }
                });
    }

    // 获取 地址
    private void initGetData(){
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.show();
        TreeMap<String, String> params = new TreeMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_GETUSERDEFAULADDR)
                .tag(this)
                .upJson(jsonObject)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mLoadingDialog.loading(getString(R.string.login));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(@NonNull Response<String> stringResponse) {
                        String msg = stringResponse.body();
                        Log.e("Address",msg);
                        try {
                            GetAddressDefaulBeen getAddressDefaulBeen = getNewGson().fromJson(msg,GetAddressDefaulBeen.class);
                            et_receiptName.setText(getAddressDefaulBeen.getData().getName());
                            et_mobile.setText(getAddressDefaulBeen.getData().getPhone());
                            et_street.setText(getAddressDefaulBeen.getData().getAddr_detail());
                            GetAddressDefaulBeen.AddressDefaulBeen addressDefaulBeen = getAddressDefaulBeen.getData();
                            et_address.setText(addressDefaulBeen.getProvince()+
                                    addressDefaulBeen.getCity()+addressDefaulBeen.getDistrict());
                        }catch (Exception e){
                            onError(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        UIHelper.showToast(mContext, getResources().getString(R.string.system_error));
                        mLoadingDialog.cancel();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                        mLoadingDialog.cancel();
                    }
                });
    }

    @Override
    public void choicePostion(int pos1, int pos2, int pos3) {
//        String s = options1Items.get(pos1).getPickerViewText() +
//                options2Items.get(pos1).get(pos2) +
//                options3Items.get(pos1).get(pos2).get(pos3);
        province = options1Items.get(pos1).getPickerViewText();
        city = options2Items.get(pos1).get(pos2);
        district = options2Items.get(pos1).get(pos2);
        et_address.setText(province+city+district);
    }
}
