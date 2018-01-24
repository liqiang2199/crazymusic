package com.music.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;


import com.framework.utils.XToastUtil;
import com.music.BaseApp;

import java.util.HashMap;
import java.util.Map;

/**
 * 安卓页面路由控制启动器
 */
public class XActionUtil {

    public static final String LOGIN = "login";

    private Map<String, Class<? extends Activity>> map = new HashMap<>();
    private String toast;
    private static XActionUtil xActionUtil;

    public static XActionUtil action() {
        if (xActionUtil == null) {
            xActionUtil = new XActionUtil();
        }
        return xActionUtil;
    }

    public void add(String action, Class<? extends Activity> aciticyClass) {
        map.put(action, aciticyClass);
    }

    public Class<? extends Activity> getMap(String action) {
        return map.get(action);
    }

    public void startActivity(Context context, @Nullable Intent intent, String action) {
        if (intent == null) {
            intent = new Intent();
        }
        Class classz = map.get(action);
        if (classz == null) {
            XToastUtil.showToast(context, toast);
            return;
        }
        intent.setClass(context, classz);
        context.startActivity(intent);
    }

    public void startActivity(Context context, String action) {
        Class classz = map.get(action);
        if (classz == null) {
            XToastUtil.showToast(context, toast);
            return;
        }
        Intent intent = new Intent(context, classz);
        context.startActivity(intent);
    }

    /**
     * 通过Application 启动
     * @param action
     */
    public void appStartActivity(String action) {
        Class classz = map.get(action);
        if (classz == null) {
            XToastUtil.showToast(BaseApp.instance(), toast);
            return;
        }
        Intent intent = new Intent(BaseApp.instance(), classz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        BaseApp.instance().startActivity(intent);
    }

    /**
     * 通过Application 启动
     * @param intent
     * @param action
     */
    public void appStartActivity(Intent intent, String action) {
        Class classz = map.get(action);
        if (classz == null) {
            XToastUtil.showToast(BaseApp.instance(), toast);
            return;
        }
        intent.setClass(BaseApp.instance(), classz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        BaseApp.instance().startActivity(intent);
    }

    public void setToast(String toast) {
        this.toast = toast;
    }
}