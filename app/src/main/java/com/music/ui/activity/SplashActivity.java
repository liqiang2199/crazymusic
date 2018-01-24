package com.music.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.framework.utils.XAppUtil;
import com.music.ui.activity.guide.GuideActivity;
import com.music.ui.activity.user.LoginActivity;
import com.music.utils.UserInfoUtils;

/**
 * 启动器
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (XAppUtil.isFirstStart(SplashActivity.this, getPackageName())) {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));//跳转首次引导
                } /*else if (UserInfoUtils.getUserInfo() != null && TextUtils.isEmpty(UserInfoUtils.getUserInfo().getId())) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));//跳转到登录
                }*/ else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));//跳转到首页
                }
                finish();
            }
        }, 500);
    }
}