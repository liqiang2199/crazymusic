package com.music.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.music.R;
import com.music.utils.UtilsTools;

/**
 * Created by Administrator on 2018/1/27.
 * 公用
 */

public class DialogCommonTip extends Dialog implements View.OnClickListener{
    private TextView dialog_Title;
    private TextView dialog_cancel;
    private TextView dialog_submit;


    private String title;
    private String cancel;
    private String submit;
    private DialogOnClickSubmit dialogOnClickSubmit;

    private int w;
    private int h;
    public DialogCommonTip(@NonNull Context context) {
        super(context);
    }

    public DialogCommonTip(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogCommonTip(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public DialogCommonTip(Context context,int themeResId,int w,int h){
        super(context,themeResId);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(false);
        initView();

        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams  params = window.getAttributes();
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        /**
         Dialog的width和height默认值为WRAP_CONTENT，正是因为如此，当屏幕中有足够的空间时，Dialog是不会被压缩的
         但是设置width和height为MATCH_PARENT的代价是无法设置gravity的值，这就无法调整Dialog中内容的位置，
         Dialog的内容会显示在屏幕左上角位置不过可以通过Padding来调节Dialog内容的位置。
         **/
//        params.x = 0;
//        params.y =  -UtilsTools.dip2px(getContext(),200);
        params.width = w-UtilsTools.dip2px(getContext(),20);
//        params.alpha = 0.7f; // 透明度

        window.setGravity(Gravity.CENTER);

        window.setAttributes(params);
    }

    private void initView() {
        dialog_Title = findViewById(R.id.dialog_Title);
        dialog_cancel = findViewById(R.id.dialog_cancel);
        dialog_submit = findViewById(R.id.dialog_submit);
        dialog_cancel.setOnClickListener(this);
        dialog_submit.setOnClickListener(this);

        dialog_Title.setText(title);
        dialog_cancel.setText(cancel);
        dialog_submit.setText(submit);
    }

    public DialogCommonTip setDialogTitle(String text){
        title = text;
        return this;
    }
    public DialogCommonTip setDialogCancel(String text){
        cancel = text;
        return this;
    }
    public DialogCommonTip setDialogSubmit(String text){
        submit = text;
        return this;
    }

    public DialogCommonTip setDialogOnClick(DialogOnClickSubmit dialogOnClickSubmit){
        this.dialogOnClickSubmit = dialogOnClickSubmit;
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_cancel:
                if (dialogOnClickSubmit != null){
                    dialogOnClickSubmit.onDialogClickCancel();
                }
                cancel();
                break;
            case R.id.dialog_submit:
                if (dialogOnClickSubmit != null){
                    dialogOnClickSubmit.onDialogClickSubmit();
                }
                cancel();
                break;
        }
    }


    public interface  DialogOnClickSubmit{
        void onDialogClickSubmit();
        void onDialogClickCancel();
    }
}

