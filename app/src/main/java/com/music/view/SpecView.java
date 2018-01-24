package com.music.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.entity.shop.GoodsInfoEntity;

import java.util.List;

/**
 * 规格定义控件
 */
public class SpecView extends LinearLayout {

    protected TextView tvSpaceName;
    protected WrapRadioButton mWrapRadioButton;

    public SpecView(Context context) {
        super(context);
        init();
    }

    public SpecView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpecView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpecView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_space_base, null);
        addView(view);
        tvSpaceName = XBaseViewHolder.get(view, R.id.tv_spaceName);
        mWrapRadioButton = XBaseViewHolder.get(view, R.id.mWrapRadioButton);
        mWrapRadioButton.setRadioButton(R.layout.item_space);
    }

    public void setOnCheckedChanged(WrapRadioButton.OnCheckedChanged onCheckedChanged) {
        mWrapRadioButton.setOnCheckedChanged(onCheckedChanged);
    }

    public void setData(List<GoodsInfoEntity.AttrListBean> specsBeans) {
        if (specsBeans == null) {
            return;
        }
        if (specsBeans.size() > 0) {
            tvSpaceName.setText(specsBeans.get(0).getName() + ":");
        }
        for (GoodsInfoEntity.AttrListBean specsBean : specsBeans) {
            mWrapRadioButton.add(specsBean.getValue(), specsBean, true);
        }
    }
}