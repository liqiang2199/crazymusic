package com.framework.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 对话框类
 */
public class XDialogUtil {
    private static Dialog dialog;

    /**
     * 信息提示
     *
     * @param context
     * @param title
     * @param msg
     * @return
     */
    public final static Dialog showDialogMsg(final Context context, String title, String msg) {
        //提示对话框
        if (dialog == null || dialog.isShowing()) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = builder.setTitle(title).setMessage(msg).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
        return dialog;
    }
}
