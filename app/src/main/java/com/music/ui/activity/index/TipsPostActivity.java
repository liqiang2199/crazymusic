package com.music.ui.activity.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.framework.utils.XFileUtil;
import com.framework.utils.XImageUtil;
import com.framework.utils.XToastUtil;
import com.framework.view.XGridViewForScrollView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.music.ConstHost;
import com.music.R;
import com.music.http.HttpRequesParams;
import com.music.http.HttpResponseCallBack;
import com.music.http.HttpUtils;
import com.music.ui.activity.BaseActivity;
import com.music.ui.entity.UploadEntity;
import com.music.ui.entity.XResult;
import com.music.ui.holder.adapter.UploadFileAdapter;
import com.framework.utils.carme.CramUtils;
import com.music.utils.DialogUtils;
import com.music.utils.OssService;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * 发布帖子
 */
public class TipsPostActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    protected TextView btnRight;
    protected EditText etContent;
    protected XGridViewForScrollView mGridView;
    protected EditText etTitle;
    private CramUtils cramUtils;
    private UploadFileAdapter uploadFileAdapter;
    private UploadEntity uploadEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_topic_post);
        super.onCreate(savedInstanceState);
        initView();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
    }

    private void initView() {
        btnRight = (TextView) findViewById(R.id.btn_right);
        btnRight.setText(getString(R.string.send));
        etContent = (EditText) findViewById(R.id.et_content);
        mGridView = (XGridViewForScrollView) findViewById(R.id.mGridView);
        etTitle = (EditText) findViewById(R.id.et_title);
        btnRight.setOnClickListener(this);
        cramUtils = new CramUtils(this);
        uploadFileAdapter = new UploadFileAdapter(this);
        mGridView.setAdapter(uploadFileAdapter);
        mGridView.setOnItemClickListener(this);
    }

    private void getData() {
        HttpRequesParams httpRequesParams = new HttpRequesParams(ConstHost.COMMUNITY_ADD);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", etContent.getText().toString());
            jsonObject.put("title", etTitle.getText().toString());
            jsonObject.put("img_urls", listToString());
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
                    XToastUtil.showToast(TipsPostActivity.this, resultData.getMsg());
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == uploadFileAdapter.getCount() - 1) {
            DialogUtils.showPhotoDialog(this, cramUtils);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (cramUtils.onResultImage(requestCode, resultCode, data)) {
            uploadFile();
        }
    }

    private void uploadFile() {
        new Thread() {
            @Override
            public void run() {
                //这里写入子线程需要做的工作
                long time = new Date().getTime();
                final String tihsImgpath = XFileUtil.getCacheDownloadDir(TipsPostActivity.this) + "/" + time + ".jpg";
                try {
                    XImageUtil.ratioAndGenThumb(cramUtils.imgPath, tihsImgpath, 512, false);
                } catch (Exception e) {
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        upLoadFile(tihsImgpath);
                    }
                });
            }
        }.start();
    }

    private void upLoadFile(String tihsImgpath) {
        //上传图片
        OssService.upLoadFile(this, tihsImgpath, new OssService.OnResult() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
            }
            @Override
            public void onFailure(String msg) {
            }
        });
    }

    /**
     * 逗号拼接字符串
     * @return
     */
    private String listToString() {
        StringBuilder sb = new StringBuilder();
        if (uploadFileAdapter.getPath() != null && uploadFileAdapter.getPath().size() > 0) {
            for (int i = 0; i < uploadFileAdapter.getPath().size(); i++) {
                sb.append(uploadFileAdapter.getPath().get(i).path).append(",");
            }
            return sb.toString().substring(0, sb.toString().length() - 1);
        } else {
            return "";
        }
    }
}
