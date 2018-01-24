package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;

/**
 * 商品收藏
 */
public class CollectGoodsListHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new GoodsListHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_collect;
    }

    private class GoodsListHolder extends XViewHolder {
        public GoodsListHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
        }

        @Override
        protected void onBindData(Object itemData) {
        }
    }
}
