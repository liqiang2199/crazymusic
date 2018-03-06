package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.framework.view.viewPager.XSlidingPlayView;
import com.music.R;
import com.music.ui.activity.music.MusicPlayerHomeActivity;
import com.music.ui.activity.video.OffLineActivity;
import com.music.ui.activity.video.VideoActivity;

/**
 * 首页顶部
 */

public class IndexTopHolder extends IViewHolder {

    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_index_top;
    }

    private class ViewHolder extends XViewHolder<String> {
        protected XSlidingPlayView mXSlidingPlayView;
//        protected LinearLayout btLive;
//        protected LinearLayout btVideo;
//        protected LinearLayout btOnline;
//        protected LinearLayout btMusic;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            mXSlidingPlayView = (XSlidingPlayView) rootView.findViewById(R.id.mXSlidingPlayView);
            mXSlidingPlayView.setNavPageResources(R.drawable.circle_zi, R.drawable.circle_gray_kx);
            mXSlidingPlayView.setNavHorizontalGravity(Gravity.CENTER);
            mXSlidingPlayView.startPlay();
//            btLive = (LinearLayout) rootView.findViewById(R.id.bt_live);
//            btVideo = (LinearLayout) rootView.findViewById(R.id.bt_video);
//            btOnline = (LinearLayout) rootView.findViewById(R.id.bt_online);
//            btMusic = (LinearLayout) rootView.findViewById(R.id.bt_music);
        }

        @Override
        protected void onBindData(String itemData) {
            mXSlidingPlayView.removeAllViews();
            addImage(R.mipmap.ban1);
            addImage(R.mipmap.ban2);
            addImage(R.mipmap.ban3);

//            btVideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //视频
                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
//                }
//            });
//            btMusic.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //玩音乐
//                    mContext.startActivity(new Intent(mContext, MusicPlayerHomeActivity.class));
//                }
//            });
//            btOnline.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //线下约
//                    mContext.startActivity(new Intent(mContext, OffLineActivity.class));
//                }
//            });
//            btLive.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //直播
////                    mContext.startActivity(new Intent(mContext, VideoActivity.class));
//                }
//            });
        }

        private void addImage(int url) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(url);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20, 20, 20, 20);
            imageView.setLayoutParams(layoutParams);
            mXSlidingPlayView.addView(imageView);
        }
    }


}
