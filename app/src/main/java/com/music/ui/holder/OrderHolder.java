package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.entity.shop.OrderListEntity;
import com.music.utils.XImageLoadUtils;

/**
 * 订单
 */
public class OrderHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }
    @Override
    public int getLayout() {
        return R.layout.item_order;
    }


    private class ViewHolder extends XViewHolder<OrderListEntity> {
        protected TextView tvOrderNum;
        protected TextView tvState;
        protected ImageView ivPic;
        protected TextView tvTitle;
        protected TextView tvDec;
        protected TextView tvPrice, tvTatalPrice;
        protected TextView tvGoodsNum;
        protected TextView tvOrderTime;
        protected ImageView ivDel;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            tvOrderNum = (TextView) rootView.findViewById(R.id.tv_order_num);
            tvState = (TextView) rootView.findViewById(R.id.tv_state);
            ivPic = (ImageView) rootView.findViewById(R.id.iv_pic);
            tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            tvDec = (TextView) rootView.findViewById(R.id.tv_dec);
            tvPrice = (TextView) rootView.findViewById(R.id.tv_price);
            tvGoodsNum = (TextView) rootView.findViewById(R.id.tv_goods_num);
            tvOrderTime = (TextView) rootView.findViewById(R.id.tv_order_time);
            ivDel = (ImageView) rootView.findViewById(R.id.iv_del);
            tvTatalPrice = rootView.findViewById(R.id.tv_total_price);
        }

        @Override
        protected void onBindData(OrderListEntity itemData) {
            XImageLoadUtils.loadFitImage(mContext, itemData.getProduct_img(), ivPic);
            tvTitle.setText(itemData.getProduct_title());
            tvTatalPrice.setText("合计： ￥" + itemData.getPayment());
            tvOrderTime.setText(itemData.getCreate_date());

            //1 未付款 2 已付款 3 未发货 4 已发货 5 交易关闭 6交易完成 7退款中 8退款完成 9拒绝退款   0就是所有订单
//            switch (itemData.getStatus()) {
//                case "1":
//                    //付款
//                    break;
//                case "3":
//                    //发货
//                    break;
//                case "1":
//                    //
//                    break;
//                case "1":
//                    break;
//                case "1":
//                    break;
//                case "1":
//                    break;
//                case "1":
//                    break;
//                case "1":
//                    break;
//            }
        }
    }
}
