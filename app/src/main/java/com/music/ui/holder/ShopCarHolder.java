package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.entity.CommunityListEntity;
import com.music.ui.entity.shop.ShopEntity;
import com.music.utils.XImageLoadUtils;

/**
 * 购物车
 */
public class ShopCarHolder extends IViewHolder {

    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_shop_car;
    }

    private class ViewHolder extends XViewHolder<ShopEntity.DataBean> {
        private ImageView ivPic;
        private TextView tvTitle;
        private TextView tvDec;
        private TextView tvPrice;
        private TextView tvNum;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            ivPic = rootView.findViewById(R.id.iv_pic);
            tvTitle = rootView.findViewById(R.id.tv_title);
            tvDec = rootView.findViewById(R.id.tv_dec);
            tvPrice = rootView.findViewById(R.id.tv_price);
            tvNum = rootView.findViewById(R.id.tv_num);
        }

        @Override
        protected void onBindData(ShopEntity.DataBean itemData) {
            XImageLoadUtils.loadCenterImage(mContext, itemData.getProduct_img(), ivPic);
            tvTitle.setText(itemData.getProduct_name());
            tvDec.setText("图片描述");
            tvPrice.setText(String.valueOf(itemData.getPayment()));
            tvNum.setText(String.valueOf(itemData.getNumber()));
        }
    }
}

