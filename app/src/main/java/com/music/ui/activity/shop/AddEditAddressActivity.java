package com.music.ui.activity.shop;

import android.os.Bundle;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;

/**
 * 新增【编辑】收货地址
 */
public class AddEditAddressActivity extends BaseActivity {

    private XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_address);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        xRecyclerView = findViewById(R.id.mRecyclerEntityView);
    }
}
