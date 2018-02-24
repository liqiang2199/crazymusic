package com.music.ui.activity.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.framework.adapter.TabFragmentPagerAdapter;
import com.framework.view.viewPager.XViewPager;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表
 * 1 未付款 2 已付款 3 未发货 4 已发货 5 交易关闭 6交易完成 7退款中 8退款完成 9拒绝退款   0就是所有订单
 */

public class OrderListActivity extends BaseActivity {
    protected TabLayout mTabLayout;
    protected XViewPager mViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tab_page);
        super.onCreate(savedInstanceState);
        if (!isActivityNeedLogin()){
            this.finish();
            return;
        }
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPage = (XViewPager) findViewById(R.id.mViewPage);

        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new OrderListFragment("0"));
        fragmentList.add(new OrderListFragment("1"));
        fragmentList.add(new OrderListFragment("3"));
        fragmentList.add(new OrderListFragment("4"));
        fragmentList.add(new OrderListFragment("6"));
        fragmentList.add(new OrderListFragment("6"));
//        fragmentList.add(new OrderListFragment("7,8,9"));
        fragmentList.add(new OrderListFragment("9"));

        List<String> list = new ArrayList<>();

        list.add("全部");
        list.add("待付款");
        list.add("待发货");
        list.add("待收货");
        list.add("交易完成");
        list.add("待评价");
        list.add("退货退款");
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPage.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, list));
        mTabLayout.setupWithViewPager(mViewPage);
        mViewPage.setOffscreenPageLimit(1);
    }
}
