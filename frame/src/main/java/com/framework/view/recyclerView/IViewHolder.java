package com.framework.view.recyclerView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Adapter 中 onCreateViewHolder调用的构造Holder  的抽象类
 */

public abstract class IViewHolder {
    protected RecyclerView.Adapter adapter;
    protected Context mContext;

    /**
     * Adapter 中 onCreateViewHolder 调用方法
     *
     * @param parent  父viewGroup
     * @param adapter
     * @return
     */
    public XViewHolder create(ViewGroup parent, RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return create(view, adapter);
    }
    //XViewHolder  返回这个对象
    protected abstract XViewHolder create(View view, RecyclerView.Adapter adapter);

    public abstract
    @LayoutRes
    int getLayout();
}
