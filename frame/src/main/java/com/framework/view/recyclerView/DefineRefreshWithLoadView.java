package com.framework.view.recyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.R;


/**
 * 描述:下拉刷新风格
 */
public class DefineRefreshWithLoadView extends XRefreshViewHolder {
    private TextView mHeaderStatusTv;
    private ImageView imgHeadResh;
    private AnimationDrawable animationDrawable;
    private String mPullDownRefreshText;
    private String mReleaseRefreshText;
    private String mRefreshingText;
    private boolean mIsRefreshEnabled = true;
    /**
     * 判断是否使用上拉加载
     */
    private boolean mIsLoadingMoreEnabled = true;
    private Context context;

    /**
     * 是否显示下拉刷新--和--加载更多
     *
     * @param context
     * @param isLoadingMoreEnabled
     * @param isRefreshEnabled
     */
    public DefineRefreshWithLoadView(Context context, boolean isLoadingMoreEnabled, boolean isRefreshEnabled) {
        super(context, isLoadingMoreEnabled);
        this.context = context;
        this.mIsLoadingMoreEnabled = isLoadingMoreEnabled;
        this.mIsRefreshEnabled = isRefreshEnabled;
        setPullDistanceScale(3.0f);
    }

    /**
     * 设置下拉显示文字
     */
    public void setPullDownRefreshText(String pullDownRefreshText) {
        this.mPullDownRefreshText = pullDownRefreshText;
    }

    /**
     * 设置释放显示文字
     */
    public void setReleaseRefreshText(String releaseRefreshText) {
        this.mReleaseRefreshText = releaseRefreshText;
    }

    /**
     * 设置正在刷新显示文字
     */
    public void setRefreshingText(String refreshingText) {
        this.mRefreshingText = refreshingText;
    }

    /**
     * 定义刷新
     */
    public View getRefreshHeaderView() {
        if (this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.header_refresh, (ViewGroup) null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
            this.mHeaderStatusTv = (TextView) this.mRefreshHeaderView.findViewById(R.id.tv_normal_refresh_header_status);
            this.mHeaderStatusTv.setText(this.mPullDownRefreshText);

        }
        //刷新不可用
        if (!mIsRefreshEnabled) {
            return null;
        }
        return mRefreshHeaderView;
    }

    @Override
    public View getLoadMoreFooterView() {
        if (!mIsLoadingMoreEnabled) {
            return null;
        }
        if (mLoadMoreFooterView == null) {
            mLoadMoreFooterView = View.inflate(mContext, R.layout.footer_loadmore_loading, null);
            mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);
            if (this.mRefreshViewBackgroundColorRes != -1) {
                this.mLoadMoreFooterView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if (this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mLoadMoreFooterView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
        }
        return mLoadMoreFooterView;
    }

    //已经开始刷新
    public void changeToRefreshing() {
        this.mHeaderStatusTv.setText(this.mRefreshingText);
    }

    //开始下拉
    public void changeToPullDown() {
        this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
    }

    //下拉到一定程度，可以刷新
    public void changeToReleaseRefresh() {
        this.mHeaderStatusTv.setText(this.mReleaseRefreshText);
    }

    //结束刷新
    public void onEndRefreshing() {
        this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
    }

    @Override
    public void handleScale(float scale, int moveYDistance) {
    }

    @Override
    public void changeToIdle() {
    }


    /**
     * 设置加载
     */
    public void updateLoadingMoreText(String text) {
        this.mFooterStatusTv.setText(text);
    }

    /**
     * 隐藏加载更多图片
     */
    public void hideLoadingMoreImg() {
        this.mLoadMoreFooterView.setVisibility(View.GONE);
    }

    /**
     * 显示加载更多图片
     */
    public void showLoadingMoreImg() {
//        this.mLoadMoreFooterView.setVisibility(View.VISIBLE);
    }

    /**
     * 是否能够刷新
     *
     * @param enabled
     */
    public void setmIsRefreshEnabled(boolean enabled) {
        mIsRefreshEnabled = enabled;
    }

    /**
     * 是否能下拉
     *
     * @param enabled
     */
    public void setIsLoadingMoreEnabled(boolean enabled) {
        mIsLoadingMoreEnabled = enabled;
    }

}