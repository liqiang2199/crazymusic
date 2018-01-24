package com.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.framework.R;


/**
 * 带标题的EditView
 * Created by cheng on 2016/10/10.
 */

public class EditTitleView extends EditText {
    int titleColor;
    private String title;
    float titleWidth;
    private float titlePaddingTop = 10;
    private float titlePaddingLeft = 20;
    int paddingLeft;
    int paddingTop;
    int paddingRight;
    int paddingBottom;
    private boolean titleCenterVertical;
    private int splitLineColor;
    private int splitLineLeft;//线左边
    private int splitLineRight;//线左边
    /**
     * 线条高度
     */
    private float splitLineHeight;
    /**
     * 线条位置
     */
    private float splitLineY;

    public EditTitleView(Context context) {
        super(context);
        titleColor = getTextColors().getDefaultColor();
        init(null);
    }

    public EditTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EditTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.EditTitleView);
        titleColor = a.getColor(R.styleable.EditTitleView_titleColor, getTextColors().getDefaultColor());
        title = a.getString(R.styleable.EditTitleView_title);
        titleWidth = a.getDimension(R.styleable.EditTitleView_titleWidth, 0f);
        titlePaddingLeft = a.getDimension(R.styleable.EditTitleView_titlePaddingLeft, 20f);
        titlePaddingTop = a.getDimension(R.styleable.EditTitleView_titlePaddingTop, 10f);
        titleCenterVertical = a.getBoolean(R.styleable.EditTitleView_titleCenterVertical, true);
        splitLineColor = a.getColor(R.styleable.EditTitleView_splitLineColor, 0xffb2b2b2);
        splitLineHeight = a.getDimensionPixelSize(R.styleable.EditTitleView_splitLineHeight, 0);
        splitLineLeft = a.getDimensionPixelSize(R.styleable.EditTitleView_spliLineLeft, 0);
        splitLineRight = a.getDimensionPixelSize(R.styleable.EditTitleView_spliLineRight, 0);
        splitLineY = (int) Math.ceil(splitLineHeight / 2f);
        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        Paint paint = new Paint();
        paint.setTextSize(getTextSize());
        paint.setColor(titleColor);
        if (!TextUtils.isEmpty(title)) {
            float h = titlePaddingTop;
            if (titleCenterVertical) {
                Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
                h = (height - fontMetrics.bottom - fontMetrics.top) / 2;
            }
            paint.setAntiAlias(true);
            canvas.drawText(title, titlePaddingLeft, h, paint);
            float textWidth;
            if (titleWidth == 0) {
                textWidth = paint.measureText(title);
            } else {
                textWidth = titleWidth;
            }
            super.setPadding((int) textWidth + 40 + paddingLeft + (int) titlePaddingLeft, paddingTop, paddingRight, paddingBottom);
        }

        if (splitLineHeight > 0) {
            paint.setColor(splitLineColor);
            paint.setStrokeWidth(splitLineHeight);
            canvas.drawLine(0, height - splitLineY, width, height - splitLineY, paint);//画线
            canvas.drawLine(splitLineLeft, splitLineY, width - splitLineRight, splitLineY, paint);//画线
        }
        super.onDraw(canvas);
    }

    public void setPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
    }

}
