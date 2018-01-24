package com.music.ui.holder.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.music.R;
import com.music.callback.CardAdapter;
import com.music.ui.entity.CardItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private Context mContext;

    public CardPagerAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        mContext = context;
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_card, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardview);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view) {
        ImageView ivCard = (ImageView) view.findViewById(R.id.iv_icon);
        //优先加载网络图片
        if (!TextUtils.isEmpty(item.getImgUrl())){
            Glide.with(mContext).load(item.getImgUrl()).into(ivCard);
        }else {
            Glide.with(mContext).load(item.getImgResId()).into(ivCard);
        }
    }
}
