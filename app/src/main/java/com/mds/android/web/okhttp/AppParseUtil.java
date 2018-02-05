package com.mds.android.web.okhttp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mds.android.framework.model.User;

/**
 * Created by vivekjha on 09/06/16.
 */
public class AppParseUtil {

    private static Gson mGson;

    private AppParseUtil()
    {
        mGson =new GsonBuilder()
                .create();
    }

    public static AppParseUtil newInstance()
    {
       return new AppParseUtil();
    }

    public User parseUser(String json)
    {
        if(json !=null){
            try{
                return mGson.fromJson(json,User.class);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }



}
