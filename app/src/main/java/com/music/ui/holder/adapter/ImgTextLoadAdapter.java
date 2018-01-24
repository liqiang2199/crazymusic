package com.music.ui.holder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.view.XBaseViewHolder;
import com.music.R;
import com.music.ui.activity.user.MyCoursesActivity;
import com.music.ui.activity.user.SetActivity;

import java.util.List;

/**
 * 通用图片/文字适配器
 */
public class ImgTextLoadAdapter extends BaseAdapter {

    private Context context;
    private int[] imgs = {R.mipmap.ic_money, R.mipmap.ic_money, R.mipmap.ic_money, R.mipmap.ic_money};
    private String[] names = {"我的课程", "我的视频", "我的社区", "视频收藏"};

    public ImgTextLoadAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_img_text, null);
        }
        ImageView ivPic = XBaseViewHolder.get(convertView, R.id.iv_pic);
        TextView tvTitle = XBaseViewHolder.get(convertView, R.id.tv_title);
        ivPic.setImageResource(imgs[position]);
        tvTitle.setText(names[position]);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        //课程
                        context.startActivity(new Intent(context,MyCoursesActivity.class));
                        break;
                    case 1:
                        //视频
                        break;
                    case 2:
                        //社区
                        break;
                    case 3:
                        //视频收藏
                        break;
                }
            }
        });
        return convertView;
    }
}
