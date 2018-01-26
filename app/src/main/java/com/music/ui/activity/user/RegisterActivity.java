package com.music.ui.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.framework.utils.XToastUtil;
import com.google.gson.Gson;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.music.R;
import com.music.api.API;
import com.music.common.Constants;
import com.music.model.RegisterBeen;
import com.music.model.ResponseBeen;
import com.music.model.busbeen.LoginFinishBus;
import com.music.ui.activity.BaseActivity;
import com.music.utils.CacheUtil;
import com.music.utils.CountDownHelper;
import com.music.utils.UIHelper;
import com.music.utils.UtilsTools;
import com.wega.library.loadingDialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建时间： 2017/9/13.
 * 类名：注册
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();

    private EditText edtMobile;
    private EditText edtVerificationCode;
    private TextView tvGetVerificationCode;
    private EditText edtPassword;
    private TextView tvAgreement;
    private Button btnRegister;

    private LoadingDialog mLoadingDialog;

    private void findViews() {
        edtMobile = (EditText) findViewById(R.id.edt_mobile);
        edtVerificationCode = (EditText) findViewById(R.id.edt_verification_code);
        tvGetVerificationCode = (TextView) findViewById(R.id.tv_get_verification_code);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        tvAgreement = (TextView) findViewById(R.id.tv_agreement);
        btnRegister = (Button) findViewById(R.id.btn_register);

        tvGetVerificationCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        Link link = new Link(getString(R.string.user_agreement_content))
                .setTextColor(Color.parseColor("#FCC671"))
                .setTextColorOfHighlightedLink(Color.parseColor("#FCC671"))
//                .setHighlightAlpha(.0f)
                .setUnderlined(false)
                .setBold(false)
                .setOnLongClickListener(new Link.OnLongClickListener() {
                    @Override
                    public void onLongClick(String clickedText) {
                    }
                })
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                    }
                });
        LinkBuilder.on(tvAgreement)
                .addLink(link)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
        findViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification_code:
                String phoneNumber = edtMobile.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNumber)) {
                    UIHelper.showToast(mContext, getString(R.string.tip_phone_number_can_not_be_empty));
                    return;
                }
                if (!UtilsTools.isPhoneNum(phoneNumber)){
                    UIHelper.showToast(mContext, getString(R.string.tip_phone_is_sure));
                    return;
                }
                mLoadingDialog = new LoadingDialog(this);
                sendVerificationCode(phoneNumber);

                break;
            case R.id.btn_register:
                mLoadingDialog = new LoadingDialog(this);
                register();
                break;
        }
    }

    /**发送短信
     *
     * @param phoneNumber
     */
    private void sendVerificationCode(String phoneNumber) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("phone", phoneNumber);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_REGISTCODE)
                .tag(this)
                .upJson(jsonObject)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
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
                        Log.e(TAG, msg);
                        try {
                            ResponseBeen responseBeen = getNewGson().fromJson(msg,ResponseBeen.class);
                            String code = responseBeen.getCode();
                            if (!TextUtils.isEmpty(code)&&code.equals("0")){
                                XToastUtil.showToast(RegisterActivity.this,R.string.Toast_send_sms);
                                // 只有成功 才开始倒计时
                                CountDownHelper helper = new CountDownHelper(tvGetVerificationCode, getString(R.string.send_verification_code),
                                        getString(R.string.resend_verification_code), 60, 1);
                                helper.setOnFinishListener(new CountDownHelper.OnFinishListener() {

                                    @Override
                                    public void finish() {

                                    }
                                });
                                helper.start();
                            }else{
                                XToastUtil.showToast(RegisterActivity.this,responseBeen.getMsg());
                            }
                            mLoadingDialog.cancel();
                        } catch (Exception e) {
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

    /**
     * 注册
     */
    private void register() {
        final String phoneNumber = edtMobile.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            UIHelper.showToast(mContext, getString(R.string.tip_phone_number_can_not_be_empty));
            return;
        }
        String verificationCode = edtVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            UIHelper.showToast(mContext, getString(R.string.tip_verification_code_can_not_be_empty));
            return;
        }
        final String loginPassword = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            UIHelper.showToast(mContext, getString(R.string.tip_login_password_can_not_be_empty));
            return;
        }
        if (!UtilsTools.isPhoneNum(phoneNumber)){
            UIHelper.showToast(mContext, getString(R.string.tip_phone_is_sure));
            return;
        }
        if (!UtilsTools.isSixLength(verificationCode)){
            UIHelper.showToast(mContext, getString(R.string.tip_verification_six));
            return;
        }
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("phone", phoneNumber);
        params.put("code", verificationCode);
        params.put("password", loginPassword);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_VALIDCODEANDCREATE)
                .tag(this)
                .upJson(jsonObject)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mLoadingDialog.loading(getString(R.string.register));
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
                        Log.e(TAG, msg);
                        try {
                            //注册成功
                            ResponseBeen responseBeen = getNewGson().fromJson(msg,ResponseBeen.class);
                            String code = responseBeen.getCode();
                            if (!TextUtils.isEmpty(code)&&code.equals("0")){
                                //成功
                                String data = responseBeen.getData();
                                if (!TextUtils.isEmpty(data)){
                                    RegisterBeen registerBeen = getNewGson().fromJson(data,RegisterBeen.class);

                                    CacheUtil.put(Constants.PHONE, phoneNumber);
                                    CacheUtil.put(Constants.TOKEN, registerBeen.getToken());
                                    CacheUtil.put(Constants.HEADIMAGE,registerBeen.getHead_img());
                                    CacheUtil.put(Constants.NINCKNAME, registerBeen.getNick_name());
                                }
                                //关闭 登录界面
                                EventBus.getDefault().post(new LoginFinishBus());
                                finish();
                            }

                            XToastUtil.showToast(RegisterActivity.this,responseBeen.getMsg());
                        } catch (Exception e) {
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
}
