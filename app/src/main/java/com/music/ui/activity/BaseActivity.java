package com.music.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.framework.utils.StatusBarUtil;
import com.framework.view.Toolbar;
import com.music.BaseApp;
import com.music.R;

/**
 * 基本Activity
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Toolbar toolbar;

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.getActivities().add(this);
        super.onCreate(savedInstanceState);
        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            final Drawable upArrow = getResources().getDrawable(R.mipmap.ic_back);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        StatusBarUtil.StatusBarDarkMode(this);
//        StatusBarUtil.setStatusBarColor(this, R.drawable.bg_base_bar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApp.getActivities().remove(this);
    }
}
