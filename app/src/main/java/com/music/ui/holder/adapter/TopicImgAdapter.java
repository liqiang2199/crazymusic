package com.music.ui.holder.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.activity.index.ImagePlayActivity;
import com.music.ui.entity.ImageEntity;
import com.music.utils.XImageLoadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片浏览适配器
 */
public class TopicImgAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public TopicImgAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_img, null);
        }
        ImageView img = XBaseViewHolder.get(convertView, R.id.iv_pic);
        XImageLoadUtils.loadCenterImage(context, list.get(position).toString(), img);

        //图片浏览
        img.setTag(position);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                ArrayList<ImageEntity> imageEntitys = new ArrayList<ImageEntity>();
                for (int i = 0; i < list.size(); i++) {
                    ImageEntity imageEntity = new ImageEntity();
                    imageEntity.image = list.get(i).toString();
                    imageEntitys.add(imageEntity);
                }
                ImagePlayActivity.show((Activity) context, imageView, imageEntitys, (int) v.getTag());
            }
        });
        return convertView;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}