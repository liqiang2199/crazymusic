package com.music.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OSS初始化
 */
public class OssService {

    /**
     * OSS初始化的三个参数
     */
    private static final String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    private static final String StsToken_AccessKeyId = "LTAIizRwVDmuyf5F";
    private static final String StsToken_SecretKeyId = "Rvsk66MCuL4ueCq5RYkmKj8lMmDpzT";
    private static final String StsToken_bucketName = "crazymusic-bucket";
    private static final String StsToken_objectKey = "upload/";//模拟文件夹
    private static final String URL = "http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com";


    /**
     * 初始化
     *
     * @param context
     * @return
     */
    private static OSS initOss(Context context) {
        // 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(StsToken_AccessKeyId, StsToken_SecretKeyId);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(context, endpoint, credentialProvider, conf);
        return oss;
    }

    /**
     * 上传文件
     *
     * @param context
     * @param uploadFilePath 本地图片路径
     */
    public static void upLoadFile(Context context, String uploadFilePath, final OnResult onResult) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(StsToken_bucketName, StsToken_objectKey, uploadFilePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        put.setCallbackParam(new HashMap<String, String>() {
            {
                put("callbackUrl", URL);
                put("callbackBody", "filename=${object}");
            }
        });
        OSSAsyncTask task = initOss(context).asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                String serverCallbackReturnJson = result.getServerCallbackReturnBody();
                Log.d("Json", serverCallbackReturnJson);
                onResult.onSuccess(request, result);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                    onResult.onFailure(serviceException.getRawMessage());
                }
            }
        });
    }

    public interface OnResult {
        void onSuccess(PutObjectRequest request, PutObjectResult result);

        void onFailure(String msg);
    }
}
