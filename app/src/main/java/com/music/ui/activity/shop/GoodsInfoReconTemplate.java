package com.music.ui.activity.shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.view.XGridViewForScrollView;
import com.music.R;
import com.music.ui.entity.shop.GoodsInfoEntity;

/**
 * 商品详情--评价/推荐商品接口
 */
public class GoodsInfoReconTemplate extends LinearLayout {
    protected ImageView ivHead;
    protected TextView tvUserName;
    protected TextView tvTitle;
    protected TextView tvContent;
    protected XGridViewForScrollView gridView;
    protected TextView btPingjia;
    private Context context;

    public GoodsInfoReconTemplate(Context context, GoodsInfoEntity goodsInfoEntity) {
        super(context);
        this.context = context;
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mLayoutInflater != null) {
            mLayoutInflater.inflate(R.layout.layout_goods_info_recom_temep, this);
            initView();
            setData(goodsInfoEntity);
        }
    }

    private void initView() {
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        gridView = (XGridViewForScrollView) findViewById(R.id.gridView);
        btPingjia = (TextView) findViewById(R.id.bt_pingjia);
    }

    private void setData(GoodsInfoEntity goodsInfoEntity) {
        if (goodsInfoEntity != null) {
            btPingjia.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //全部评论
                    context.startActivity(new Intent(context, AllEvaluateActivity.class));
                }
            });
        }
    }
}
