package com.music.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.music.ui.entity.shop.GoodsInfoEntity;


public class WrapRadioButton extends ViewGroup implements CompoundButton.OnCheckedChangeListener {
    private final static String TAG = "MyViewGroup";

    private final static int VIEW_MARGIN = 0;
    OnCheckedChanged onCheckedChanged;

    public WrapRadioButton(Context context) {
        super(context);
    }

    public WrapRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WrapRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "widthMeasureSpec = " + widthMeasureSpec + " heightMeasureSpec" + heightMeasureSpec);

        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            // measure
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
        LayoutParams layoutParams = getLayoutParams();
        arg3 = getWidth();
        final int count = getChildCount();
        int row = 0;// which row lay you view relative to parent
        int lengthX = 0;    // right position of child relative to parent
        int lengthY = 0;    // bottom position of child relative to parent
        for (int i = 0; i < count; i++) {
            final View child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            lengthX += width + VIEW_MARGIN;
            lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
            //if it can't drawing on a same line , skip to next line
            if (lengthX > arg3) {
                lengthX = width + VIEW_MARGIN;
                row++;
                lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
            }
            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
//            child.layout(lengthX - width + layoutParams.leftMargin, lengthY - height + layoutParams.topMargin, lengthX - layoutParams.rightMargin, lengthY - layoutParams.bottomMargin);
        }


        layoutParams.height = lengthY;
        setLayoutParams(layoutParams);
    }

    int radioButtonLayout = 0;

    public void setRadioButton(int radioButtonLayout) {
        this.radioButtonLayout = radioButtonLayout;
    }

    /**
     * 添加按钮
     *
     * @param title
     * @param value
     */
    public void add(String title, GoodsInfoEntity.AttrListBean value) {
        add(title, value, false);
    }

    CompoundButton checked = null;

    /**
     * 添加按钮
     *
     * @param title
     * @param value
     * @param isCheck 是否选中
     */
    public void add(String title, GoodsInfoEntity.AttrListBean value, boolean isCheck) {
        if (radioButtonLayout == 0) {
            return;
        }
        View view = LayoutInflater.from(getContext()).inflate(radioButtonLayout, null);
        CompoundButton radioButton = null;
        if (view instanceof CompoundButton) {
            radioButton = (CompoundButton) view;
        } else if (view instanceof ViewGroup) {
            ViewGroup v = (ViewGroup) view;
            for (int i = 0; i < v.getChildCount(); i++) {
                if (v.getChildAt(i) instanceof CompoundButton) {
                    radioButton = (CompoundButton) v.getChildAt(i);
                    break;
                }
            }
        }
        if (radioButton == null) {
            return;
        }
        radioButton.setText(title);
        radioButton.setTag(value);

        radioButton.setChecked(isCheck);
        radioButton.setOnCheckedChangeListener(this);
        if (isCheck) {
            if (checked != null) {
                checked.setChecked(false);
            }
            checked = radioButton;
        }

        addView(view);
    }

    /**
     * 设置回掉接口
     *
     * @param onCheckedChanged
     */
    public void setOnCheckedChanged(OnCheckedChanged onCheckedChanged) {
        this.onCheckedChanged = onCheckedChanged;
    }

    /**
     * 选择时回掉
     *
     * @param compoundButton
     * @param b
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (checked != null) {
            checked.setChecked(false);
        }
        checked = compoundButton;

        if (onCheckedChanged != null) {
            onCheckedChanged.onCheckedChanged(compoundButton, (GoodsInfoEntity.AttrListBean) compoundButton.getTag());
        }
    }

    /**
     * 回掉接口
     */
    public interface OnCheckedChanged {
        public void onCheckedChanged(View view, GoodsInfoEntity.AttrListBean value);
    }

    public void setChecked(CompoundButton checked) {
        this.checked = checked;
    }
}