package com.framework.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.framework.R;

/**
 * 自定义对话框
 *
 */
public class CustomDialogUtil {

    /**
     * 加载进度框
     * @param context
     * @param msg     提示信息
     * @return
     */
    public static Dialog showLoadDialog(Context context, String msg) {
        Dialog dialog = new Dialog(context, R.style.DialogThemeNoTitle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(view);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView tv_msg = (TextView) view.findViewById(R.id.message);
        if (!TextUtils.isEmpty(msg)){
            tv_msg.setText(msg);
        }
        return dialog;
    }
}