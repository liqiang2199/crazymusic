package com.music.http;

/**
 * 回调
 */

public interface HttpResponseCallBack {
    void onSuccess(String result);

    void onFailed(String failedMsg);

    void onFinished();
}
