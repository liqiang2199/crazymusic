package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.model.jsonbeen.CouponsListBeen;

import java.util.ArrayList;

/**
 * Created by empty cup on 2018/1/26.
 */

public class CouponsHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view,adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_coupons;
    }

    private class ViewHolder extends XViewHolder<CouponsListBeen.CouponsBeen>{

        private TextView coupons_Name;
        private TextView coupons_Time;
        private TextView coupons_price;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            coupons_Name = (TextView)rootView.findViewById(R.id.coupons_Name);
            coupons_Time = (TextView)rootView.findViewById(R.id.coupons_Time);
            coupons_price = (TextView)rootView.findViewById(R.id.coupons_price);
        }

        @Override
        protected void onBindData(CouponsListBeen.CouponsBeen itemData) {
            if (itemData != null){

                coupons_Name.setText(itemData.getName());
                coupons_Time.setText(itemData.getExpire_date());
                //1 未使用 0 已使用 -1 过期
                if (itemData.getState() .equals("1") ){
                    coupons_price.setText("过期时间："+itemData.getPayment());
                }

                if (itemData.getState() .equals("0") )
                {
                    coupons_price.setText("已使用");
                }
                if (itemData.getState() .equals("-1") )
                {
                    coupons_price.setText("已过期");
                }
            }
        }


    }

}
