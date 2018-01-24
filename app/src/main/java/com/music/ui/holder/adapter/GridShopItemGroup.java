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

public class GridShopItemGroup extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<GoodsAllEntity.DataBean.ChildListBean> childList;
    private ImageView mShopImg;

    public GridShopItemGroup(Context context, GoodsAllEntity.DataBean itemData) {
        this.mContext = context;
        childList = itemData.getChildList();
    }

    @Override
    public int getCount() {
        return childList.size();
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
            ((TextView) convertView.findViewById(R.id.name)).setText(childList.get(position).getType_name());
        }
        mShopImg = convertView.findViewById(R.id.shop_img);
        XImageLoadUtils.loadCenterImage(mContext, childList.get(position).getType_pic(), mShopImg);
        convertView.setTag(R.id.position, position);
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, GoodsSearchActivity.class);
        intent.putExtra("tid", childList.get((Integer) view.getTag(R.id.position)).getId());
        intent.putExtra("typeName", childList.get((Integer) view.getTag(R.id.position)).getType_name());
        mContext.startActivity(intent);
    }
}
