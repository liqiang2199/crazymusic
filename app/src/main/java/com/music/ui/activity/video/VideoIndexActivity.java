package com.music.ui.activity.video;

import android.os.Bundle;
import android.widget.GridView;

import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.adapter.VideoIndexAdapter;

/**
 * Created by Administrator on 2018/3/13.
 * 首页点击 视频进入
 */

public class VideoIndexActivity extends BaseActivity{
    private GridView grid_video;
    private VideoIndexAdapter videoIndexAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_index);
        init_Data();
    }

    private void init_Data() {
        grid_video = findViewById(R.id.grid_video);
        videoIndexAdapter = new VideoIndexAdapter(this);
        grid_video.setAdapter(videoIndexAdapter);
    }
}
