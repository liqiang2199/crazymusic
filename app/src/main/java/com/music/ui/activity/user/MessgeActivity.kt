package com.music.ui.activity.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.framework.view.recyclerView.XRecyclerView
import com.music.R
import com.music.ui.activity.BaseActivity
import com.music.ui.holder.AddBankHolder
import com.music.ui.holder.BankListHolder
import com.music.ui.holder.InformationHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout

@SuppressLint("Registered")
/**
 * Created by Administrator on 2018/1/27.
 * 消息界面
 */
class MessgeActivity :BaseActivity(){
    private var recyclerview: XRecyclerView?= null
    private var refresh_layout: SmartRefreshLayout?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_goods_search)
        super.onCreate(savedInstanceState)
        if (!isActivityNeedLogin) {
            this.finish()
            return
        }
        initView()
    }

    private fun initView(){
        recyclerview = findViewById(R.id.mRecyclerEntityView)
        recyclerview!!.recyclerView.layoutManager = LinearLayoutManager(this@MessgeActivity)
        recyclerview!!.adapter.bindHolder(InformationHolder())
        recyclerview!!.adapter.setData(0, "")
    }
}