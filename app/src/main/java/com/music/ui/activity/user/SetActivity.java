package com.music.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.utils.XActionUtil;

/**
 * 设置
 */
public class SetActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private SwitchButton switchButton;
    private TextView tvVersionUpdate;
    private TextView tvTermsOfTheAgreement;
    private TextView tvClearCache;
    private TextView tvSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        switchButton = (SwitchButton) findViewById(R.id.switchButton);
        tvVersionUpdate = (TextView) findViewById(R.id.tv_version_update);
        tvTermsOfTheAgreement = (TextView) findViewById(R.id.tv_terms_of_the_agreement);
        tvClearCache = (TextView) findViewById(R.id.tv_clear_cache);
        tvSignOut = (TextView) findViewById(R.id.tv_sign_out);

        switchButton.setOnCheckedChangeListener(this);
        tvVersionUpdate.setOnClickListener(this);
        tvTermsOfTheAgreement.setOnClickListener(this);
        tvClearCache.setOnClickListener(this);
        tvSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_sign_out:
                //退出登录
                Intent intent = new Intent();
                intent.putExtra("isBack", true);
                XActionUtil.action().appStartActivity(intent, XActionUtil.LOGIN);
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switchButton.setChecked(isChecked);
    }
}
