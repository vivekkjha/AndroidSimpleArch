package com.mds.android.web.okhttp;


import com.mds.android.common.value.Constants;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by vivekjha on 07/08/16.
 */
public abstract class CallWrapper implements IOkHttpRequest,IOkHttpResponse,IApiProgress {


    public boolean isProgressNeeded()
    {return true;}

    @Override
    public String getDescription() {
        return "Loading...";
    }

    @Override
    public void onProgressEnd() {

    }

    @Override
    public void onProgressStart() {

    }

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public Constants.API_METHOD getApiMethod() {
        return Constants.API_METHOD.GET;
    }


    @Override
    public String getRequestTypeString() {
        return null;
    }

    @Override
    public void onHttpRequestFailure(String requestType, Request request, String errorMessage) {

    }

    @Override
    public void onHttpRequestSuccess(String requestType, Response response) {

    }
}
