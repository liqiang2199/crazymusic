package com.framework.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

/**
 * 资源工具 获取资源
 */
public class XResUtil {
    /**
     * 获取attr 颜色
     *
     * @param attr
     * @return
     */
    public static final int getAttrColor(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        int[] attribute = new int[]{attr};
        TypedArray array = context.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color0 = array.getColor(0 /* index */, 0xffffffff /* default size */);
        array.recycle();
        return color0;
    }

    /**获取颜色值
     * @param context
     * @param res
     * @return
     */
    public static final int getColor(Context context, int res) {
        return context.getResources().getColor(res);
    }
}
