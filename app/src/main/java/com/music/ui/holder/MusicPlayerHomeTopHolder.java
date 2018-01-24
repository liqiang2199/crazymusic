package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.framework.view.viewPager.XSlidingPlayView;
import com.music.R;

/**
 */
public class MusicPlayerHomeTopHolder extends IViewHolder {

    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }
    @Override
    public int getLayout() {
        return R.layout.item_music_player_home_top;
    }

    private class ViewHolder extends XViewHolder {
        protected XSlidingPlayView mXSlidingPlayView;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            mXSlidingPlayView = (XSlidingPlayView) itemView.findViewById(R.id.mXSlidingPlayView);
            mXSlidingPlayView.setNavPageResources(R.drawable.circle_zi, R.drawable.circle_gray_kx);
            mXSlidingPlayView.setNavHorizontalGravity(Gravity.CENTER);
            mXSlidingPlayView.startPlay();
        }

        @Override
        protected void onBindData(Object itemData) {
            addImage(R.mipmap.ban1);
            addImage(R.mipmap.ban2);
            addImage(R.mipmap.ban3);
            addImage(R.mipmap.ban4);
        }

        //轮播
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
