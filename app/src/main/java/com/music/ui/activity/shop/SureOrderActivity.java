package com.music.ui.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.framework.utils.XToastUtil;
import com.framework.view.Toolbar;
import com.framework.view.XListViewForScrollView;
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
import com.music.ui.holder.ShopListHolder;
import com.music.ui.holder.adapter.GoodsSureOrderAdapter;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 确认订单
 */
public class SureOrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    protected TextView tvConsignee;
    protected TextView tvMobile;
    protected TextView tvCity;
    protected LinearLayout btnShopAddress;
    protected XListViewForScrollView mListViewForScrollView;
    protected TextView tvRess;
    protected TextView tvNum;
    protected TextView tvAdd;
    protected TextView tvKuaidi;
    protected TextView tvYoufei;
    protected TextView tvNumGoods;
    protected TextView tvGoodsPrice;
    protected TextView tvTotal;
    protected Button btSubmit;
    protected RadioGroup radioGroup;
    protected EditText etBuyerMsg;
    private GoodsSureOrderAdapter orderAdapter;//商品列表
    private String spaceIds;
    private GoodsInfoEntity goodsInfoEntity;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sure_order);
        super.onCreate(savedInstanceState);
        initView();
        goodsInfoEntity = EventBus.getDefault().getStickyEvent(GoodsInfoEntity.class);
        EventBus.getDefault().removeStickyEvent(GoodsInfoEntity.class);
        if (getIntent() != null) {
            spaceIds = getIntent().getStringExtra("ids");
            num = getIntent().getIntExtra("num", 1);
        }
        if (goodsInfoEntity == null) {
            return;
        }
        setData();
    }

    private void setData() {
        tvConsignee = (TextView) findViewById(R.id.tv_consignee);
        tvMobile = (TextView) findViewById(R.id.tv_mobile);
        tvCity = (TextView) findViewById(R.id.tv_city);
        btnShopAddress = (LinearLayout) findViewById(R.id.btn_shopAddress);
        mListViewForScrollView = (XListViewForScrollView) findViewById(R.id.mListViewForScrollView);
        tvRess = (TextView) findViewById(R.id.tv_ress);
        tvNum.setText(String.valueOf(num));
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvKuaidi = (TextView) findViewById(R.id.tv_kuaidi);
        tvYoufei = (TextView) findViewById(R.id.tv_youfei);
        tvNumGoods.setText("共" + num + "件商品");
        tvGoodsPrice.setText("￥" + num * goodsInfoEntity.getNow_price());
        tvTotal.setText("￥" + num * goodsInfoEntity.getNow_price());
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        etBuyerMsg = (EditText) findViewById(R.id.et_buyer_msg);
        btSubmit = (Button) findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(this);
        btnShopAddress.setOnClickListener(this);
        orderAdapter = new GoodsSureOrderAdapter(this);
        mListViewForScrollView.setAdapter(orderAdapter);
    }

    private void initView() {
        tvConsignee = (TextView) findViewById(R.id.tv_consignee);
        tvMobile = (TextView) findViewById(R.id.tv_mobile);
        tvCity = (TextView) findViewById(R.id.tv_city);
        btnShopAddress = (LinearLayout) findViewById(R.id.btn_shopAddress);
        mListViewForScrollView = (XListViewForScrollView) findViewById(R.id.mListViewForScrollView);
        tvRess = (TextView) findViewById(R.id.tv_ress);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvKuaidi = (TextView) findViewById(R.id.tv_kuaidi);
        tvYoufei = (TextView) findViewById(R.id.tv_youfei);
        tvNumGoods = (TextView) findViewById(R.id.tv_num_goods);
        tvGoodsPrice = (TextView) findViewById(R.id.tv_goods_price);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        etBuyerMsg = (EditText) findViewById(R.id.et_buyer_msg);
        btSubmit = (Button) findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(this);
        btnShopAddress.setOnClickListener(this);
        orderAdapter = new GoodsSureOrderAdapter(this);
        mListViewForScrollView.setAdapter(orderAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_submit:
                //提交数据
                submitData();
                break;
            case R.id.btn_shopAddress:
                //选择地址
                startActivity(new Intent(this, AddressListActivity.class));
                break;
        }
    }

    /**
     * 提交数据
     */
    private void submitData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.ORDER_CREATE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", goodsInfoEntity.getId());
            jsonObject.put("number", num + "");
            jsonObject.put("attrs", spaceIds);
            jsonObject.put("buyerMessage", etBuyerMsg.getText().toString());
            jsonObject.put("buyerNick", num + "");
            jsonObject.put("productTitle", goodsInfoEntity.getName());
            jsonObject.put("product_price", goodsInfoEntity.getCost_price());
            jsonObject.put("product_img", goodsInfoEntity.getImgs().get(0).getProduct_img());
            jsonObject.put("cardId", num + "");
            jsonObject.put("payment", String.valueOf(num * goodsInfoEntity.getNow_price()));
            jsonObject.put("postFee", String.valueOf(num));
            jsonObject.put("receiver_name", tvConsignee.getText());
            jsonObject.put("receiver_phone", tvMobile.getText());
            jsonObject.put("receiver_province", "四川");
            jsonObject.put("receiver_city", "成都");
            jsonObject.put("receiver_district", "青羊区");
            jsonObject.put("receiver_address", "3号楼");
            jsonObject.put("receiver_code", "610000");
            jsonObject.put("orderType", "0");
            jsonObject.put("coupon_id", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<XResult<String>>() {
                }.getType();
                XResult<String> resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    XToastUtil.showToast(SureOrderActivity.this, resultData.getMsg());
                    finish();
                } else {
                    XToastUtil.showToast(SureOrderActivity.this, resultData.getMsg());
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_appliy:
                //支付宝
                break;
            case R.id.rb_weixin:
                //微信
                break;
        }
    }
}
