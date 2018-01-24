package com.music.ui.activity;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.utils.XToastUtil;
import com.music.BaseApp;
import com.music.R;
import com.music.ui.fragment.CommunityFragment;
import com.music.ui.fragment.HomeFragment;
import com.music.ui.fragment.MyFragment;
import com.music.ui.fragment.ShopFragment;
import com.music.ui.fragment.ShopFragments;
import com.music.utils.DialogUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    protected TextView tabSy;
    protected TextView tabLg;
    protected TextView tabSq;
    protected TextView tabWd;
    protected ImageView tabFb;
    private ArrayList<Fragment> xFragment = new ArrayList<>();
    private TextView[] tabs;
    private Drawable[] tabn;//默认图标
    private Drawable[] tabh;//点亮图标
    private int lastPostion = 0;
    private int[] colorN, colorH;////默认颜色，选中颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initView();
        setTab();
    }

    //初始化
    private void initView() {
        tabSy = (TextView) findViewById(R.id.tab_sy);
        tabLg = (TextView) findViewById(R.id.tab_lg);
        tabSq = (TextView) findViewById(R.id.tab_sq);
        tabWd = (TextView) findViewById(R.id.tab_wd);
        tabFb = (ImageView) findViewById(R.id.tab_fb);

        tabs = new TextView[]{tabSy, tabLg, tabSq, tabWd};
        tabn = new Drawable[]{ContextCompat.getDrawable(this, R.mipmap.ic_home1),
                ContextCompat.getDrawable(this, R.mipmap.ic_legou1),
                ContextCompat.getDrawable(this, R.mipmap.ic_shequ1),
                ContextCompat.getDrawable(this, R.mipmap.ic_wode1)};
        tabh = new Drawable[]{ContextCompat.getDrawable(this, R.mipmap.ic_home),
                ContextCompat.getDrawable(this, R.mipmap.ic_legou),
                ContextCompat.getDrawable(this, R.mipmap.ic_shequ),
                ContextCompat.getDrawable(this, R.mipmap.ic_wode)};
        colorN = new int[]{
                ContextCompat.getColor(this, R.color.color666666),
                ContextCompat.getColor(this, R.color.color666666),
                ContextCompat.getColor(this, R.color.color666666),
                ContextCompat.getColor(this, R.color.color666666)
        };
        colorH = new int[]{
                ContextCompat.getColor(this, R.color.color00BEE7),
                ContextCompat.getColor(this, R.color.color00BEE7),
                ContextCompat.getColor(this, R.color.color00BEE7),
                ContextCompat.getColor(this, R.color.color00BEE7)
        };
    }

    //设置底部tab
    private void setTab() {
        //设置fragment
        xFragment.add(new HomeFragment());//首页
        xFragment.add(new ShopFragments());
        xFragment.add(new CommunityFragment());
        xFragment.add(new MyFragment());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mFrameLayout, xFragment.get(1));
        transaction.hide(xFragment.get(1));
        transaction.add(R.id.mFrameLayout, xFragment.get(2));
        transaction.hide(xFragment.get(2));
        transaction.add(R.id.mFrameLayout, xFragment.get(3));
        transaction.hide(xFragment.get(3));
        transaction.add(R.id.mFrameLayout, xFragment.get(0));
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tab_sy:
                //社区
                onPageSelected(0);
                break;
            case R.id.tab_lg:
                //乐购
                onPageSelected(1);
                break;
            case R.id.tab_sq:
                //社区
                onPageSelected(2);
                break;
            case R.id.tab_wd:
                //我的
                onPageSelected(3);
                break;
            case R.id.tab_fb:
                //发布
                DialogUtils.showPost(this,this);
                break;
        }
    }

    /**
     * 选择
     *
     * @param position
     */
    public void onPageSelected(int position) {
        if (position == lastPostion) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(xFragment.get(lastPostion));
        transaction.show(xFragment.get(position));
        //提交修改
        transaction.commit();
        if (lastPostion != position) {
            tabs[lastPostion].setCompoundDrawablesWithIntrinsicBounds(null, tabn[lastPostion], null, null);
            tabs[lastPostion].setTextColor(colorN[lastPostion]);
            tabs[position].setCompoundDrawablesWithIntrinsicBounds(null, tabh[position], null, null);
            tabs[position].setTextColor(colorH[position]);
            lastPostion = position;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private void exit() {
        if (!isExit) {
            isExit = true;
            XToastUtil.showToast(this, "再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            for (Activity activity : BaseApp.getActivities()) {
                activity.finish();
            }
        }
    }
}
