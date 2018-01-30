package com.music.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import com.music.R
import com.music.utils.UtilsTools

/**
 * Created by empty cup on 2018/1/30.
 * 支付方式选择
 */
class PayOrderDialog :Dialog{

    private var w:Int = 0
    private var h:Int = 0
    private var image_closed: ImageView?= null



    constructor(context: Context):super(context)
    constructor(context: Context, themeResId:Int):super(context,themeResId)
    constructor(context: Context, cancelable:Boolean, cancelListener: DialogInterface.OnCancelListener)
            :super(context,cancelable,cancelListener)
    constructor(context: Context, themeResId:Int, w:Int, h:Int):super(context, themeResId){
        this.w = w
        this.h = h
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_buy_shop_paystlye)
        setCanceledOnTouchOutside(false)
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
        params.width = w- UtilsTools.dip2px(context,20.0)
//        params.alpha = 0.7f; // 透明度

        window.setGravity(Gravity.CENTER)

        window.attributes = params
    }

    private fun initView(){
        image_closed = findViewById(R.id.image_closed)
        image_closed!!.setOnClickListener{
            cancel()
        }
    }
}