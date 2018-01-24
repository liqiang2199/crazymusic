package com.music.ui.entity;

/**
 */
public class CardItem {

    private int imgResId;// 本地资源图片
    private String imgUrl;//网络加载图片URl

    public CardItem(int imgResId, String imgUrl) {
        this.imgResId = imgResId;
        this.imgUrl = imgUrl;
    }

    public int getImgResId() {
        return imgResId;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
