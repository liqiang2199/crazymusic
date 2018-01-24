package com.music.ui.activity.index;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bm.library.Info;
import com.music.ui.entity.ImageEntity;
import com.music.view.HackyViewPager;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;


/**
 * 图片浏览器
 * Created by cheng on 2016/6/13.
 */

public class ImagePlayActivity extends AppCompatActivity {
    private static Info mInfo;
    private HackyViewPager mViewPager;
    private TextView imageCursor;
    private ArrayList<ImageEntity> mDatas;
    private int mPosition;
    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);
    private SamplePagerAdapter samplePagerAdapter;
    private View mBg;
    private boolean isFinishing = true;
    public ProgressBar mProgressBar;
    public TextView mProgressBarText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout relativeLayout = new FrameLayout(this);
        mViewPager = new HackyViewPager(this);
        mViewPager.setOffscreenPageLimit(3);
        mBg = new View(this);

        in.setDuration(300);
        out.setDuration(300);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageCursor.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageCursor = new TextView(this);
        mBg.setBackgroundColor(0xff000000);
        relativeLayout.addView(mBg, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(mViewPager);

        mProgressBar = new ProgressBar(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        mProgressBarText = new TextView(this);
        mProgressBar.setLayoutParams(layoutParams);
        mProgressBarText.setLayoutParams(layoutParams);

        imageCursor.setGravity(Gravity.CENTER);
        imageCursor.setBackgroundColor(0x55000000);
        imageCursor.setTextColor(0xFFFFFFFF);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//此处相当于布局文件中的Android:layout_gravity属性
        lp.gravity = Gravity.BOTTOM;
        imageCursor.setLayoutParams(lp);
        relativeLayout.addView(imageCursor);

        relativeLayout.addView(mProgressBar);
        relativeLayout.addView(mProgressBarText);
        setContentView(relativeLayout);
        mDatas = getIntent().getParcelableArrayListExtra("images");

        mPosition = getIntent().getIntExtra("position", 0);
        samplePagerAdapter = new SamplePagerAdapter(mPosition, mInfo, mDatas, imageCursor, mProgressBarText, mProgressBar);
        imageCursor.setAnimation(in);
        mBg.startAnimation(in);
        mViewPager.setAdapter(samplePagerAdapter);

        mViewPager.setCurrentItem(mPosition);

    }

    private class SamplePagerAdapter extends PagerAdapter {
        private final TextView mProgressBarText;
        public ProgressBar mProgressBar;
        private final int mPosition;
        private final Info mInfo;
        private final ArrayList<ImageEntity> mDatas;
        boolean isFalst = true;
        private TextView imageCursor;

        private PhotoView mPhotoView;
        ImageOptions options = new ImageOptions.Builder()
                // 是否忽略GIF格式的图片
                .setIgnoreGif(true)
                // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .build();

        public SamplePagerAdapter(int mPosition, Info info, ArrayList<ImageEntity> mDatas, TextView imageCursor, TextView mProgressBarText, ProgressBar mProgressBar) {
            this.mPosition = mPosition;
            this.mInfo = info;
            this.mDatas = mDatas;
            this.imageCursor = imageCursor;
            this.mProgressBarText = mProgressBarText;
            this.mProgressBar = mProgressBar;
        }

        @Override
        public int getCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView view = new PhotoView(container.getContext());
            view.enable();
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mPhotoView = (PhotoView) object;
            loadIndex(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public PhotoView getmPhotoView() {
            return mPhotoView;
        }

        private void loadIndex(final int index) {
            imageCursor.setText(1 + index + "/" + mDatas.size());
            String url = mDatas.get(index).image;

            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBarText.setVisibility(View.VISIBLE);
            Callback.ProgressCallback progressCallback = new Callback.ProgressCallback<Drawable>() {
                @Override
                public void onWaiting() {
                }

                @Override
                public void onStarted() {
                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    final int p = (int) (100.0 * current / total);
                    LogUtil.d(p + "%");
                    mProgressBarText.setText(p + "%");
                    mProgressBar.setProgress(p);
                }

                @Override
                public void onSuccess(Drawable result) {
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                    mProgressBarText.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                }
            };
            x.image().bind(mPhotoView, url, options, progressCallback);
            mPhotoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPhotoView.animaTo(mInfo, new Runnable() {
                        @Override
                        public void run() {
                            mPhotoView.setVisibility(View.GONE);
                            finish();
                        }
                    });
                }
            });
        }
    }

    public static void show(ImageView view, ImageEntity imageEntity) {
        ArrayList<ImageEntity> imageEntities = new ArrayList<ImageEntity>();
        imageEntities.add(imageEntity);
        show(view, imageEntities, 0);
    }

    public static void show(ImageView view, ArrayList<ImageEntity> imageEntities, int position) {
        Activity context = (Activity) view.getContext();
        Intent intent = new Intent(context, ImagePlayActivity.class);
        intent.putParcelableArrayListExtra("images", imageEntities);
        intent.putExtra("position", position);
        if (view != null) {
            mInfo = PhotoView.getImageViewInfo(view);
        }
        context.startActivity(intent);
        context.overridePendingTransition(0, 0);
    }

    public static void show(Activity context, ImageView view, ArrayList<ImageEntity> imageEntities, int position) {
        Intent intent = new Intent(context, ImagePlayActivity.class);
        intent.putParcelableArrayListExtra("images", imageEntities);
        intent.putExtra("position", position);
        if (view != null) {
            mInfo = PhotoView.getImageViewInfo(view);
        }
        context.startActivity(intent);
        context.overridePendingTransition(0, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
        mInfo = null;
    }

    @Override
    public void onBackPressed() {
        out();
    }

    private void out() {
        if (isFinishing) {
            isFinishing = false;
            imageCursor.setAnimation(out);
            mBg.startAnimation(out);
            samplePagerAdapter.getmPhotoView().disenable();
            mViewPager.setScanScroll(false);
            samplePagerAdapter.getmPhotoView().animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    mViewPager.setVisibility(View.GONE);
                }
            });
        }
    }


}
