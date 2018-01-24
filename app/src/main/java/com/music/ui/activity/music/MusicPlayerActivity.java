package com.music.ui.activity.music;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.adapter.CardPagerAdapter;
import com.music.ui.entity.CardItem;
import com.music.utils.ShadowTransformer;

import static com.music.R.id.viewpager;

public class MusicPlayerActivity extends BaseActivity {
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_music_player);
        super.onCreate(savedInstanceState);
        setTitle("正在播放");
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(viewpager);
        mCardAdapter = new CardPagerAdapter(this);
        mCardAdapter.addCardItem(new CardItem(R.mipmap.ban1, ""));
        mCardAdapter.addCardItem(new CardItem(R.mipmap.ban2, ""));
        mCardAdapter.addCardItem(new CardItem(R.mipmap.ban3, ""));
        mCardAdapter.addCardItem(new CardItem(R.mipmap.ban4, ""));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(2);
    }

}
