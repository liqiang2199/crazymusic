package com.music.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.bigkoo.pickerview.OptionsPickerView;
import com.framework.utils.StatusBarUtil;
import com.framework.view.Toolbar;
import com.google.gson.Gson;
import com.music.BaseApp;
import com.music.R;
import com.music.common.Constants;
import com.music.model.jsonbeen.JsonBean;
import com.music.ui.activity.user.LoginActivity;
import com.music.ui.ihanlder.IChoiceAddressHanlder;
import com.music.utils.GetJsonDataUtil;
import com.music.utils.UtilsTools;
import com.wega.library.loadingDialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * 基本Activity
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Toolbar toolbar;

    protected Context mContext;
    protected Gson gson;
    protected  int width;
    protected  int height;

    protected  LoadingDialog mLoadingDialog;

    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.getActivities().add(this);
        super.onCreate(savedInstanceState);
        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            final Drawable upArrow = getResources().getDrawable(R.mipmap.ic_back);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        StatusBarUtil.StatusBarDarkMode(this);
//        StatusBarUtil.setStatusBarColor(this, R.drawable.bg_base_bar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        BaseApp.getActivities().remove(this);

    }

    /**
     * 界面需要登录
     */
    protected boolean isActivityNeedLogin(){
        if (UtilsTools.isStringNull(UtilsTools.getReadCacheUtilData(Constants.TOKEN))){

            startActivity(new Intent(mContext, LoginActivity.class));
            return false;
        }else{
            String pakgeName = mContext.getClass().getName();
            if (!pakgeName.equals("com.music.ui.activity.user.SetActivity")
                    ||!pakgeName.equals("com.music.ui.activity.guide.GuideActivity")
                    ||!pakgeName.equals("com.music.ui.activity.SplashActivity")
                    ||!pakgeName.equals("com.music.ui.activity.MainActivity")){

                return true;
            }else{
                startActivity(new Intent(mContext, LoginActivity.class));
                return false;
            }
        }

    }

    /**
     * 获取屏幕的高度
     */
    public void Width_Height(){

        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

    }

    /**
     * 返回公用的Gson 对象
     * @return
     */
    protected Gson getNewGson(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public ArrayList<JsonBean> options1Items = new ArrayList<>();
    public ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }


    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public void ShowPickerView(final IChoiceAddressHanlder choiceAddressHanlder) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);

                choiceAddressHanlder.choicePostion(options1,options2,options3);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }
}
