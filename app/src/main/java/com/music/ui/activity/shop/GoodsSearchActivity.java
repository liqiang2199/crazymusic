package com.music.ui.activity.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.framework.view.recyclerView.DefineRefreshWithLoadView;
import com.framework.view.recyclerView.XRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.BaseActivity;
import com.music.ui.entity.XPage;
import com.music.ui.entity.shop.GoodsClassesListEntity;
import com.music.ui.holder.GoodsClassesListHolder;
import com.music.utils.DialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 商品分类、搜索页面
 */
public class GoodsSearchActivity extends BaseActivity implements XRecyclerView.PullLoadMoreListener {
    //    protected EditText etSearch;
//    protected ImageView ivSerch;
    protected XRecyclerView mRecyclerEntityView;
    private int page = 1, total;
    private String typeId;
    private String typeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_goods_search);
        super.onCreate(savedInstanceState);
        typeId = getIntent().getStringExtra("tid");
        typeName = getIntent().getStringExtra("typeName");
        initView();
        getData();
    }

    private void initView() {
//        etSearch = (EditText) findViewById(R.id.et_search);
//        ivSerch = (ImageView) findViewById(R.id.iv_serch);
//        ivSerch.setOnClickListener(this);
        mRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getRecyclerView().setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerEntityView.getAdapter().bindHolder(new GoodsClassesListHolder());
        mRecyclerEntityView.setOnPullLoadMoreListener(this);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setTitle(typeName);
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_PRODUCE_LIST);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageIndex", page + "");
            jsonObject.put("pageSize", 10 + "");
            jsonObject.put("typeId", typeId);
//            jsonObject.put("searchKey", etSearch.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                mRecyclerEntityView.setPullLoadMoreCompleted();
                Gson gson = new Gson();
                Type type = new TypeToken<XPage<GoodsClassesListEntity>>() {
                }.getType();
                XPage<GoodsClassesListEntity> resultData = gson.fromJson(result, type);
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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_search:
                //分类
                DialogUtils.showGoodsClasses(this, typeId, new DialogUtils.OnResult() {
                    @Override
                    public void onSuccess(String name, String id) {
                        typeId = id;
                        page = 1;
                        getData();
                    }
                });
                break;
        }
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
