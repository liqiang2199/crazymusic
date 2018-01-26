package com.framework.view.recyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 自定义RecyclerView
 */
public class XRecyclerView extends RefreshLayout {

    protected RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layout;
    protected XRecyclerViewAdapter mXRecyclerViewAdapter;
    private int firstItemPosition;
    private int lastItemPosition;
    /**
     * 设置刷新和加载
     */
    private DefineRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;

    public XRecyclerView(Context context) {
        super(context);
        init();
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setRefreshLayout();
        mRecyclerView = new RecyclerView(getContext());
        addView(mRecyclerView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layout);
        // 绑定的Adapter
        mXRecyclerViewAdapter = new XRecyclerViewAdapter();
        mRecyclerView.setAdapter(mXRecyclerViewAdapter);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 1;
        mRecyclerView.setLayoutParams(layoutParams);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public int getFirstItemPosition() {
        return firstItemPosition;
    }

    public int getLastItemPosition() {
        return lastItemPosition;
    }

    /**
     * 设置 BGARefreshLayout刷新和加载
     */
    private void setRefreshLayout() {
        mDefineBAGRefreshWithLoadView = new DefineRefreshWithLoadView(getContext(), true, true);
        //设置刷新样式
        setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
    }

    public DefineRefreshWithLoadView getRefreshViewHolder() {
        return mDefineBAGRefreshWithLoadView;
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return false;
    }

    public void setLayout(RecyclerView.LayoutManager layout) {
        this.layout = layout;
    }

    public RecyclerView.LayoutManager getLayout() {
        return layout;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public XRecyclerViewAdapter getAdapter() {
        return (XRecyclerViewAdapter) mRecyclerView.getAdapter();
    }

    /**
     * 关闭
     */
    public void setPullLoadMoreCompleted() {
        endRefreshing();
        endLoadingMore();
    }

    /**
     * 刷新结束
     */
    public void endRefresh() {
        endRefreshing();
    }

    /**
     * 加载更多结束
     */
    public void endLoading() {
        endLoadingMore();
    }

}
