package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.model.jsonbeen.BankListBeen;

/**
 * Created by Administrator on 2018/1/27.
 * 加载银行卡列表
 */

public class BankListHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view,adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_bank_card_;
    }
    class ViewHolder extends XViewHolder<BankListBeen.BankList> {

        private TextView tv_name;
        private TextView tv_bank_name;
        private TextView tv_bank_num;
        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            tv_name = rootView.findViewById(R.id.tv_name);
            tv_bank_name = rootView.findViewById(R.id.tv_bank_name);
            tv_bank_num = rootView.findViewById(R.id.tv_bank_num);
        }

        @Override
        protected void onBindData(BankListBeen.BankList itemData) {
            tv_name.setText(itemData.getAccount_name());
            tv_bank_num.setText(itemData.getAccount_num());
        }


    }
}
