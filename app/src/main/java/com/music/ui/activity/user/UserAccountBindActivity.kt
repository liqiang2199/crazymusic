package com.music.ui.activity.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.music.R
import com.music.ui.activity.BaseActivity

@SuppressLint("Registered")
/**
 * Created by Administrator on 2018/1/28.
 * 账号管理
 */
class UserAccountBindActivity :BaseActivity(),View.OnClickListener {

    private var ralt_bind_qq:RelativeLayout ?= null
    private var ralt_bind_weiChat:RelativeLayout ?= null
    private var ralt_bind_phone:RelativeLayout ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_set_account_manage)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        ralt_bind_qq = findViewById(R.id.ralt_bind_qq)
        ralt_bind_weiChat = findViewById(R.id.ralt_bind_weiChat)
        ralt_bind_phone = findViewById(R.id.ralt_bind_phone)

        ralt_bind_qq!!.setOnClickListener(this)
        ralt_bind_weiChat!!.setOnClickListener(this)
        ralt_bind_phone!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        if (v!!.id == R.id.ralt_bind_qq){

        }
        if (v.id == R.id.ralt_bind_weiChat){

        }
        if (v.id == R.id.ralt_bind_phone){
            //手机号绑定
            startActivity(Intent(mContext,BindPhoneActivity::class.java))
        }
    }
}