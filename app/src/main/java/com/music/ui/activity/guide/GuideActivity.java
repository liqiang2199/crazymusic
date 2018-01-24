package com.music.ui.activity.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.music.R;
import com.music.ui.activity.MainActivity;

/**
 * 首次启动引导页
 */

public class GuideActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private ViewPager imagePager;
    private GalleryImageAdapter galleryImageAdapter;
    private int pager_num;
    int total_page;
    FrameLayout backgroundLayout;
    HorizontalScrollView background_srcollview;
    HorizontalScrollView layer_srcollview;
    int backgoundWidth;
    private View dot;
    FrameLayout.LayoutParams dotlayoutParams;
    private View dotlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        initLayout();
        backgroundLayout = (FrameLayout) findViewById(R.id.backgroundLayout);
        background_srcollview = (HorizontalScrollView) findViewById(R.id.background_srcollview);
        background_srcollview.setHorizontalScrollBarEnabled(false);


        layer_srcollview = (HorizontalScrollView) findViewById(R.id.layer_srcollview);
        layer_srcollview.setHorizontalScrollBarEnabled(false);
        layer_srcollview.setOnTouchListener(this);
        dotlayout = findViewById(R.id.dotlayout);
        dot = findViewById(R.id.dot);
        dotlayoutParams = (FrameLayout.LayoutParams) dot.getLayoutParams();
        imagePager = (ViewPager) findViewById(R.id.image_pager);
        //imagePager.setAlpha((float) 0.5);

        galleryImageAdapter = new GalleryImageAdapter(this);
        imagePager.setAdapter(galleryImageAdapter);

        imagePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                pager_num = position + 1;
                if (position == 2) {
                    dotlayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                float realOffset = Cubic.easeIn(positionOffset, 0, 1, 1);

                total_page = galleryImageAdapter.getCount();
                float offset = (float) ((position + realOffset) * 1.0 / total_page);
                int offsetPositon = (int) (backgoundWidth * offset);


                float layerRealOffset = Sine.easeIn(positionOffset, 0, 1, 1);
                float layerOffset = (float) ((position + layerRealOffset) * 1.0 / total_page);
                int layerOffsetPositon = (int) (backgoundWidth * layerOffset);
                background_srcollview.scrollTo(layerOffsetPositon, 0);
                layer_srcollview.scrollTo(offsetPositon, 0);

                int leftMargins = (int) (dot.getWidth() * position + dot.getWidth() * positionOffset);
                dotlayoutParams.setMargins(leftMargins, 0, 0, 0);
                dot.setLayoutParams(dotlayoutParams);
                if (position == 2) {
                    dotlayout.setAlpha(1 - positionOffset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        imagePager.setOnTouchListener(this);
    }

    boolean isStart = false;

    public void startActivityMain() {
        if (!isStart) {
            startActivity(new Intent(this, MainActivity.class));
            isStart = true;
            finish();
        }
    }

    GestureDetector mygesture = new GestureDetector(this);

    public boolean onTouch(View v, MotionEvent event) {
        mygesture.onTouchEvent(event);
        return imagePager.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            if (pager_num == 3) {
                startActivityMain();
            }
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    void initLayout() {
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        backgoundWidth = dm.widthPixels * 3;
        ViewGroup.LayoutParams layoutParams;

        ImageView back_image_one = (ImageView) findViewById(R.id.back_image_one);
        layoutParams = back_image_one.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_one.setLayoutParams(layoutParams);

        ImageView back_image_two = (ImageView) findViewById(R.id.back_image_two);
        layoutParams = back_image_two.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_two.setLayoutParams(layoutParams);

        ImageView back_image_three = (ImageView) findViewById(R.id.back_image_three);
        layoutParams = back_image_three.getLayoutParams();
        layoutParams.height = dm.heightPixels;
        layoutParams.width = dm.widthPixels;
        back_image_three.setLayoutParams(layoutParams);

        FrameLayout.LayoutParams frameLayoutParams;
        ImageView layer_image_one = (ImageView) findViewById(R.id.layer_image_one);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_one.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_one.setLayoutParams(frameLayoutParams);

        ImageView layer_image_two = (ImageView) findViewById(R.id.layer_image_two);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_two.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_two.setLayoutParams(frameLayoutParams);

        ImageView layer_image_three = (ImageView) findViewById(R.id.layer_image_three);
        frameLayoutParams = (FrameLayout.LayoutParams) layer_image_three.getLayoutParams();
        frameLayoutParams.height = dm.heightPixels;
        frameLayoutParams.width = dm.widthPixels;
        layer_image_three.setLayoutParams(frameLayoutParams);
    }

    @Override
    protected void onDestroy() {
        startActivityMain();
        super.onDestroy();
    }

    public void onClick(View view) {
        startActivityMain();
    }

}