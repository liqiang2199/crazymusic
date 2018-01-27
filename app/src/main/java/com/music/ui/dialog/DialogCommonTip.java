package com.music.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/1/27.
 */

public class DialogCommonTip extends Dialog {
    public DialogCommonTip(@NonNull Context context) {
        super(context);
    }

    public DialogCommonTip(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogCommonTip(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
