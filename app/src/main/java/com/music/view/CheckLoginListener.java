package com.music.view;

import android.os.Bundle;
import android.os.Parcelable;

import com.music.ui.entity.UserInfoEntity;

/**
 * 判断登录回调
 */
public interface CheckLoginListener extends Parcelable {

    void nLogin(UserInfoEntity customer, Bundle bundle);
}