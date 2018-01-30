package com.music.ui.activity.video;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.VideoHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频界面
 */
public class VideoActivity extends BaseActivity {

    protected XRecyclerView mRecyclerEntityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_list);
        setContentView(R.layout.activity_goods_search);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getRecyclerView().setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerEntityView.getAdapter().bindHolder(new VideoHolder());
        List<String> data = new ArrayList<>();
        data.add("111");
        mRecyclerEntityView.getAdapter().setData(0, data);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choice, menu);
        return true;
    }
}
