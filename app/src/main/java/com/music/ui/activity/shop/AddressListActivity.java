package com.music.ui.activity.shop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.AddressHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址列表
 */
public class AddressListActivity extends BaseActivity {

    private XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        xRecyclerView = findViewById(R.id.mRecyclerEntityView);
        xRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.getAdapter().bindHolder(new AddressHolder());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        xRecyclerView.getAdapter().setData(0, list);
    }
}
