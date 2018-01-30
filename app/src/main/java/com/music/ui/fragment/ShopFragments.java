package com.music.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framework.view.recyclerView.XRecyclerView;
import com.google.gson.Gson;
import com.music.BaseApp;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.shop.ShopCarActivity;
import com.music.ui.entity.shop.GoodsAllEntity;
import com.music.ui.holder.ShopHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feq on 2017/12/22.
 */

public class ShopFragments extends Fragment implements View.OnClickListener {

    private LinearLayout titleListView;
    private List<GoodsAllEntity.DataBean> titleList = new ArrayList<>();
    private XRecyclerView mGoodsListView;
    boolean isScroll = false;
    private RelativeLayout titleViews[];
    private ImageView mShopCar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.shop_fragment, null);
        initView(mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }


    private void initView(View mView) {
        titleListView = (LinearLayout) mView.findViewById(R.id.tools);
        mView.findViewById(R.id.iv_search).setOnClickListener(this);
        mGoodsListView = mView.findViewById(R.id.goods_pager);
        mGoodsListView.getAdapter().bindHolder(new ShopHolder());

        mGoodsListView.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isScroll) {
                        RecyclerView.LayoutManager layoutManager = mGoodsListView.getRecyclerView().getLayoutManager();
                        int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                        for (int i = 0; i < titleList.size(); i++) {
                            ((TextView) titleViews[i].findViewById(R.id.title)).setTextColor(getResources().getColor(R.color.un_checked));
                            titleViews[i].findViewById(R.id.line).setVisibility(View.GONE);
                        }
                        //                        titleListView.getChildAt(firstVisibleItemPosition).setBackgroundColor(getResources().getColor(R.color.cardview_dark_background));
                        ((TextView) titleViews[firstVisibleItemPosition].findViewById(R.id.title)).setTextColor(getResources().getColor(R.color.checked));
                        (titleViews[firstVisibleItemPosition].findViewById(R.id.line)).setVisibility(View.VISIBLE);
                    }
                    isScroll = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                //购物车
                startActivity(new Intent(getActivity(), ShopCarActivity.class));
                return;
        }
        isScroll = true;
        for (int i = 0; i < titleList.size(); i++) {
            ((TextView) titleViews[i].findViewById(R.id.title)).setTextColor(getResources().getColor(R.color.un_checked));
            titleViews[i].findViewById(R.id.line).setVisibility(View.GONE);
        }
        ((TextView) titleViews[view.getId()].findViewById(R.id.title)).setTextColor(getResources().getColor(R.color.checked));
        titleViews[view.getId()].findViewById(R.id.line).setVisibility(View.VISIBLE);
        mGoodsListView.getRecyclerView().smoothScrollToPosition(view.getId());
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_CLASSES_ALL);
        httpRequesParams.setAsJsonContent(true);
        HttpUtils.post(BaseApp.instance(), false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---", "onSuccess: " + result);
                Gson gson = new Gson();
                GoodsAllEntity goodsAllEntity = gson.fromJson(result, GoodsAllEntity.class);

                if (goodsAllEntity.getCode() == 0) {
                    if (goodsAllEntity.getData() != null) {
                        titleList.addAll(goodsAllEntity.getData());
                        initData();
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

    private void initData() {
        titleViews = new RelativeLayout[titleList.size()];
        mGoodsListView.getAdapter().setData(0, titleList);
        for (int i = 0; i < titleList.size(); i++) {
            RelativeLayout view = (RelativeLayout) View.inflate(BaseApp.instance(), R.layout.item_shop_title, null);
            view.setId(i);
            view.setOnClickListener(this);
            TextView titleTextView = view.findViewById(R.id.title);
            if (i == 0) {
                titleTextView.setTextColor(getResources().getColor(R.color.checked));
                view.findViewById(R.id.line).setVisibility(View.VISIBLE);
            }
            titleTextView.setText(titleList.get(i).getType_name());
            titleListView.addView(view);
            titleViews[i] = view;
        }
    }
}
