package com.music.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.activity.shop.GoodsInfoActivity;
import com.music.ui.entity.shop.ShopIndexEntity;
import com.music.utils.XImageLoadUtils;

import java.util.List;

/**
 * 商城列表
 * item_shop_list
 */
public class ShopListHolder extends BaseAdapter {

    private Context context;
    private List<ShopIndexEntity.ProductDatasBean> list;

    public ShopListHolder(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public ShopIndexEntity.ProductDatasBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop_list, null);
        }
        final ShopIndexEntity.ProductDatasBean productDatasBean = list.get(position);

        ImageView imageGoods = XBaseViewHolder.get(convertView, R.id.image_goods);
        TextView tvGoodsName = XBaseViewHolder.get(convertView, R.id.tv_goodsName);
        RatingBar mRating = XBaseViewHolder.get(convertView, R.id.mRating);
        TextView tvCostPrice = XBaseViewHolder.get(convertView, R.id.tv_cost_price);
        TextView tvOldPrice = XBaseViewHolder.get(convertView, R.id.tv_old_price);

        XImageLoadUtils.loadFitImage(context, productDatasBean.getProduct_img(), imageGoods);
        tvGoodsName.setText(productDatasBean.getName());
        mRating.setRating(productDatasBean.getRecommend_star());
        tvCostPrice.setText("￥" + productDatasBean.getNow_price());
        tvOldPrice.setText("￥" + productDatasBean.getHigst_price());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //商品详情
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("id", productDatasBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setList(List<ShopIndexEntity.ProductDatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
