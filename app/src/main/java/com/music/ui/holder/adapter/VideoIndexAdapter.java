package com.music.ui.holder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.music.R;

/**
 * Created by Administrator on 2018/3/13.
 * 视频
 */

public class VideoIndexAdapter extends BaseAdapter {
    private Context context;
    public VideoIndexAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 5;
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_index_video,null);
        }
        return convertView;
    }
}
