package com.music.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

/**
 * Created by Administrator on 2018/1/27.
 * 公用的 弹窗 提示
 */
class DialogCommon :Dialog(){
    constructor(context: Context):super(context)
    constructor(context: Context,themeResId:Int):super(context,themeResId)
    constructor(context: Context,cancelable:Boolean,cancelListener: DialogInterface.OnCancelListener)
            :super(context,cancelable,cancelListener)

}