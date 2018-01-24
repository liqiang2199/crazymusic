package com.music.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.framework.utils.GlideCircleTransform;
import com.framework.utils.GlideRoundTransform;
import com.music.R;

/**
 * 图片工具类
 */

public class XImageLoadUtils {


    /**
     * 加载圆角图片
     * 无默认图片
     *
     * @param context
     * @param url
     * @param imageView //转化为圆形
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView, int icon, int dp) {
        Glide.with(context).load(url).thumbnail(0.1f).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).
                transform(new GlideRoundTransform(context, dp)).into(imageView);
    }

    /**
     * 加载圆形图片
     * 无默认图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).thumbnail(0.1f).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).
                transform(new GlideCircleTransform(context)).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param errorResource 加载失败的图片
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int placeholder, int errorResource) {
        Glide.with(context).load(url).thumbnail(0.1f).centerCrop().placeholder(errorResource).
                error(errorResource).diskCacheStrategy(DiskCacheStrategy.ALL).
                transform(new GlideCircleTransform(context)).into(imageView);
    }

    /**
     * 加载本地圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadLocalCircleImage(Context context, int url, ImageView imageView) {
        Glide.with(context).load(url).thumbnail(0.1f).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).
                transform(new GlideCircleTransform(context)).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCenterImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).thumbnail(0.1f).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param errorResource 加载失败的图片
     */
    public static void loadCenterImage(Context context, String url, ImageView imageView, int placeholder, int errorResource) {
        Glide.with(context).load(url).thumbnail(0.1f).centerCrop().placeholder(errorResource).
                error(errorResource).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadFitImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).thumbnail(0.1f).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.bg_img_def).
                error(R.mipmap.bg_img_error).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholder   预加载图片
     * @param errorResource 加载失败的图片
     */
    public static void loadFitImage(Context context, String url, ImageView imageView, Drawable placeholder, Drawable errorResource) {
        Glide.with(context).load(url).thumbnail(0.1f).fitCenter().placeholder(errorResource).
                error(errorResource).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
}
