package com.music.ui.activity.user

import android.annotation.SuppressLint
import android.os.Bundle
import com.music.R
import com.music.ui.activity.BaseActivity

@SuppressLint("Registered")
/**
 * Created by Administrator on 2018/1/28.
 * 绑定手机号
 */
class BindPhoneActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_set_account_bind_phone)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){

    }
}