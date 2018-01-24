package com.music.ui.activity.music;

import android.os.Bundle;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.MusicPlayerHomeListHolder;
import com.music.ui.holder.MusicPlayerHomeTopHolder;

/**
 * 玩音乐
 */
public class MusicPlayerHomeActivity extends BaseActivity {

    private XRecyclerView mXRecyclerEntityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mXRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        mXRecyclerEntityView.getAdapter().bindHolder(new  MusicPlayerHomeTopHolder());
        mXRecyclerEntityView.getAdapter().bindHolder(new  MusicPlayerHomeListHolder());
    }
}
