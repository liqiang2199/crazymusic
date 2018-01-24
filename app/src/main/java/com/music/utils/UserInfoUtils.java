package com.music.utils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.ui.entity.UserInfoEntity;
import com.music.view.CheckLoginListener;

import java.lang.reflect.Type;

/**
 * 用户信息本地操作类
 */
public class UserInfoUtils {

    //网络返回--标识
    public static final String INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK = "INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK";

    //网络返回--标识
    public static final String INTENT_ONLOGIN_CALLBACK_PARAMS = "INTENT_ONLOGIN_CALLBACK_PARAMS";

    /**
     * 判断是否登录并回调
     *
     * @param
     * @param loginClass
     * @param listener
     * @param bundle
     */
    public static void checkLogin(Context context, Class<?> loginClass, CheckLoginListener listener, Bundle bundle) {
        if (!isLogin()) {
            Intent intent = new Intent(context, loginClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK, listener);
            intent.putExtra(INTENT_ONLOGIN_CALLBACK_PARAMS, bundle);
            context.startActivity(intent);
        } else {
            listener.nLogin(getUserInfo(), bundle);
        }
    }

    /**
     * 缓存是否登录
     *
     * @param isLogin
     */
    public static void cacheLogin(boolean isLogin) {
        XSharedPreferCache.put(ConstHost.REMEMBER_LOGIN, isLogin);
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return XSharedPreferCache.get(ConstHost.REMEMBER_LOGIN, false);
    }

    /**
     * 清除用户信息缓存
     */
    public static void clearUserInfo() {
        XSharedPreferCache.put(ConstHost.USER_INFO, "");
    }


    /**
     * 缓存用户信息
     *
     * @param userAllInfo
     */
    public static void cacheUserInfo(UserInfoEntity userAllInfo) {
        Gson gson = new Gson();
        String userInfoStr = gson.toJson(userAllInfo);
        XSharedPreferCache.put(ConstHost.USER_INFO, userInfoStr);
    }

    /**
     * 获取本地用户信息
     *
     * @return
     */
    public static UserInfoEntity getUserInfo() {
        UserInfoEntity resultData = new UserInfoEntity();
        String userInfoStr = XSharedPreferCache.get(ConstHost.USER_INFO, "");
        if (!TextUtils.isEmpty(userInfoStr)) {
            Gson gson = new Gson();
            Type type = new TypeToken<UserInfoEntity>() {
            }.getType();
            resultData = gson.fromJson(userInfoStr, type);
        }
        return resultData;

    }
}
