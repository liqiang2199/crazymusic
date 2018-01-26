package com.music.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framework.view.Toolbar;
import com.framework.view.recyclerView.XRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.index.TipsPostActivity;
import com.music.ui.entity.CommunityListEntity;
import com.music.ui.entity.XPage;
import com.music.ui.holder.CommentHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 社区
 */
public class CommunityFragment extends Fragment implements XRecyclerView.PullLoadMoreListener, View.OnClickListener {
    protected Toolbar toolbar;
    protected TextView btnRight;
    private View rootView;
    protected XRecyclerView mRecyclerEntityView;
    private int page = 1, total;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_community, null);
        }
        initView();
        return rootView;
    }

    private void initView() {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.community));
        mRecyclerEntityView = (XRecyclerView) rootView.findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerEntityView.getAdapter().bindHolder(new CommentHolder());
        mRecyclerEntityView.setOnPullLoadMoreListener(this);
        btnRight = (TextView) rootView.findViewById(R.id.btn_right);
        btnRight.setText(getString(R.string.topic_post));
        btnRight.setOnClickListener(this);

        getData();
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.COMMUNITY_LIST);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("pageIndex", page + "");
                    jsonObject.put("pageSize", 10 + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpRequesParams.setAsJsonContent(true);
                httpRequesParams.setBodyContent(jsonObject.toString());
                HttpUtils.post(getActivity(), false, httpRequesParams, new HttpResponseCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.v("music","     社区       "+result);
                        Gson gson = new Gson();
                        Type type = new TypeToken<XPage<CommunityListEntity>>() {
                        }.getType();
                        XPage<CommunityListEntity> resultData = gson.fromJson(result, type);
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
        },800);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right:
                //发布
                startActivity(new Intent(getActivity(), TipsPostActivity.class));
                break;
        }
    }
}
