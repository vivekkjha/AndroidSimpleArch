package com.mds.android.common.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.mds.android.framework.activity.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivekjha on 21/09/15.
 */
public class MdsAppLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = MdsAppLifecycleHandler.class.getSimpleName();
    private static int RESUMED = 0;
    private static int STARTED = 0;
    private static int STOPPED = 0;
    private static int visibleActivityCount = 0;
    private static int foregroundActivityCount = 0;



    private static Map<String, WeakReference<Activity>> mActivitiesMap;

    MdsAppLifecycleHandler() {
        mActivitiesMap = MdsApplication.getActivitiesMap();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        String name = activity instanceof BaseActivity ? ((BaseActivity) activity).getActivityKey() :activity.getClass().getSimpleName();
        boolean killAll = activity instanceof BaseActivity && ((BaseActivity) activity).killAllActivity();

        for (String key : mActivitiesMap.keySet()) {
            WeakReference<Activity> weakReference = mActivitiesMap.get(key);
            if (weakReference == null) {
                continue;
            }
            Activity a = weakReference.get();
            if (killAll) {
                if (a != null) a.finish();
                continue;
            }
            if (!key.equals(name)) {
                continue;
            }
            if (a != null && !a.isFinishing()) {
                if (a instanceof BaseActivity) {
                    if (((BaseActivity) a).isSingleInstance()) {
                        a.finish();
                    }
                }
            }
        }
        mActivitiesMap.put(name, new WeakReference<Activity>(activity));
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        String removeKey = "";
        for (String key : mActivitiesMap.keySet()) {
            WeakReference<Activity> weakReference = mActivitiesMap.get(key);
            if (weakReference == null) {
                continue;
            }
            if (weakReference.get() == activity) {
                removeKey = activity instanceof BaseActivity ? ((BaseActivity) activity).getActivityKey() : activity.getClass().getSimpleName();
            }
        }
        mActivitiesMap.remove(removeKey);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        foregroundActivityCount --;
        //LogUtil.w("EylogAppLifecycleHandler RESUMED", "application is being foreground: " + (foregroundActivityCount>0));

    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++RESUMED;
        foregroundActivityCount ++;


    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        visibleActivityCount ++;
        STARTED++;
        Log.w(TAG, "application is being visible: " + (visibleActivityCount>0));

    }

    @Override
    public void onActivityStopped(Activity activity) {
        visibleActivityCount --;
        ++STOPPED;
        //LogUtil.w("EylogAppLifecycleHandler", "application is being killed " + (STARTED == STOPPED));
        Log.w(TAG, "application is being visible: " + (visibleActivityCount>0));


    }

    public static boolean isApplicationInForeground() {
        return RESUMED > STOPPED;
    }

    /** Returns true if app has foreground */
    public static boolean isAppInForeground(){
        return foregroundActivityCount > 0;
    }

    /** Returns true if any activity of app is visible (or device is sleep when
     * an activity was visible) */
    public static boolean isAppVisible(){
        return visibleActivityCount > 0;
    }
}
