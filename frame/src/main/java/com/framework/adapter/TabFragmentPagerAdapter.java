package com.framework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * TabFragmentPagerAdapter
 * 基础标签切换
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.size() > position) {
            return titles.get(position);
        } else {
            return "";
        }
    }

}
