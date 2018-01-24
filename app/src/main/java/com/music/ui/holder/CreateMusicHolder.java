package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.activity.music.MusicPlayerActivity;
import com.music.ui.entity.CreateMusicEntity;

import static com.music.R.id.tv_name;

/**
 * 玩音乐---更多
 */
public class CreateMusicHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_create_music;
    }

    private class ViewHolder extends XViewHolder {

        private ImageView ivBg;
        private TextView tvMusicName, tvName;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            ivBg = (ImageView) rootView.findViewById(R.id.iv_icon);
            tvMusicName = (TextView) rootView.findViewById(R.id.tv_music_name);
            tvName = (TextView) rootView.findViewById(tv_name);
        }

        @Override
        protected void onBindData(Object itemData) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MusicPlayerActivity.class));
                }
            });
        }
    }
}
