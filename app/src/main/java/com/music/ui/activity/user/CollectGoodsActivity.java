package com.music.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.framework.view.recyclerView.XRecyclerView;
import com.music.R;
import com.music.ui.activity.BaseActivity;
import com.music.ui.holder.CollectGoodsListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏商品
 */
public class CollectGoodsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
    }

    private void initView() {
    }
}
