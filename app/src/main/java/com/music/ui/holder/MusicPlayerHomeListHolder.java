package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framework.view.XGridViewForScrollView;
import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.activity.music.MyCreateMusicActivity;

/**
 * 玩音乐列表Holder
 */
public class MusicPlayerHomeListHolder extends IViewHolder {

    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_music_player_home;
    }

    private class ViewHolder extends XViewHolder {
        protected TextView textTitle;
        protected TextView textMore;
        protected XGridViewForScrollView girdView;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            textTitle = (TextView) rootView.findViewById(R.id.text_title);
            textMore = (TextView) rootView.findViewById(R.id.text_more);
            girdView = (XGridViewForScrollView) rootView.findViewById(R.id.girdView);
        }

        @Override
        protected void onBindData(Object itemData) {

            girdView.setAdapter(new MusicPlayerHomeListAdapter(mContext));

            //更多列表
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MyCreateMusicActivity.class));
                }
            });
        }
    }
}
