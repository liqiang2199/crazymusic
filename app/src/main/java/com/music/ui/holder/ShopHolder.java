package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.framework.view.XGridViewForScrollView;
import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.entity.shop.GoodsAllEntity;
import com.music.ui.holder.adapter.GridShopItemGroup;

/**
 * Created by feq on 2017/12/24.
 */

public class ShopHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_shop_group;
    }

    private class ViewHolder extends XViewHolder<GoodsAllEntity.DataBean> {
        private XGridViewForScrollView girdView;
        private TextView title;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            girdView = (XGridViewForScrollView) itemView.findViewById(R.id.girdView);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        protected void onBindData(GoodsAllEntity.DataBean itemData) {
            GridShopItemGroup gridShopItemGroup = new GridShopItemGroup(mContext, itemData);
            title.setText(itemData.getType_name());
            girdView.setAdapter(gridShopItemGroup);
        }
    }
}
