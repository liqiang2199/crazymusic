package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.activity.shop.GoodsInfoActivity;
import com.music.ui.entity.shop.GoodsClassesListEntity;
import com.music.utils.XImageLoadUtils;

/**
 * 分类商品
 */
public class GoodsClassesListHolder extends IViewHolder {

    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ListHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_shop_classfiy_product;
    }

    private class ListHolder extends XViewHolder<GoodsClassesListEntity> implements View.OnClickListener {
        protected ImageView imageGoods;
        protected TextView tvGoodsName;
        protected TextView tvCostPrice;
        protected TextView tvOldPrice;

        public ListHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            imageGoods = (ImageView) rootView.findViewById(R.id.image_goods);
            tvGoodsName = (TextView) rootView.findViewById(R.id.tv_goodsName);
            tvCostPrice = (TextView) rootView.findViewById(R.id.tv_cost_price);
            tvOldPrice = (TextView) rootView.findViewById(R.id.tv_old_price);
            rootView.setOnClickListener(this);
        }

        @Override
        protected void onBindData(GoodsClassesListEntity itemData) {
            XImageLoadUtils.loadFitImage(mContext, itemData.getProduct_img(), imageGoods);
            tvGoodsName.setText(itemData.getName());
            tvCostPrice.setText(itemData.getCost_price());
            tvOldPrice.setText("已售" + itemData.getBuy_count() + "件");
            itemView.setTag(R.id.shop_id, itemData.getId());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, GoodsInfoActivity.class);
            intent.putExtra("id", String.valueOf(view.getTag(R.id.shop_id)));
            mContext.startActivity(intent);
        }
    }
}
