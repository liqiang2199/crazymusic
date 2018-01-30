package com.music.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.music.R
import com.music.utils.UtilsTools
import java.util.*


/**
 * Created by empty cup on 2018/1/30.
 * 发表评论的弹窗
 */
class CommentsPublishDialog :Dialog {
    private var w:Int = 0
    private var h:Int = 0

    private var edit_comments:EditText ?= null
    private var text_publish_comments:TextView ?= null
    private var text_comments:TextView ?= null

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
        setContentView(R.layout.item_community_detail_edit)
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
//        params.alpha = 0.7f; // 透明度

        window.setGravity(Gravity.BOTTOM)

        window.attributes = params
    }

    private fun initView(){
        edit_comments = findViewById(R.id.edit_comments)
        text_publish_comments = findViewById(R.id.text_publish_comments)
        text_comments = findViewById(R.id.text_comments)

        text_comments!!.visibility = View.GONE
        edit_comments!!.visibility = View.VISIBLE

        text_publish_comments!!.setOnClickListener{
            cancel()
        }

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val inputManager = edit_comments!!.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(edit_comments, 0)
            }
        }, 500)
    }

    override fun dismiss() {
        super.dismiss()
        closeKeyboard()
    }

    private fun closeKeyboard() {
        val view = window!!.peekDecorView()
        if (view != null) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}