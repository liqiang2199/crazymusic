package com.music.ui.holder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.entity.shop.GoodsClassesEntity;

import java.util.List;

/**
 * 确认订单页面--商品展示
 */
public class GoodsSureOrderAdapter extends BaseAdapter {

    private Context context;
    private List<GoodsClassesEntity> entityList;

    public GoodsSureOrderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return entityList == null ? 0 : entityList.size();
    }

    @Override
    public GoodsClassesEntity getItem(int position) {
        return entityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_classes, parent, false);
        }
        GoodsClassesEntity entity = entityList.get(position);
        TextView tvTitle = XBaseViewHolder.get(convertView, R.id.tv_title);
        if (entity != null) {
            tvTitle.setText(entity.getType_name());
        }
        return convertView;
    }

    public void setEntityList(List<GoodsClassesEntity> entityList) {
        this.entityList = entityList;
        notifyDataSetChanged();
    }
}
