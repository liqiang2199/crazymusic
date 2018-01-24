package com.music.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 图片浏览实体类
 */

public class ImageEntity implements Parcelable {
    /**
     * 原图
     */
    public String image;

    /**
     * 缩略图
     */
    public String thumbnail;

    /**
     * 缩略图宽度
     */
    public int width;

    /**
     * 缩略图高度
     */
    public int height;

    public ImageEntity() {

    }

    protected ImageEntity(Parcel in) {
        image = in.readString();
        thumbnail = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Parcelable.Creator<ImageEntity> CREATOR = new Parcelable.Creator<ImageEntity>() {
        @Override
        public ImageEntity createFromParcel(Parcel in) {
            return new ImageEntity(in);
        }

        @Override
        public ImageEntity[] newArray(int size) {
            return new ImageEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(thumbnail);
        dest.writeInt(width);
        dest.writeInt(height);
    }
}
