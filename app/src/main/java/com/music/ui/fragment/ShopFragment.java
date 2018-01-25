package com.music.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.view.XGridViewForScrollView;
import com.framework.view.recyclerView.XRecyclerView;
import com.framework.view.viewPager.XSlidingPlayView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.shop.SearchActivity;
import com.music.ui.activity.shop.ShopCarActivity;
import com.music.ui.entity.shop.ShopIndexEntity;
import com.music.ui.entity.XResult;
import com.music.ui.holder.adapter.ShopClassfiyAdapter;
import com.music.ui.holder.ShopListHolder;

import java.lang.reflect.Type;

/**
 * 商城
 */
public class ShopFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected XRecyclerView mRecyclerEntityView;
    protected TextView etSearch;
    protected ImageView ivSerch;
    protected XSlidingPlayView mXSlidingPlayView;
    protected XGridViewForScrollView shopClassGrid;
    protected XGridViewForScrollView shopGrid;
    private ShopListHolder shopListHolder;
    private ShopClassfiyAdapter classfiyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_shop, null);
        }
        initView();
        getData();
        return rootView;
    }

    private void initView() {
        etSearch = (TextView) rootView.findViewById(R.id.et_search);
        ivSerch = (ImageView) rootView.findViewById(R.id.iv_search);
        ivSerch.setOnClickListener(this);
        etSearch.setOnClickListener(this);

        mXSlidingPlayView = (XSlidingPlayView) rootView.findViewById(R.id.mXSlidingPlayView);
        shopClassGrid = (XGridViewForScrollView) rootView.findViewById(R.id.shop_class_grid);
        shopGrid = (XGridViewForScrollView) rootView.findViewById(R.id.shop_grid);

        mXSlidingPlayView.setNavPageResources(R.drawable.circle_zi, R.drawable.circle_gray_kx);
        mXSlidingPlayView.setNavHorizontalGravity(Gravity.RIGHT);
        mXSlidingPlayView.startPlay();

        //商品
        shopListHolder = new ShopListHolder(getActivity());
        shopGrid.setAdapter(shopListHolder);
        //分类
        classfiyAdapter = new ShopClassfiyAdapter(getActivity());
        shopClassGrid.setAdapter(classfiyAdapter);

        //轮播图
        mXSlidingPlayView.removeAllViews();
        addImage(R.mipmap.ban1);
        addImage(R.mipmap.ban2);
        addImage(R.mipmap.ban3);
    }

    /**
     * 获取数据
     */
    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_INDEX);
        HttpUtils.post(getActivity(), false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<XResult<ShopIndexEntity>>() {
                }.getType();
                XResult<ShopIndexEntity> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
                        if (resultData.getData().getType_datas() != null && resultData.getData().getType_datas().size() > 0) {
                            classfiyAdapter.setList(resultData.getData().getType_datas());
                        }
                        if (resultData.getData().getProduct_datas() != null && resultData.getData().getProduct_datas().size() > 0) {
                            shopListHolder.setList(resultData.getData().getProduct_datas());
                        }
                    }
                }
            }

            @Override
            public void onFailed(String failedMsg) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                //购物车
                startActivity(new Intent(getActivity(), ShopCarActivity.class));
                break;
            case R.id.et_search:
                //搜所商品
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    //轮播
    private void addImage(int url) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(url);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20, 20, 20, 20);
        imageView.setLayoutParams(layoutParams);
        mXSlidingPlayView.addView(imageView);
    }
}
