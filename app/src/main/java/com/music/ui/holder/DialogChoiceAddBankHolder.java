package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.model.busbeen.AddBankChoiceBus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by empty cup on 2018/1/29.
 *  银行卡列表选择
 */

public class DialogChoiceAddBankHolder extends IViewHolder{
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view,adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_center_textview;
    }

    class ViewHolder extends XViewHolder{
        private LinearLayout linear_add_bank;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            linear_add_bank = rootView.findViewById(R.id.linear_add_bank);
            linear_add_bank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new AddBankChoiceBus(1));
                }
            });
        }

        @Override
        protected void onBindData(Object itemData) {

        }
    }
}
