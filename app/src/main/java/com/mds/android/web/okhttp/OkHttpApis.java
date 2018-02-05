package com.mds.android.web.okhttp;

import android.app.ProgressDialog;

import com.mds.android.common.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vivekjha on 09/06/16.
 */
public class OkHttpApis {

    //> constants
    private static String TAG  = OkHttpApis.class.getName();
    private static final long CONNECTION_TIMEOUT = 120;//>90 seconds
    private static final long READ_TIMEOUT = 120;//>60 seconds
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
    public static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    public static final MediaType MEDIA_TYPE_GIF = MediaType.parse("image/gif");


    //> objects
    private OkHttpClient mClient;
    private ProgressDialog mProgressDialog;


    public OkHttpApis()
    {
        setOkHttpClient();
    }

    /**
     * Set Ok http client
     */
    private void setOkHttpClient()
    {
        mClient = new OkHttpClient();
    }


    /**
     * Make OkHttp Api calls
     * @param mRequest request bundled in interface
     * @param mResponse Ok http response
     * @param mIApiProgress get API progress
     */
    public void makeOkHttpApiCall(final IOkHttpRequest mRequest, final  IOkHttpResponse mResponse,
                                  final IApiProgress mIApiProgress)
    {
        //> http request
        Request mOkHttpRequest = null;
        LogUtil.w(TAG,"Url : " + mRequest.getRequestUrl());
        switch (mRequest.getApiMethod())
        {
            case GET:
                mOkHttpRequest = new Request.Builder()
                        .url(mRequest.getRequestUrl())
                        .build();
                break;
            case POST:
                mOkHttpRequest = new Request.Builder()
                        .url(mRequest.getRequestUrl())
                        .post(mRequest.getRequestBody())
                        .build();
                break;
            case PUT:
                mOkHttpRequest = new Request.Builder()
                        .url(mRequest.getRequestUrl())
                        .put(mRequest.getRequestBody())
                        .build();
                break;
            case DELETE:
                mOkHttpRequest = new Request.Builder()
                        .url(mRequest.getRequestUrl())
                        .delete(mRequest.getRequestBody())
                        .build();
                break;

        }
        if(mOkHttpRequest!=null) {
            if(mIApiProgress!=null) {
                mProgressDialog = new ProgressDialog(mIApiProgress.getActivityContext());
                mProgressDialog.setMessage(mIApiProgress.getDescription());
                mProgressDialog.setIndeterminate(true);
                mIApiProgress.onProgressStart();
                mProgressDialog.setCancelable(mIApiProgress.isCancellable());
                mProgressDialog.show();
            }
            mClient.newCall(mOkHttpRequest).enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if(mIApiProgress!=null) {
                        mIApiProgress.getActivityContext().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(mProgressDialog!=null)
                                    mProgressDialog.dismiss();
                                mIApiProgress.onProgressEnd();
                            }
                        });
                    }
                    mResponse.onHttpRequestSuccess(mRequest.getRequestTypeString(), response);
                }

                @Override
                public void onFailure(Call call, IOException e) {

                    LogUtil.e(TAG, " Error in Api call: " + e.getMessage());
                    if(mIApiProgress!=null) {
                        mIApiProgress.getActivityContext().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(mProgressDialog!=null)
                                    mProgressDialog.dismiss();
                                mIApiProgress.onProgressEnd();
                            }
                        });
                    }
                    mResponse.onHttpRequestFailure(mRequest.getRequestTypeString(), call.request(), e.getMessage());
                }
            });
        }
    }

    public void makeOkHttpApiCall(final CallWrapper mCallWrapper)
    {
        //> http request
        Request mOkHttpRequest = null;
        LogUtil.w(TAG,"Url : " + mCallWrapper.getRequestUrl());
        switch (mCallWrapper.getApiMethod())
        {
            case GET:
                mOkHttpRequest = new Request.Builder()
                        .url(mCallWrapper.getRequestUrl())
                        .build();
                break;
            case POST:
                mOkHttpRequest = new Request.Builder()
                        .url(mCallWrapper.getRequestUrl())
                        .post(mCallWrapper.getRequestBody())
                        .build();
                break;
            case PUT:
                mOkHttpRequest = new Request.Builder()
                        .url(mCallWrapper.getRequestUrl())
                        .put(mCallWrapper.getRequestBody())
                        .build();
                break;
            case DELETE:
                mOkHttpRequest = new Request.Builder()
                        .url(mCallWrapper.getRequestUrl())
                        .delete(mCallWrapper.getRequestBody())
                        .build();
                break;

        }
        if(mOkHttpRequest!=null) {
            if(mCallWrapper.isProgressNeeded()) {
                mProgressDialog = new ProgressDialog(mCallWrapper.getActivityContext());
                mProgressDialog.setMessage(mCallWrapper.getDescription());
                mProgressDialog.setIndeterminate(true);
                mCallWrapper.onProgressStart();
                mProgressDialog.setCancelable(mCallWrapper.isCancellable());
                mProgressDialog.show();
            }
            mClient.newCall(mOkHttpRequest).enqueue(new Callback() {

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if(mCallWrapper.isProgressNeeded()) {
                        mCallWrapper.getActivityContext().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(mProgressDialog!=null)
                                    mProgressDialog.dismiss();
                                mCallWrapper.onProgressEnd();
                            }
                        });
                    }
                    mCallWrapper.onHttpRequestSuccess(mCallWrapper.getRequestTypeString(), response);
                }

                @Override
                public void onFailure(Call call, IOException e) {

                    LogUtil.e(TAG, " Error in Api call: " + e.getMessage());
                    if(mCallWrapper.isProgressNeeded()) {
                        mCallWrapper.getActivityContext().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(mProgressDialog!=null)
                                    mProgressDialog.dismiss();
                                mCallWrapper.onProgressEnd();
                            }
                        });
                    }
                    mCallWrapper.onHttpRequestFailure(mCallWrapper.getRequestTypeString(), call.request(), e.getMessage());
                }
            });
        }
    }



}
