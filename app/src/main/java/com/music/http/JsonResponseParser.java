package com.music.http;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * JsonResponse
 */
public class JsonResponseParser implements ResponseParser {
    @Override
    public void checkResponse(UriRequest request) throws Throwable {
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        return null;
    }
}
