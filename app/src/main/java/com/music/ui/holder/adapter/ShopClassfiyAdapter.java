package com.music.ui.holder.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.activity.shop.GoodsSearchActivity;
import com.music.ui.entity.shop.ShopIndexEntity;
import com.music.utils.XImageLoadUtils;

import java.util.List;

/**
 * 商城分类
 */
public class ShopClassfiyAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShopIndexEntity.TypeDatasBean> list;

    public ShopClassfiyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public ShopIndexEntity.TypeDatasBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shop_classfiy, null);
        }
        ImageView ivClass = XBaseViewHolder.get(convertView, R.id.iv_class);
        TextView tvTitle = XBaseViewHolder.get(convertView, R.id.tv_title);

        final ShopIndexEntity.TypeDatasBean typeDatasBean = list.get(position);
        if (!TextUtils.isEmpty(typeDatasBean.getType_pic())) {
            XImageLoadUtils.loadCircleImage(mContext, typeDatasBean.getType_pic(), ivClass);
        }
        tvTitle.setText(typeDatasBean.getType_name());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsSearchActivity.class);
                intent.putExtra("tid", typeDatasBean.getId());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setList(List<ShopIndexEntity.TypeDatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
