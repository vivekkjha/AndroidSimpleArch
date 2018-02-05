package com.mds.android.common.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vivekjha on 14/09/15.
 * Shared preferences class file
 */
public class Prefs {

    private static final String USER_ID = "UserId";

    private static final String AUTH_TOKEN = "AuthToken";

    private static final String sharedPrefsName = "AppPrefs";

    /**
     * The m_context.
     */
    private Context context;

    /**
     * Instantiates a new prefs.
     *
     * @param context the context
     */
    public Prefs(Context context) {
        this.context = context;
    }

    /**
     * Gets the.
     *+
     * @return the shared preferences
     */
    private SharedPreferences get() {
        return context.getSharedPreferences(sharedPrefsName,
                Context.MODE_PRIVATE);
    }

    public int getUserId() {
        return get().getInt(USER_ID, -1);
    }

    public void setUserId(int userId) {
        get().edit().putInt(USER_ID, userId).commit();
    }



}
