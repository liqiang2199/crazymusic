package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.activity.video.VideoPlayActivity;

/**
 * 视频列表
 */
public class VideoHolder extends IViewHolder {

    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_video;
    }

    private class ViewHolder extends XViewHolder {
        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
        }

        @Override
        protected void onBindData(Object itemData) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, VideoPlayActivity.class));
                }
            });
        }
    }
}
