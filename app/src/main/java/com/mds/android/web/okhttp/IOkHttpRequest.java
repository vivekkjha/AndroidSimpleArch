package com.mds.android.web.okhttp;


import com.mds.android.common.value.Constants;

import okhttp3.RequestBody;

/**
 * Created by vivekjha on 09/06/16.
 */
public interface IOkHttpRequest
{
    RequestBody getRequestBody(); //> Get request body for Ok http
    Constants.API_METHOD getApiMethod(); //> get Api calling method
    String getRequestUrl(); //> get Request URL
    String getRequestTypeString();
}
