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
import com.music.ui.dialog.DialogCommonTip;
import com.music.utils.XActionUtil;

/**
 * 设置
 */
public class SetActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener
,DialogCommonTip.DialogOnClickSubmit{

    private SwitchButton switchButton;
    private TextView tvVersionUpdate;
    private TextView tvTermsOfTheAgreement;
    private TextView tvClearCache;
    private TextView tvSignOut;
    private TextView tv_account_manage;//账号管理

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
        tv_account_manage = (TextView) findViewById(R.id.tv_account_manage);

        switchButton.setOnCheckedChangeListener(this);
        tvVersionUpdate.setOnClickListener(this);
        tvTermsOfTheAgreement.setOnClickListener(this);
        tvClearCache.setOnClickListener(this);
        tvSignOut.setOnClickListener(this);
        tv_account_manage.setOnClickListener(this);

        Width_Height();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_sign_out:
                //退出登录
                DialogCommonTip dialogCommonTip = new DialogCommonTip(mContext,R.style.ScreenDialogStyle,width,height);
                dialogCommonTip.setDialogTitle("确定退出登录？").setDialogCancel("取消")
                        .setDialogSubmit("确定").setDialogOnClick(this).show();
                break;
            case R.id.tv_account_manage:
                //账号管理
                startActivity(new Intent(mContext,UserAccountBindActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switchButton.setChecked(isChecked);
    }

    @Override
    public void onDialogClickSubmit() {
        Intent intent = new Intent();
        intent.putExtra("isBack", true);
        XActionUtil.action().appStartActivity(intent, XActionUtil.LOGIN);
        finish();
    }

    @Override
    public void onDialogClickCancel() {

    }
}
