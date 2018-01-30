package com.music.ui.activity.index;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.music.ui.dialog.CommentsPublishDialog;
import com.music.ui.entity.CommunityDetailListEntity;
import com.music.ui.entity.CommunityListEntity;
import com.music.ui.entity.XPage;
import com.music.ui.entity.XResult;
import com.music.ui.holder.CommentEditViewHolder;
import com.music.ui.holder.CommentInfoListHolder;
import com.music.ui.holder.CommentInfoTopHolder;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 社区详情
 */
public class CommunityDetailActivity extends BaseActivity implements XRecyclerView.PullLoadMoreListener {

    protected XRecyclerView mRecyclerEntityView;
    private CommunityListEntity communityListEntity;
    private int page = 1, total;
    private EditText edit_comments;
    private TextView text_publish_comments;
    private TextView text_comments;
    private LinearLayout liner_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_list);
        setContentView(R.layout.activity_community_detail);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {

        Width_Height();
        edit_comments = findViewById(R.id.edit_comments);
        text_publish_comments = findViewById(R.id.text_publish_comments);
        text_comments = findViewById(R.id.text_comments);
        edit_comments.setVisibility(View.GONE);
        text_comments.setVisibility(View.VISIBLE);
        liner_dialog = findViewById(R.id.liner_dialog);


        liner_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论点击
                CommentsPublishDialog commentsPublishDialog = new CommentsPublishDialog(mContext,
                        R.style.ScreenDialogStyle,width,height);
                commentsPublishDialog.show();
            }
        });

        mRecyclerEntityView = findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getAdapter().bindHolder(new CommentInfoTopHolder());
        mRecyclerEntityView.getAdapter().bindHolder(new CommentInfoListHolder());

        communityListEntity = EventBus.getDefault().getStickyEvent(CommunityListEntity.class);

        EventBus.getDefault().removeStickyEvent(CommunityListEntity.class);

        if (communityListEntity != null) {
            mRecyclerEntityView.getAdapter().setData(0, communityListEntity);
            getData();
        }
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.COMMUNITY_EVALUATE_LIST);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("community_id", communityListEntity.getId());
            jsonObject.put("pageIndex", page + "");
            jsonObject.put("pageSize", 10 + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<XPage<CommunityDetailListEntity>>() {
                }.getType();
                XPage<CommunityDetailListEntity> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
                        page = resultData.getPage().getPageIndex();
                        total = resultData.getPage().getTotalPage();
                        if (page == 1) {
                            mRecyclerEntityView.getAdapter().setData(1, resultData.getData());
                        } else {
                            mRecyclerEntityView.getAdapter().addData(1, resultData.getData());
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
