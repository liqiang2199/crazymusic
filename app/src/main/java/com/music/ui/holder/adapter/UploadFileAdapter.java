package com.music.ui.holder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.entity.UploadEntity;
import com.music.utils.XImageLoadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 多图展示适配器
 */
public class UploadFileAdapter extends BaseAdapter {

    private Context mContext;
    private List<UploadEntity> pathsList = new ArrayList<UploadEntity>();
    private boolean review = false;
    public boolean isdel = false;

    public UploadFileAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPath(List<UploadEntity> path) {
        this.pathsList = path;
        notifyDataSetChanged();
    }

    public void addPath(UploadEntity path) {
        if (this.pathsList == null) {
            this.pathsList = new ArrayList<UploadEntity>();
        }
        this.pathsList.add(path);
    }

    public void setReview(boolean review) {
        this.review = review;
    }

    public List<UploadEntity> getPath() {
        return pathsList;
    }

    @Override
    public int getCount() {
        if (review) {
            return pathsList == null ? 0 : pathsList.size() + 0;
        } else {
            return pathsList == null ? 1 : pathsList.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_uploadfile, parent, false);
        }
        ImageView item_grida_image = XBaseViewHolder.get(convertView, R.id.item_grida_image);
        ImageView iv_del = XBaseViewHolder.get(convertView, R.id.iv_del);
        //是删除， 且在范围内
        if (pathsList != null && position != pathsList.size()) {
            iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pathsList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        if (pathsList == null || position == pathsList.size()) {
            item_grida_image.setImageResource(R.mipmap.icon_tianjia);
            iv_del.setVisibility(View.GONE);
        } else {
            if (pathsList.get(position).path.startsWith("http")) {
                XImageLoadUtils.loadFitImage(mContext, pathsList.get(position).path, item_grida_image);
            } else {
                XImageLoadUtils.loadFitImage(mContext, "file://" + pathsList.get(position).path, item_grida_image);
            }
            iv_del.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
