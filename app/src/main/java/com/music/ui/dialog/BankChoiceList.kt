package com.music.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import com.framework.view.recyclerView.XRecyclerView
import com.music.R
import com.music.ui.holder.DialogChoiceAddBankHolder
import com.music.ui.holder.DialogChoiceBankHolder

/**
 * Created by empty cup on 2018/1/29.
 * 银行卡选择列表
 */
class BankChoiceList :Dialog{
    private var w:Int = 0
    private var h:Int = 0

    private var mRecyclerEntityView:XRecyclerView ?=null


    constructor(context: Context):super(context)
    constructor(context: Context, themeResId:Int):super(context,themeResId)
    constructor(context: Context, cancelable:Boolean, cancelListener: DialogInterface.OnCancelListener)
            :super(context,cancelable,cancelListener)
    constructor(context: Context,themeResId:Int,w:Int,h:Int):super(context, themeResId){
        this.w = w
        this.h = h
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_choice_bank)
        setCanceledOnTouchOutside(true)
        initView()

        val window = window!!
        val params = window.attributes
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        /**
         *
        Dialog的width和height默认值为WRAP_CONTENT，正是因为如此，当屏幕中有足够的空间时，Dialog是不会被压缩的
        但是设置width和height为MATCH_PARENT的代价是无法设置gravity的值，这就无法调整Dialog中内容的位置，
        Dialog的内容会显示在屏幕左上角位置不过可以通过Padding来调节Dialog内容的位置。
         **/
//        params.x = 0;
//        params.y =  -UtilsTools.dip2px(getContext(),200);
        params.width = w
        params.height = h/3
//        params.alpha = 0.7f; // 透明度

        window.setGravity(Gravity.BOTTOM)

        window.attributes = params

    }

    private fun initView(){
        mRecyclerEntityView = findViewById(R.id.mRecyclerEntityView)

        mRecyclerEntityView!!.recyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerEntityView!!.adapter.bindHolder(DialogChoiceBankHolder())
        mRecyclerEntityView!!.adapter.bindHolder(DialogChoiceAddBankHolder())
        mRecyclerEntityView!!.adapter.setData(0, "dd")
        mRecyclerEntityView!!.adapter.setData(1, "dd")
    }
}