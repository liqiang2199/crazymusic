package com.music.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.framework.utils.StatusBarUtil;
import com.framework.view.Toolbar;
import com.google.gson.Gson;
import com.music.BaseApp;
import com.music.R;
import com.wega.library.loadingDialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 基本Activity
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Toolbar toolbar;

    protected Context mContext;
    protected Gson gson;
    protected  int width;
    protected  int height;

    protected  LoadingDialog mLoadingDialog;

    @Subscribe
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

    @Subscribe
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        BaseApp.getActivities().remove(this);

    }

    /**
     * 获取屏幕的高度
     */
    public void Width_Height(){

        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

    }

    /**
     * 返回公用的Gson 对象
     * @return
     */
    protected Gson getNewGson(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }
}
