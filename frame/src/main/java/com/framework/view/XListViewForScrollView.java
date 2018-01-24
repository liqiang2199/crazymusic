package com.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 重写ListVeiw
 */
public class XListViewForScrollView extends ListView {
    public XListViewForScrollView(Context context) {
        super(context);
    }

    public XListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XListViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
