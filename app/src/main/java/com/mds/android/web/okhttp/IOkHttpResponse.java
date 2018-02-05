package com.mds.android.web.okhttp;


import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vivekjha on 09/06/16.
 */
public interface IOkHttpResponse{

    void onHttpRequestSuccess(String requestType, Response response);
    void onHttpRequestFailure(String requestType, Request request, String errorMessage) ;
}
