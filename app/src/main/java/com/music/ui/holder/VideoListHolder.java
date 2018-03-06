package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.activity.user.MyVideoDetailActivity;
import com.music.ui.activity.user.MyWalletActivity;

/**
 * 课程
 */
public class VideoListHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ListHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_my_video;
    }

    class ListHolder extends XViewHolder {
        private LinearLayout video_liner;
        public ListHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            video_liner = rootView.findViewById(R.id.video_liner);
            video_liner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MyVideoDetailActivity.class));
                }
            });
        }

        @Override
        protected void onBindData(Object itemData) {
        }
    }
}
