package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;

/**
 * Created by Administrator on 2018/3/4.
 * 首页教师排行榜
 */

public class IndexTeacherHolder extends IViewHolder{
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view,adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_index_teacher;
    }

    class ViewHolder extends XViewHolder{
        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {

        }

        @Override
        protected void onBindData(Object itemData) {

        }
    }
}
