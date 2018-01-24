package com.music.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.framework.utils.XAppUtil;
import com.framework.utils.XToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.shop.GoodsInfoActivity;
import com.music.ui.activity.shop.GoodsInfoTemplate;
import com.music.ui.activity.shop.GoodsRecommend;
import com.music.ui.activity.shop.SureOrderActivity;
import com.music.ui.entity.XResult;
import com.music.ui.entity.shop.GoodsInfoEntity;
import com.music.view.SpecView;
import com.music.view.WrapRadioButton;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品规格
 */
@SuppressLint("StringFormatInvalid")
public class ShopBuyDialog extends Dialog implements View.OnClickListener, WrapRadioButton.OnCheckedChanged {

    protected View rootView;
    protected Context activity;
    protected ImageView goodsImage;
    protected TextView tvJiage;
    protected TextView tvKuchun;
    protected LinearLayout llGoodsspec;
    protected TextView btnMov;
    protected TextView tvNum;
    protected TextView btnAdd;
    protected RelativeLayout gmSl;
    protected TextView btnSubmit;
    private GoodsInfoEntity goodsInfoEntity;
    private Map<String, String> selects = new HashMap<String, String>();
    private int number = 1;
    private boolean isBuy;


    public ShopBuyDialog(Context context, GoodsInfoEntity goodsInfoEntity) {
        super(context, R.style.AppTheme_bottomDialog);
        this.activity = context;
        this.goodsInfoEntity = goodsInfoEntity;
        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_goodsspec, null);
        setContentView(rootView);
        initView(rootView);
    }

    public void show(boolean isBuy) {
        super.show();
        this.isBuy = isBuy;
        XAppUtil.closeSoftInput(activity);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        WindowManager m = ((Activity) activity).getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = window.getAttributes();  //获取对话框当前的参数值
        p.width = d.getWidth();    //宽度设置为屏幕的0.8
        window.setAttributes(p);     //设置生效
        setData();
    }

    /**
     * 初始化
     */
    private void initView(View rootView) {
        goodsImage = (ImageView) rootView.findViewById(R.id.goodsImage);
        tvJiage = (TextView) rootView.findViewById(R.id.tv_jiage);
        tvKuchun = (TextView) rootView.findViewById(R.id.tv_kuchun);
        llGoodsspec = (LinearLayout) rootView.findViewById(R.id.ll_goodsspec);
        btnMov = (TextView) rootView.findViewById(R.id.btn_mov);
        tvNum = (TextView) rootView.findViewById(R.id.tv_num);
        btnAdd = (TextView) rootView.findViewById(R.id.btn_add);
        gmSl = (RelativeLayout) rootView.findViewById(R.id.gm_sl);
        btnSubmit = (TextView) rootView.findViewById(R.id.btn_submit);

        btnMov.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    /**
     * 绑定数据
     */
    private void setData() {
        if (goodsInfoEntity != null) {
            llGoodsspec.removeAllViews();
            try {
                tvJiage.setText(goodsInfoEntity.getNow_price() + "");//商品价格
                if (goodsInfoEntity.getImgs() != null && goodsInfoEntity.getImgs().size() > 0) {
                    XImageLoadUtils.loadRoundImage(activity, goodsInfoEntity.getImgs().get(0).toString(), goodsImage, R.mipmap.bg_img_def, 10);
                }
                if (goodsInfoEntity.getAttrList() != null) {
                    List<List<GoodsInfoEntity.AttrListBean>> list = new ArrayList<>();
                    List<GoodsInfoEntity.AttrListBean> listnei = new ArrayList<>();
                    String s = "";
                    for (GoodsInfoEntity.AttrListBean specsBean : goodsInfoEntity.getAttrList()) {
                        //判断是否是相同的规格
                        if (s.equals(specsBean.getType())) {
                            listnei.add(specsBean);
                        } else {
                            listnei = new ArrayList<>();
                            listnei.add(specsBean);
                            list.add(listnei);
                            s = specsBean.getType();
                        }
                        selects.put(specsBean.getType(), specsBean.getProduct_attr_id());
                    }
                    for (List<GoodsInfoEntity.AttrListBean> specsBeanlist : list) {
                        SpecView specView = new SpecView(activity);
                        specView.setData(specsBeanlist);
                        specView.setOnCheckedChanged(this);
                        llGoodsspec.addView(specView);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取选择的商品规格
     * 逗号分隔（，）
     */
    private String getSpaces() {
        String space = "";
        try {
            for (String v : selects.values()) {
                space += v + ",";
            }
            space = space.substring(0, space.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        space += "";
        return space;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_mov) {
            if (number > 1) {
                tvNum.setText(--number + "");
            }
        } else if (view.getId() == R.id.btn_add) {
            number++;
            tvNum.setText(number + "");
        } else if (view.getId() == R.id.btn_submit) {
            if (isBuy) {
                //直接购买
                EventBus.getDefault().postSticky(goodsInfoEntity);
                Intent intent = new Intent(activity, SureOrderActivity.class);
                intent.putExtra("ids", getSpaces());
                intent.putExtra("num", number);
                activity.startActivity(intent);
                dismiss();
            } else {
                addBuyCar();
            }
        }
    }

    private void addBuyCar() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.SHOP_CAR_ADD);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("product_id", goodsInfoEntity.getId());
            jsonObject.put("product_attrs", getSpaces());
            jsonObject.put("number", tvNum.getText());
            jsonObject.put("product_name", goodsInfoEntity.getName());
            jsonObject.put("product_img", goodsInfoEntity.getImgs().get(0).getProduct_img());
            jsonObject.put("payment", 100);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(getContext(), false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("---", "onSuccess: " + result);
                Gson gson = new Gson();
                Type type = new TypeToken<XResult<GoodsInfoEntity>>() {
                }.getType();
                XResult<GoodsInfoEntity> resultData = gson.fromJson(result, type);
                Toast.makeText(getContext(), "成功加入购物车", Toast.LENGTH_SHORT).show();
                dismiss();
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
    public void onCheckedChanged(View view, GoodsInfoEntity.AttrListBean value) {
        selects.put(value.getType(), value.getProduct_attr_id());
    }
}


