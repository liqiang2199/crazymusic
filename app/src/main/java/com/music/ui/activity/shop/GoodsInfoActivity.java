package com.music.ui.activity.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.BaseActivity;
import com.music.ui.entity.XResult;
import com.music.ui.entity.shop.GoodsInfoEntity;
import com.music.utils.ShopBuyDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 商品详情
 */
public class GoodsInfoActivity extends BaseActivity {

    protected LinearLayout linContent;
    protected Button btBuy;
    protected TextView ivCollect;
    protected Button btAddCar;
    private String id;
    private ShopBuyDialog shopBuyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_goods_info);
        super.onCreate(savedInstanceState);
        initView();
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
            getData();
        }
    }

    private void initView() {
        linContent = (LinearLayout) findViewById(R.id.lin_content);
        btBuy = (Button) findViewById(R.id.bt_buy);
        btBuy.setOnClickListener(this);
        ivCollect = (TextView) findViewById(R.id.iv_collect);
        btAddCar = (Button) findViewById(R.id.bt_add_car);
        ivCollect.setOnClickListener(this);
        btAddCar.setOnClickListener(this);
    }

    private void getData() {
        linContent.removeAllViews();
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_GOODS_INFO);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<XResult<GoodsInfoEntity>>() {
                }.getType();
                XResult<GoodsInfoEntity> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
                        GoodsInfoTemplate goodsInfoTemplate = new GoodsInfoTemplate(GoodsInfoActivity.this, resultData.getData());//商城信息模板
                        linContent.addView(goodsInfoTemplate);
                        //商品推荐
                        GoodsRecommend goodsRecommend = new GoodsRecommend(GoodsInfoActivity.this, resultData.getData());//评价--推荐商品列表
                        linContent.addView(goodsRecommend);
                        //规格数据
                        shopBuyDialog = new ShopBuyDialog(GoodsInfoActivity.this, resultData.getData());
                    }
                }
            }

            @Override
            public void onFailed(String failedMsg) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_buy:
                //购买
                shopBuyDialog.show(true);
                break;
            case R.id.bt_add_car:
                //加入购物车
                shopBuyDialog.show(false);
                break;
            case R.id.iv_collect:
                addCollection();
                break;
        }
    }

    private void addCollection() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.COLLECTION_ADD);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("product_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<XResult<GoodsInfoEntity>>() {
                }.getType();
                XResult<GoodsInfoEntity> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    if (resultData.getData() != null) {
                        Toast.makeText(getApplicationContext(), "已收藏", Toast.LENGTH_SHORT).show();
                    }
                } else if (resultData.code == -1) {
                    Toast.makeText(getApplicationContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String failedMsg) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
