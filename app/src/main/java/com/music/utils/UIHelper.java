package com.music.utils;

import android.content.Context;
import android.widget.Toast;

public class UIHelper {
    public static Toast mToast = null;

    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            // mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void destroyToast() {
        if (mToast == null) {
            return;
        }
        mToast = null;
    }
}
