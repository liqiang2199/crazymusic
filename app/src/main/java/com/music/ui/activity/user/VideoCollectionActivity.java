package com.music.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.VideoCollectionListHolder;
import com.music.ui.holder.VideoListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by empty cup on 2018/3/6.
 * 我的视频
 */

public class VideoCollectionActivity extends BaseActivity {
    protected XRecyclerView mRecyclerEntityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_list);

        setContentView(R.layout.activity_goods_search);
        super.onCreate(savedInstanceState);
//        if (!isActivityNeedLogin()){
//            this.finish();
//            return;
//        }
        initView();
    }

    private void initView() {
        mRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);

        mRecyclerEntityView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mRecyclerEntityView.getAdapter().bindHolder(new VideoCollectionListHolder());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mRecyclerEntityView.getAdapter().setData(0, list);
    }
}
