package com.music.ui.activity.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.music.R;
import com.music.api.API;
import com.music.ui.activity.BaseActivity;
import com.music.utils.CountDownHelper;
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
 * 创建时间：2017/9/13.
 * 类名：重置时间
 */
public class ResetPwdActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getName();

    private EditText edtMobile;
    private EditText edtVerificationCode;
    private TextView tvGetVerificationCode;
    private EditText edtPassword;
    private EditText edtNewPassword;
    private Button btnRegister;

    private LoadingDialog mLoadingDialog;

    private void findViews() {
        edtMobile = (EditText) findViewById(R.id.edt_mobile);
        edtVerificationCode = (EditText) findViewById(R.id.edt_verification_code);
        tvGetVerificationCode = (TextView) findViewById(R.id.tv_get_verification_code);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtNewPassword = (EditText) findViewById(R.id.edt_new_password);
        btnRegister = (Button) findViewById(R.id.btn_register);

        tvGetVerificationCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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
                sendVerificationCode(phoneNumber);
                CountDownHelper helper = new CountDownHelper(tvGetVerificationCode, getString(R.string.send_verification_code),
                        getString(R.string.resend_verification_code), 60, 1);
                helper.setOnFinishListener(new CountDownHelper.OnFinishListener() {

                    @Override
                    public void finish() {

                    }
                });
                helper.start();
                break;
            case R.id.btn_register:
                mLoadingDialog = new LoadingDialog(this);
                resetPassword();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reset_pwd);
        super.onCreate(savedInstanceState);
        findViews();
    }

    private void sendVerificationCode(String phoneNumber) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("phone", phoneNumber);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_FINDPWDCODE)
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
                        Log.e(TAG, stringResponse.body());
                        try {
                            Gson gson = new Gson();

                        } catch (Exception e) {
                            onError(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        UIHelper.showToast(mContext, getResources().getString(R.string.system_error));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    private void resetPassword() {
        final String phoneNumber = edtMobile.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            UIHelper.showToast(mContext, getString(R.string.tip_phone_number_can_not_be_empty));
            return;
        }
        String verificationCode = edtVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(verificationCode)) {
            UIHelper.showToast(mContext, getString(R.string.tip_verification_code_can_not_be_empty));
            return;
        }
        final String loginPassword = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(loginPassword)) {
            UIHelper.showToast(mContext, getString(R.string.tip_new_login_password_can_not_be_empty));
            return;
        }
        String loginNewPassword = edtNewPassword.getText().toString().trim();
        if (TextUtils.isEmpty(loginNewPassword)) {
            UIHelper.showToast(mContext, getString(R.string.tip_new_login_password_again_can_not_be_empty));
            return;
        }
        if (TextUtils.equals(loginPassword, loginNewPassword)) {
            UIHelper.showToast(mContext, getString(R.string.enter_the_password_twice_inconsistent));
            return;
        }
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("phone", phoneNumber);
        params.put("code", verificationCode);
        params.put("password", loginPassword);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<String>post(API.USER_UPDATEPWDBYCODE)
                .tag(this)
                .upJson(jsonObject)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        mLoadingDialog.loading(getString(R.string.request));
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
                        Log.e(TAG, stringResponse.body());
                        try {
                            Gson gson = new Gson();
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
