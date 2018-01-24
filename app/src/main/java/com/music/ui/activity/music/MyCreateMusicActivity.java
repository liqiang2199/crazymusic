package com.music.ui.activity.music;

import android.os.Bundle;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.CreateMusicHolder;

/**
 * 自创音乐
 */
public class MyCreateMusicActivity extends BaseActivity {

    private XRecyclerView mXRecyclerEntityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);
        setTitle("自创音乐");
        initView();
    }

    private void initView() {
        mXRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        //CreateMusicEntity
        mXRecyclerEntityView.getAdapter().bindHolder(new CreateMusicHolder());
    }

}
