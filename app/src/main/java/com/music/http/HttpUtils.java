package com.music.http;

import android.content.Context;
import android.os.Environment;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.ex.HttpException;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 网络框架
 */

public class HttpUtils {

    private static final String REQUEST_COOKIE_KEY = "Cookie";
    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;


    /**
     * Get网络请求
     *
     * @param context
     * @param isShowLoading
     * @param params
     * @param callBack
     */
    public static void get(Context context, boolean isShowLoading, HttpRequesParams params, final HttpResponseCallBack callBack) {
        setDefaultHeader(params);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    httpExceptionHandler(ex, callBack);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                callBack.onFinished();
            }
        });
    }

    /**
     * Post网络请求
     *
     * @param context
     * @param isShowLoading
     * @param params
     * @param callBack
     */
    public static void post(Context context, boolean isShowLoading, HttpRequesParams params, final HttpResponseCallBack callBack) {
        setDefaultHeader(params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    httpExceptionHandler(ex, callBack);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                callBack.onFinished();
            }
        });
    }

    /**
     * 下载文件
     *
     * @param context
     * @param url
     * @param savePath
     * @param callBack
     */
    public static void downloadFile(Context context, String url, String savePath, HttpResponseCallBack callBack) {
        String urlTf8 = "";
        try {
            urlTf8 = URLEncoder.encode(url, "UTF-8").replaceAll("\\+", "%20");
            urlTf8 = urlTf8.replaceAll("%3A", ":").replaceAll("%2F", "/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final HttpRequesParams requestParams = new HttpRequesParams(urlTf8);
        requestParams.setSaveFilePath(BASE_PATH + savePath);
        requestParams.setAutoRename(true);//断点下载
        requestParams.setExecutor(new PriorityExecutor(2, true));
        get(context, false, requestParams, callBack);
    }

    /**
     * 上传文件
     *
     * @param context
     * @param url
     * @param uploadeFilePath
     * @param callBack
     */
    public static void uploadFile(Context context, String url, String uploadeFilePath, HttpResponseCallBack callBack) {
        HttpRequesParams params = new HttpRequesParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file", new File(uploadeFilePath));
        post(context, false, params, callBack);
    }

    /**
     * 设置默认请求header
     * @param params
     */
    private static void setDefaultHeader(HttpRequesParams params) {
//        params.addHeader("User-Agent", "BauhiniaValley Android");
//        params.addHeader("Accept-Language", getLocaleLanguage());
    }

    private static void httpExceptionHandler(Throwable ex, HttpResponseCallBack callBack) {
        HttpException httpEx = (HttpException) ex;
        int responseCode = httpEx.getCode();
        String responseMsg = httpEx.getMessage();
        String errorResult = httpEx.getResult();
        if (responseCode >= 400 && responseCode < 500) {
            callBack.onFailed("抱歉，客户端错误，请稍后重试！");
        } else {
            callBack.onFailed("抱歉，服务端错误，请稍后重试！");
        }
    }


}
