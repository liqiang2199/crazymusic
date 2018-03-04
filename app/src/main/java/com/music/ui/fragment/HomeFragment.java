package com.music.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.holder.IndexListHolder;
import com.music.ui.holder.IndexTeacherHolder;
import com.music.ui.holder.IndexTopHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的
 */
public class HomeFragment extends BaseFragment {
    protected View rootView;
    protected EditText etSearch;
    protected XRecyclerView mRecyclerEntityView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_home, null);
        }
        initView();
        return rootView;
    }

    private void initView() {
        etSearch = (EditText) rootView.findViewById(R.id.et_search);
        mRecyclerEntityView = (XRecyclerView) rootView.findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getAdapter().bindHolder(new IndexTopHolder());
        mRecyclerEntityView.getAdapter().bindHolder(new IndexListHolder());
        mRecyclerEntityView.getAdapter().bindHolder(new IndexTeacherHolder());

        mRecyclerEntityView.getAdapter().setData(0, "ddd");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add("");
        }
        mRecyclerEntityView.getAdapter().setData(1, list);
        mRecyclerEntityView.getAdapter().setData(2, "ee");
    }
}
