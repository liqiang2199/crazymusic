package com.music.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;

/**
 * 课程
 */
public class CoursesListHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ListHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_courses;
    }

    class ListHolder extends XViewHolder {
        public ListHolder(View itemView, RecyclerView.Adapter adapter) {
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
