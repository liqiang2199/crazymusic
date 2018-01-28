package com.music;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.bumptech.glide.request.target.ViewTarget;
import com.framework.XApplication;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.music.ui.activity.user.LoginActivity;
import com.music.utils.CacheUtil;
import com.music.utils.XActionUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import okhttp3.OkHttpClient;

/**
 * Application初始类
 */
public class BaseApp extends XApplication {

    private static BaseApp instance;
    private static List<Activity> activities = new ArrayList<>();

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorFCFBF9, android.R.color.black);
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //xutil初始化
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        //登录路由
        XActionUtil.action().add(XActionUtil.LOGIN, LoginActivity.class);

        //Glide已经默认为ImageView设置的Tag
        ViewTarget.setTagId(R.id.glide_tag);

        //拍照7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        CacheUtil.init(this);
        initOkGo();
        //检测程序崩溃类和重新启动APP
        CustomActivityOnCrash.install(this);
    }

    public static List<Activity> getActivities() {
        return activities;
    }

    public static BaseApp instance() {
        return instance;
    }

    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers);                     //全局公共头
    }
}
