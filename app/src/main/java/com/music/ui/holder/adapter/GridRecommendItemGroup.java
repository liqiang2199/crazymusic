package com.music.ui.holder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.R;
import com.music.ui.activity.shop.GoodsSearchActivity;
import com.music.ui.entity.shop.GoodsAllEntity;
import com.music.utils.XImageLoadUtils;

import java.util.List;

/**
 * Created by feq on 2017/12/24.
 */

public class GridRecommendItemGroup extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ImageView mShopImg;

    public GridRecommendItemGroup(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shop, null);
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, GoodsSearchActivity.class);
//        intent.putExtra("tid", childList.get((Integer) view.getTag(R.id.position)).getId());
//        intent.putExtra("typeName", childList.get((Integer) view.getTag(R.id.position)).getType_name());
        mContext.startActivity(intent);
    }
}
