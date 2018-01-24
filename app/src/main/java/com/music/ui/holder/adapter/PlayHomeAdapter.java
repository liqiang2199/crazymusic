package com.music.ui.holder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.music.R;

/**
 * 玩音乐
 */
public class PlayHomeAdapter extends BaseAdapter {

    private Context mContext;

    public PlayHomeAdapter(Context mContext) {
        this.mContext = mContext;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_index_live, null);
        }
        return convertView;
    }
}
