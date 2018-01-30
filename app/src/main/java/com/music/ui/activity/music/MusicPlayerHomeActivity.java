package com.music.ui.activity.music;

import android.os.Bundle;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.MusicPlayerHomeListHolder;
import com.music.ui.holder.MusicPlayerHomeTopHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩音乐
 */
public class MusicPlayerHomeActivity extends BaseActivity {

    private XRecyclerView mXRecyclerEntityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_list);
        setContentView(R.layout.activity_goods_search);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mXRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        mXRecyclerEntityView.getAdapter().bindHolder(new  MusicPlayerHomeTopHolder());
        mXRecyclerEntityView.getAdapter().bindHolder(new  MusicPlayerHomeListHolder());
        List<String> data = new ArrayList<>();
        data.add("111");
        mXRecyclerEntityView.getAdapter().setData(0, data);
        mXRecyclerEntityView.getAdapter().setData(1, data);
    }
}
