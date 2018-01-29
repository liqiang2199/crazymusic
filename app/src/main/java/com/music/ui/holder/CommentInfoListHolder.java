package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.music.R;
import com.music.ui.activity.index.ReplyActivity;
import com.music.ui.entity.CommunityDetailListEntity;
import com.music.utils.XImageLoadUtils;

/**
 * 评论详情--评论列表
 */
public class CommentInfoListHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_comment;
    }

    private class ViewHolder extends XViewHolder<CommunityDetailListEntity> {
        protected ImageView ivHead;
        protected TextView tvTitle;
        protected TextView tvFrom;
        protected TextView tvContent;
        protected TextView tv_zan;
        private LinearLayout linInfo;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            ivHead = (ImageView) rootView.findViewById(R.id.iv_head);
            tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            tvFrom = (TextView) rootView.findViewById(R.id.tv_from);
            tvContent = (TextView) rootView.findViewById(R.id.tv_content);
            linInfo = rootView.findViewById(R.id.lin_info);
            tv_zan = rootView.findViewById(R.id.tv_zan);
        }

        @Override
        protected void onBindData(final CommunityDetailListEntity itemData) {
            XImageLoadUtils.loadCircleImage(mContext, itemData.getHead_img(), ivHead,0,R.mipmap.ic_head);
            tvTitle.setText(itemData.getNick_name());
            tvContent.setText(itemData.getContent());

            //展示是否点赞
//            if (itemData.isIs_perfect()) {
//                tv_zan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.ic_zan_true), null, null, null);
//            } else {
//                tv_zan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.ic_zan), null, null, null);
//            }

            linInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回复
                    Intent intent = new Intent(mContext, ReplyActivity.class);
                    intent.putExtra("cid", itemData.getId());
                    intent.putExtra("pid", itemData.getUser_id());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
