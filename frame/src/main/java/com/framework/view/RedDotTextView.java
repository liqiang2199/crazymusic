package com.framework.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framework.R;

/**
 * 数字红点
 */
public class RedDotTextView extends TextView {
    boolean onlyDot;

    public RedDotTextView(Context context) {
        super(context);
    }

    public RedDotTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }


    public RedDotTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RedDotTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
//        TypedArray a = getContext().obtainStyledAttributes(attrs,
//                R.styleable.RedDotTextView);
//        onlyDot = a.getBoolean(R.styleable.RedDotTextView_onlyDot, false);
//        setOnlyDot(onlyDot);
    }

    public void setOnlyDot(boolean onlyDot) {
        this.onlyDot = onlyDot;
        if (onlyDot) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = d2px(8);
            layoutParams.width=layoutParams.height;
            setBackgroundResource(R.drawable.bg_oval_red);
            setMinHeight(layoutParams.height);
            setMinWidth(layoutParams.height);
            setLayoutParams(layoutParams);
        }
    }

    private int d2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(text) || TextUtils.equals(text, "0")) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
        }
        if (!onlyDot) {
            super.setText(text, type);
        }else {
            super.setText("",type);
        }
    }
}
