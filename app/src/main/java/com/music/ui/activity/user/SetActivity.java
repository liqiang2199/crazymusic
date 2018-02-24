package com.music.ui.activity.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.music.R;
import com.music.common.Constants;
import com.music.ui.activity.BaseActivity;
import com.music.ui.dialog.DialogCommonTip;
import com.music.utils.CacheUtil;
import com.music.utils.UtilsTools;
import com.music.utils.XActionUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.util.LogUtil;

import ch.ielse.view.SwitchView;

/**
 * 设置
 */
public class SetActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener
,DialogCommonTip.DialogOnClickSubmit{

    private SwitchView switchButton;
    private TextView tvVersionUpdate;
    private TextView tvTermsOfTheAgreement;
    private TextView tvClearCache;
    private TextView tvSignOut;
    private TextView tv_account_manage;//账号管理
    private TextView tv_version_update;//版本号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set);
        super.onCreate(savedInstanceState);
        initView();
    }

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    private void initView() {
        switchButton = (SwitchView) findViewById(R.id.switchButton);
        tvVersionUpdate = (TextView) findViewById(R.id.tv_version_update);
        tvTermsOfTheAgreement = (TextView) findViewById(R.id.tv_terms_of_the_agreement);
        tvClearCache = (TextView) findViewById(R.id.tv_clear_cache);
        tvSignOut = (TextView) findViewById(R.id.tv_sign_out);
        tv_account_manage = (TextView) findViewById(R.id.tv_account_manage);
        tv_version_update = (TextView) findViewById(R.id.tv_version_update);

        tv_version_update.setText("v"+getLocalVersionName());

        tvVersionUpdate.setOnClickListener(this);
        tvTermsOfTheAgreement.setOnClickListener(this);
        tvClearCache.setOnClickListener(this);
        tv_account_manage.setOnClickListener(this);
        //
        if (UtilsTools.isStringNull(UtilsTools.getReadCacheUtilData(Constants.TOKEN))){
            tvSignOut.setOnClickListener(null);
            tvSignOut.setVisibility(View.GONE);
        }else{
            tvSignOut.setOnClickListener(this);
            tvSignOut.setVisibility(View.VISIBLE);
        }

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
    }

    @Override
    public void onDialogClickSubmit() {
        Intent intent = new Intent();
        intent.putExtra("isBack", true);
        XActionUtil.action().appStartActivity(intent, XActionUtil.LOGIN);
        //退出登录后清空所有 保存
        CacheUtil.deleteAll();
        finish();
    }

    @Override
    public void onDialogClickCancel() {

    }

    /**
     * 获取本地软件版本号名称
     */
    private   String getLocalVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
