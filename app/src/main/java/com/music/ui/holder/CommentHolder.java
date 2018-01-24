package com.music.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.utils.XToastUtil;
import com.framework.view.XGridViewForScrollView;
import com.framework.view.XListViewForScrollView;
import com.framework.view.recyclerView.IViewHolder;
import com.framework.view.recyclerView.XViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.index.CommunityDetailActivity;
import com.music.ui.activity.index.ReplyActivity;
import com.music.ui.entity.CommunityListEntity;
import com.music.ui.entity.XResult;
import com.music.ui.holder.adapter.TopicImgAdapter;
import com.music.utils.XImageLoadUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 社区
 */
public class CommentHolder extends IViewHolder {
    @Override
    protected XViewHolder create(View view, RecyclerView.Adapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.item_community;
    }

    private class ViewHolder extends XViewHolder<CommunityListEntity> {
        protected ImageView ivHead;
        protected TextView tvTitle;
        protected ImageView tvFrom;
        protected TextView tvContent;
        protected XGridViewForScrollView gridView;
        protected TextView tvZan;
        protected TextView tvPing;
        protected XListViewForScrollView listHf;
        private boolean isZan;
        private int zanNum;

        public ViewHolder(View itemView, RecyclerView.Adapter adapter) {
            super(itemView, adapter);
        }

        @Override
        protected void initView(View rootView) {
            ivHead = (ImageView) rootView.findViewById(R.id.iv_head);
            tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            tvFrom = (ImageView) rootView.findViewById(R.id.tv_from);
            tvContent = (TextView) rootView.findViewById(R.id.tv_content);
            gridView = (XGridViewForScrollView) rootView.findViewById(R.id.gridView);
            tvZan = (TextView) rootView.findViewById(R.id.tv_zan);
            tvPing = (TextView) rootView.findViewById(R.id.tv_ping);
            listHf = (XListViewForScrollView) rootView.findViewById(R.id.list_hf);
            gridView.setAdapter(new TopicImgAdapter(mContext));
        }

        @Override
        protected void onBindData(final CommunityListEntity itemData) {
            XImageLoadUtils.loadCircleImage(mContext, itemData.getHead_img(), ivHead);
            tvTitle.setText(itemData.getNick_name());
            tvContent.setText(itemData.getContent());
            //图片展示
            TopicImgAdapter topicImgAdapter = new TopicImgAdapter(mContext);
            gridView.setAdapter(topicImgAdapter);
            topicImgAdapter.setList(itemData.getImgList());
            tvZan.setText(itemData.getPerfect_count() + "");
            tvPing.setText(itemData.getEvaluate_count() + "");

            //展示是否点赞
            if (itemData.isIs_perfect()) {
                tvZan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.ic_zan_true), null, null, null);
            } else {
                tvZan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.ic_zan), null, null, null);
            }
            //点赞
            isZan = itemData.isIs_perfect();
            zanNum = itemData.getPerfect_count();
            tvZan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isZan) {
                        HttpRequesParams params = new HttpRequesParams(ConstHost.COMMUNITY_PREFECT);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("community_id", itemData.getId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        params.setAsJsonContent(true);
                        params.setBodyContent(jsonObject.toString());
                        HttpUtils.post(mContext, false, params, new HttpResponseCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                Type type = new TypeToken<XResult>() {
                                }.getType();
                                XResult resultData = gson.fromJson(result, type);
                                if (resultData.code == 0) {
                                    //点赞
                                    tvZan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.ic_zan_true), null, null, null);
                                    zanNum++;
                                    tvZan.setText(zanNum + "");
                                    isZan = false;
                                } else {
                                    XToastUtil.showToast(mContext, resultData.getMsg());
                                }
                            }

                            @Override
                            public void onFailed(String failedMsg) {
                            }

                            @Override
                            public void onFinished() {
                            }
                        });
                    } else {
                        HttpRequesParams params = new HttpRequesParams(ConstHost.COMMUNITY_CANCEL_PREFECT);
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("community_id", itemData.getId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        params.setAsJsonContent(true);
                        params.setBodyContent(jsonObject.toString());
                        HttpUtils.post(mContext, false, params, new HttpResponseCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                Type type = new TypeToken<XResult>() {
                                }.getType();
                                XResult resultData = gson.fromJson(result, type);
                                if (resultData.code == 0) {
                                    //取消点赞
                                    tvZan.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.ic_zan), null, null, null);
                                    zanNum--;
                                    tvZan.setText(zanNum + "");
                                    isZan = true;
                                } else {
                                    XToastUtil.showToast(mContext, resultData.getMsg());
                                }
                            }
                            @Override
                            public void onFinished() {
                            }
                            @Override
                            public void onFailed(String failedMsg) {
                            }
                        });
                    }
                }
            });
            tvPing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回复
                    Intent intent = new Intent(mContext, ReplyActivity.class);
                    intent.putExtra("cid", itemData.getId() + "");
                    mContext.startActivity(intent);
                }
            });
            //社区详情
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(itemData);
                    mContext.startActivity(new Intent(mContext, CommunityDetailActivity.class));
                }
            });
        }
    }
}
