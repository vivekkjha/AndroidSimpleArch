package com.mds.android.common.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

/**
 * Created by vivekjha on 14/09/15.
 * Shared preferences class file, which is encrypted by 'secure preference' library
 * Please refer https://github.com/scottyab/secure-preferences for its integration and other details
 */
public class EncryptedPrefs {


    private static final String sharedPrefsName = "AppPrefs";
    private static final String USER_ID = "UserId";
    private static final String AUTH_TOKEN = "AuthToken";


    /**
     * The m_context.
     */
    private Application mContext;
    private String mPrefsPassword;
    private static SecurePreferences mPref;

    public EncryptedPrefs(Application context,String prefsPassword) {
        this.mContext = context;
        this.mPrefsPassword = prefsPassword;
        mPref = new SecurePreferences(mContext, mPrefsPassword, sharedPrefsName);
    }

    /**
     * Gets the shared preference to get and set elements
     *+
     * @return the shared preferences
     */
    private SharedPreferences get() {
        if (mPref == null) {
            mPref = new SecurePreferences(mContext, mPrefsPassword, sharedPrefsName);
        }
        return mPref;
    }

    public int getUserId() {
        return get().getInt(USER_ID, -1);
    }

    public void setUserId(int userId) {
        get().edit().putInt(USER_ID, userId).commit();
    }



}
