package com.music.ui.holder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.music.R;
import com.music.view.SelectableRoundedImageView;

/**
 * 直播
 */
public class LiveHomeAdapter extends BaseAdapter {

    private Context mContext;
    private int imgs[] = {R.mipmap.bg_home1,R.mipmap.bg_home2,R.mipmap.bg_home3,R.mipmap.bg_home4};

    public LiveHomeAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_index_live, null);
            viewHolder = new ViewHolder();
            viewHolder.image_goods = convertView.findViewById(R.id.image_goods);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.image_goods.setImageResource(imgs[position]);
        return convertView;
    }
    class ViewHolder{
        private SelectableRoundedImageView image_goods;
    }
}
