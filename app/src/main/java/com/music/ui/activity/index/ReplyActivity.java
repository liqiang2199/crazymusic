package com.music.ui.activity.index;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.framework.utils.XToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.BaseActivity;
import com.music.ui.entity.XResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 社区回复
 */
public class ReplyActivity extends BaseActivity {
    protected EditText etContent;
    private TextView btnRight;
    private String communId, prenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reply);
        super.onCreate(savedInstanceState);
        initView();
        if (getIntent() != null) {
            communId = getIntent().getStringExtra("cid");
            prenId = getIntent().getStringExtra("pid");
        }
    }

    private void initView() {
        etContent = (EditText) findViewById(R.id.et_content);
        btnRight = (TextView) findViewById(R.id.btn_right);
        btnRight.setText(getString(R.string.send));
        btnRight.setOnClickListener(this);
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.COMMUNITY_EVALUATE_ADD);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", etContent.getText().toString());
            jsonObject.put("community_id", communId);
            if (!TextUtils.isEmpty(prenId)) {
                jsonObject.put("parent_id", prenId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequesParams.setAsJsonContent(true);
        httpRequesParams.setBodyContent(jsonObject.toString());
        HttpUtils.post(this, false, httpRequesParams, new HttpResponseCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<XResult>() {
                }.getType();
                XResult resultData = gson.fromJson(result, type);
                if (resultData.code == 0) {
                    XToastUtil.showToast(ReplyActivity.this, resultData.getMsg());
                    finish();
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
            case R.id.btn_right:
                //发布
                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    XToastUtil.showToast(this, getString(R.string.input_info));
                    return;
                }
                getData();
                break;
        }
    }
}
