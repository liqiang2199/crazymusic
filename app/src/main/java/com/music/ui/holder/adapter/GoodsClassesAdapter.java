package com.music.ui.holder.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
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
 * 商品分类适配器
 */
public class GoodsClassesAdapter extends BaseAdapter {

    private Context context;
    private List<GoodsClassesEntity> entityList;
    private int selectorPosition = -1;

    public GoodsClassesAdapter(Context context) {
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
        //如果当前的position等于传过来点击的position,就去改变他的状态
        if (selectorPosition == position) {
            tvTitle.setBackgroundResource(R.drawable.bg_base_40bar);
            tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            //其他的恢复原来的状态
            tvTitle.setBackgroundResource(R.drawable.bg_gray_holl_fillet_frame40);
            tvTitle.setTextColor(Color.parseColor("#999999"));
        }
        return convertView;
    }

    public void setEntityList(List<GoodsClassesEntity> entityList) {
        this.entityList = entityList;
        notifyDataSetChanged();
    }

    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();
    }
}
