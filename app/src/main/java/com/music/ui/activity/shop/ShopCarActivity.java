package com.music.ui.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.framework.view.recyclerView.XRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.BaseActivity;
import com.music.ui.entity.CommunityListEntity;
import com.music.ui.entity.XPage;
import com.music.ui.entity.shop.ShopEntity;
import com.music.ui.holder.OrderHolder;
import com.music.ui.holder.ShopCarHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 */
public class ShopCarActivity extends BaseActivity implements XRecyclerView.PullLoadMoreListener {

    protected XRecyclerView mRecyclerEntityView;
    private int page = 1, total;
    private TextView bt_sure;//下单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shop_car);
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {

        bt_sure = findViewById(R.id.bt_sure);

        mRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mRecyclerEntityView.getAdapter().bindHolder(new ShopCarHolder());
        mRecyclerEntityView.setOnPullLoadMoreListener(this);

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定订单
                startActivity(new Intent(mContext,SureOrderActivity.class));
            }
        });
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_CAR_LIST);
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<XPage<ShopEntity.DataBean>>() {
                }.getType();
                XPage<ShopEntity.DataBean> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
//                        page = resultData.getPage().getPageIndex();
//                        total = resultData.getPage().getTotalPage();
                        if (page == 1) {
                            mRecyclerEntityView.getAdapter().setData(0, resultData.getData());
                        } else {
                            mRecyclerEntityView.getAdapter().addData(0, resultData.getData());
                        }
                    }
                }
            }

            @Override
            public void onFailed(String failedMsg) {
            }

            @Override
            public void onFinished() {
                mRecyclerEntityView.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 1;
        getData();
    }

    @Override
    public boolean onLoadMore() {
        if (page < total) {
            page++;
            getData();
            return true;
        }
        return false;
    }

}
