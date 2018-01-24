package com.music.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ViewPage
 */
public class HackyViewPager extends ViewPager {
    private boolean isCanScroll = true;
    public HackyViewPager(Context context) {
        super(context);
    }
    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void scrollTo(int x, int y){
        if (isCanScroll){
            super.scrollTo(x, y);
        }
    }

}

