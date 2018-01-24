package com.music.ui.activity.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.framework.view.XGridViewForScrollView;
import com.music.R;
import com.music.ui.entity.shop.GoodsInfoEntity;
import com.music.ui.holder.adapter.GridRecommendItemGroup;
import com.music.ui.holder.adapter.GridShopItemGroup;

/**
 * Created by feq on 2017/12/28.
 */

public class GoodsRecommend extends LinearLayout {
    private Context context;
    protected XGridViewForScrollView girdView;

    public GoodsRecommend(Context context, GoodsInfoEntity goodsInfoEntity) {
        super(context);
        this.context = context;
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mLayoutInflater != null) {
            mLayoutInflater.inflate(R.layout.item_shop_group, this);
            initView();
            setData(goodsInfoEntity);
        }
    }

    private void setData(GoodsInfoEntity goodsInfoEntity) {
        GridRecommendItemGroup gridShopItemGroup = new GridRecommendItemGroup(context);
        girdView.setAdapter(gridShopItemGroup);
    }

    private void initView() {
        girdView = (XGridViewForScrollView) findViewById(R.id.girdView);
    }
}
