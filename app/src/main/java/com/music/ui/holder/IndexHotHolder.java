package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.framework.view.XGridViewForScrollView;
import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.holder.adapter.LiveHomeAdapter;
import com.music.ui.holder.adapter.OfflineHomeAdapter;
import com.music.ui.holder.adapter.PlayHomeAdapter;
import com.music.ui.holder.adapter.VideoHomeAdapter;

/**
 * 内容列表
 */
public class IndexHotHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_index_hot;
    }

    private class ViewHolder extends XViewHolder {
//        protected TextView textTitle;
//        protected TextView textMore;
        protected XGridViewForScrollView girdView;
        protected LiveHomeAdapter liveHomeAdapter;//直播
//        private PlayHomeAdapter playHomeAdapter;//玩音乐
//        private VideoHomeAdapter videoHomeAdapter;//视频
//        private OfflineHomeAdapter offlineHomeAdapter;//线下约

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
//            textTitle = (TextView) itemView.findViewById(R.id.text_title);
//            textMore = (TextView) itemView.findViewById(R.id.text_more);
            girdView = (XGridViewForScrollView) itemView.findViewById(R.id.girdView);
        }

        @Override
        protected void onBindData(Object itemData) {
            //直播
            liveHomeAdapter = new LiveHomeAdapter(mContext);
            girdView.setAdapter(liveHomeAdapter);

        }
    }
}
