package com.music.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framework.view.recyclerView.XRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.entity.XPage;
import com.music.ui.entity.shop.OrderListEntity;
import com.music.ui.holder.OrderHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 订单
 */
@SuppressLint("ValidFragment")
public class OrderListFragment extends Fragment implements XRecyclerView.PullLoadMoreListener {
    private View rootView;
    protected XRecyclerView mRecyclerEntityView;
    private int page = 1, total;
    private String type;

    public OrderListFragment(String s) {
        this.type = s;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, null);
        initView();
        return rootView;
    }

    private void initView() {
        mRecyclerEntityView = (XRecyclerView) rootView.findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerEntityView.getAdapter().bindHolder(new OrderHolder());
        mRecyclerEntityView.setOnPullLoadMoreListener(this);
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_ORDER_LIST);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", "1");
            jsonObject.put("status", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(getActivity(), false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<XPage<OrderListEntity>>() {
                }.getType();
                XPage<OrderListEntity> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
                        page = resultData.getPage().getPageIndex();
                        total = resultData.getPage().getTotalPage();
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
