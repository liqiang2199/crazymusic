package com.music.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.CoursesListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 我课程
 */
public class MyCoursesActivity extends BaseActivity {

    protected XRecyclerView mRecyclerEntityView;
    private int page = 1, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mRecyclerEntityView = (XRecyclerView) findViewById(R.id.mRecyclerEntityView);
        mRecyclerEntityView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mRecyclerEntityView.getAdapter().bindHolder(new CoursesListHolder());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        mRecyclerEntityView.getAdapter().setData(0, list);
    }
}
