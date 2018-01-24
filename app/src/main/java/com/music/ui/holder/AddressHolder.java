package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.framework.view.XBaseViewHolder;
import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;

/**
 * 地址
 */
public class AddressHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new AddHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_address;
    }

    class AddHolder extends XViewHolder {
        protected TextView tvName;//姓名
        protected TextView tvMobile;//电话号码
        protected TextView tvIsMoren;//是否默认
        protected TextView tvAddress;//地址

        public AddHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            tvName = (TextView) rootView.findViewById(R.id.tv_name);
            tvMobile = (TextView) rootView.findViewById(R.id.tv_mobile);
            tvIsMoren = (TextView) rootView.findViewById(R.id.tv_isMoren);
            tvAddress = (TextView) rootView.findViewById(R.id.tv_address);
        }

        @Override
        protected void onBindData(Object itemData) {

        }
    }
}