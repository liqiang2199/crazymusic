package com.music.ui.activity.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.view.viewPager.XSlidingPlayView;
import com.music.ConstHost;
import com.music.R;
import com.music.ui.entity.shop.GoodsInfoEntity;
import com.music.utils.XImageLoadUtils;

/**
 * 商品信息模板
 */
public class GoodsInfoTemplate extends LinearLayout {

    protected XSlidingPlayView mXSlidingPlayView;
    protected TextView tvTitle;
    protected TextView tvPrice;
    protected TextView tvTag;
    protected TextView tvOldPrice;
    protected WebView mWebView;
    private Context context;
    private LinearLayout mComment;

    public GoodsInfoTemplate(Context context, GoodsInfoEntity goodsInfoEntity) {
        super(context);
        this.context = context;
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mLayoutInflater != null) {
            mLayoutInflater.inflate(R.layout.layout_goods_info_temelt, this);
            initView();
            setData(goodsInfoEntity);
        }
    }

    private void initView() {
        mXSlidingPlayView = (XSlidingPlayView) findViewById(R.id.mXSlidingPlayView);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvTag = (TextView) findViewById(R.id.tv_tag);
        tvOldPrice = (TextView) findViewById(R.id.tv_old_price);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mComment = findViewById(R.id.ll_comment);

        //WebView设置
        mWebView.getSettings().setJavaScriptEnabled(true); // 支持js
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);// 单列模式
        mWebView.getSettings().setDefaultFontSize(16);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mXSlidingPlayView.setNavPageResources(R.drawable.circle_zi, R.drawable.circle_gray_kx);
        mXSlidingPlayView.setNavHorizontalGravity(Gravity.CENTER);
        mXSlidingPlayView.startPlay();
    }

    private void setData(GoodsInfoEntity goodsInfoEntity) {
        if (goodsInfoEntity != null) {
            //图片
            if (goodsInfoEntity.getImgs() != null && goodsInfoEntity.getImgs().size() > 0) {
                mXSlidingPlayView.removeAllViews();
                for (int i = 0; i < goodsInfoEntity.getImgs().size(); i++) {
                    addImage(goodsInfoEntity.getImgs().get(i).getProduct_img());
                }
            }
            tvTitle.setText(goodsInfoEntity.getName());
            tvPrice.setText(goodsInfoEntity.getNow_price() + "");
            tvOldPrice.setText("原价：" + goodsInfoEntity.getCost_price());
            if (!TextUtils.isEmpty(goodsInfoEntity.getTag_name())) {
                tvTag.setVisibility(VISIBLE);
                tvTag.setText(goodsInfoEntity.getTag_name());
            } else {
                tvTag.setVisibility(GONE);
            }
            mWebView.loadDataWithBaseURL(ConstHost.BASE_HOST, goodsInfoEntity.getHtml(), "text/html", "utf-8", null);

            mComment.addView(new GoodsInfoReconTemplate(context, goodsInfoEntity));//评价--推荐商品列表);
        }
    }

    //轮播
    private void addImage(String url) {
        ImageView imageView = new ImageView(context);
        XImageLoadUtils.loadFitImage(context, url, imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20, 20, 20, 20);
        imageView.setLayoutParams(layoutParams);
        mXSlidingPlayView.addView(imageView);
    }
}
