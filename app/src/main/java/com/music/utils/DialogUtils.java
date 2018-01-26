package com.music.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.framework.utils.carme.CramUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.index.TipsPostActivity;
import com.music.ui.entity.XResult;
import com.music.ui.entity.shop.GoodsClassesEntity;
import com.music.ui.holder.adapter.GoodsClassesAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 对话框工具类
 */
public class DialogUtils {

    /**
     * 弹出发帖/直播窗口
     *
     * @param context
     */
    public static void showPost(final Context context, final Activity activity) {
        final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View viewPost = LayoutInflater.from(context).inflate(R.layout.popup_relaese, null);
        popupWindow.setContentView(viewPost);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(LayoutInflater.from(context).inflate(R.layout.activity_main, null),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.7f;
        activity.getWindow().setAttributes(lp);

        popupWindow.setAnimationStyle(R.style.pop_style_out);

        LinearLayout btBuy = (LinearLayout) viewPost.findViewById(R.id.bt_buy);
        LinearLayout btnRelease = (LinearLayout) viewPost.findViewById(R.id.btn_release);
        View btView = (View) viewPost.findViewById(R.id.bt_view);
        ImageView tabFb = (ImageView) viewPost.findViewById(R.id.tab_fb);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });

        btnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发贴
                context.startActivity(new Intent(context, TipsPostActivity.class));
//                context.startActivity(new Intent(context, LuckDrawActivity.class));

            }
        });
        tabFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 拍照选择
     *
     * @param context
     */
    public static void showPhotoDialog(final Context context, final CramUtils cramUtils,int w,int h) {
        final Dialog dlg = new Dialog(context, R.style.DialogThemeNoTitle);
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.dialog_photo);
        window.setWindowAnimations(R.style.mystyle);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = w-UtilsTools.dip2px(context,30);
        window.setAttributes(params);

        Button photograph_btn = (Button) window.findViewById(R.id.photograph_btn);
        Button select_photo_btn = (Button) window.findViewById(R.id.select_photo_btn);
        //拍照选择
        photograph_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cramUtils.camera();
                dlg.dismiss();
            }
        });
        // 从相册中选择照片
        select_photo_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cramUtils.album();
                dlg.dismiss();
            }
        });
        // 关闭alert对话框架
        Button cancel = (Button) window.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
    }

    /**
     * 商品分类
     *
     * @param context
     */
    public static void showGoodsClasses(final Context context, String typeId, final OnResult onResult) {
        final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View viewPost = LayoutInflater.from(context).inflate(R.layout.popup_goods_classes, null);
        popupWindow.setContentView(viewPost);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(LayoutInflater.from(context).inflate(R.layout.activity_main, null),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        final GridView gridView = (GridView) viewPost.findViewById(R.id.recycle_goods_calsses);
        final GoodsClassesAdapter goodsClassesAdapter = new GoodsClassesAdapter(context);
        gridView.setAdapter(goodsClassesAdapter);
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_CLASSES);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("typeId", typeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setBodyContent(jsonObject.toString());
        httpRequesParams.setAsJsonContent(true);
        HttpUtils.post(context, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---111", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<XResult<List<GoodsClassesEntity>>>() {
                }.getType();
                XResult<List<GoodsClassesEntity>> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
                        goodsClassesAdapter.setEntityList(resultData.getData());
                    }
                }
            }

            @Override
            public void onFailed(String failedMsg) {
            }

            @Override
            public void onFinished() {
            }
        });
        //点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goodsClassesAdapter.changeState(position);
                onResult.onSuccess(goodsClassesAdapter.getItem(position).getType_name(), goodsClassesAdapter.getItem(position).getId());
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 回调
     */
    public interface OnResult {
        void onSuccess(String name, String id);
    }
}
