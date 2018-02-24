package com.music.ui.activity.user;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.view.Toolbar;
import com.music.R;
import com.music.ui.activity.BaseActivity;

/**
 * 成为老师
 */
public class ApplyForTeacherActivity extends BaseActivity {

    protected TextView btnRight;
    protected AppBarLayout appBar;
    protected ImageView ivOn;
    protected ImageView ivUp;
    protected ImageView ivZl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_apply_for_teacher);
        super.onCreate(savedInstanceState);
        if (!isActivityNeedLogin()){
            this.finish();
            return;
        }
        initView();
    }

    private void initView() {
        btnRight = (TextView) findViewById(R.id.btn_right);
        btnRight.setText(getString(R.string.upload));
        btnRight.setOnClickListener(ApplyForTeacherActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        ivOn = (ImageView) findViewById(R.id.iv_on);
        ivOn.setOnClickListener(ApplyForTeacherActivity.this);
        ivUp = (ImageView) findViewById(R.id.iv_up);
        ivUp.setOnClickListener(ApplyForTeacherActivity.this);
        ivZl = (ImageView) findViewById(R.id.iv_zl);
        ivZl.setOnClickListener(ApplyForTeacherActivity.this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_right) {

        } else if (view.getId() == R.id.iv_on) {

        } else if (view.getId() == R.id.iv_up) {

        } else if (view.getId() == R.id.iv_zl) {
        }
    }
}
