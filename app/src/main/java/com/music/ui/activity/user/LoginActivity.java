package com.music.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.utils.StatusBarUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.music.R;
import com.music.api.API;
import com.music.common.Constants;
import com.music.common.StatusCode;
import com.music.model.LoginRes;
import com.music.model.busbeen.LoginFinishBus;
import com.music.ui.activity.BaseActivity;
import com.music.ui.activity.MainActivity;
import com.music.utils.CacheUtil;
import com.music.utils.UIHelper;
import com.music.utils.UtilsTools;
import com.wega.library.loadingDialog.LoadingDialog;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 类说明： 登录页面
 * 类描述：
 * 创建时间： 2017/9/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();

    private EditText edtPhoneNumber;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvForgetPassword;
    private TextView tvRegister;
    private ImageView ivQq;
    private ImageView ivWechat;

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
//        StatusBarUtil.StatusBarDarkMode(this);
//        StatusBarUtil.setStatusBarColor(this, R.color.white);
        initView();
    }

    private void initView() {
        edtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        ivQq = (ImageView) findViewById(R.id.iv_qq);
        ivWechat = (ImageView) findViewById(R.id.iv_wechat);

        btnLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        ivQq.setOnClickListener(this);
        ivWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_forget_password:
                //忘记密码
                startActivity(new Intent(this, ResetPwdActivity.class));
                break;
            case R.id.btn_login:
                //登录
                mLoadingDialog = new LoadingDialog(this);
                login();
                break;
            case R.id.tv_register:
                //注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.iv_qq:
                //qq
                qqLogin();
                break;
            case R.id.iv_wechat:
                //Wechat
                wechatLogin();
                break;
        }
    }

    private void login() {
        final String phoneNumber = edtPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            UIHelper.showToast(mContext, getString(R.string.tip_phone_number_can_not_be_empty));
            return;
        }
        String loginPassword = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            UIHelper.showToast(mContext, getString(R.string.tip_login_password_can_not_be_empty));
            return;
        }
        if (!UtilsTools.isPhoneNum(phoneNumber)){
            UIHelper.showToast(mContext, getString(R.string.tip_phone_is_sure));
            return;
        }
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("phone", phoneNumber);
        params.put("password", loginPassword);
        params.put("login_type","0");
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_LOGIN)
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
                        Log.e(TAG,msg);
                        try {
                            LoginRes loginRes = getNewGson().fromJson(msg, LoginRes.class);
                            if (StatusCode.SUCCESS.getType() == loginRes.getCode()) {
                                //保存
                                CacheUtil.put(Constants.PHONE, phoneNumber);
                                CacheUtil.put(Constants.TOKEN, loginRes.getData().getToken());
                                CacheUtil.put(Constants.NINCKNAME, loginRes.getData().getNick_name());
                                startActivity(new Intent(mContext, MainActivity.class));
                                LoginActivity.this.finish();
                            } else {
                                UIHelper.showToast(mContext, loginRes.getMsg());
                            }
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

    private void qqLogin(){

    }

    private void wechatLogin(){

    }
    @Subscribe
    public void onEventMainThread(LoginFinishBus event){
        if (event != null){
            finish();
        }
    }
}
