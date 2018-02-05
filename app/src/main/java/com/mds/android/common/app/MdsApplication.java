package com.mds.android.common.app;

import android.app.Activity;
import android.app.Application;

import com.mds.android.framework.activity.BaseActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vivekjha on 26/08/16.
 */
public class MdsApplication extends Application {


    private static String TAG = MdsApplication.class.getName();
    private static MdsApplication mInstance;
    private Prefs mPrefs;
    private EncryptedPrefs mEPrefs;
    private DisplayImageOptions options;
    private com.nostra13.universalimageloader.core.ImageLoader loader;

    private static Map<String, WeakReference<Activity>> mActivitiesMap;

    static
    {
        mActivitiesMap =  new HashMap<String, WeakReference<Activity>>();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mPrefs = new Prefs(this);
        mEPrefs = new EncryptedPrefs(this,"MDS#%53");
        // > Initializing image loader at application level
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        loader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        loader.init(config);
        registerActivityLifecycleCallbacks(new MdsAppLifecycleHandler());

    }

    public static MdsApplication getInstance() {
        return mInstance;
    }
    public Prefs getPrefs() {
        return mPrefs;
    }
    public EncryptedPrefs getEPrefs() {
        return mEPrefs;
    }
    public DisplayImageOptions getImageLoaderOptions() {
        return options;
    }
    public com.nostra13.universalimageloader.core.ImageLoader getImageLoader() {
        return loader;
    }
    public static Map<String, WeakReference<Activity>> getActivitiesMap() {
        return mActivitiesMap;
    }

    /**
     * Method to kill All Activity and exit the application
     */
    public void killAllActivity() {
        if (mActivitiesMap == null) return;
        Set<String> keySet = mActivitiesMap.keySet();
        for (String key : keySet) {
            WeakReference<Activity> weakReference = mActivitiesMap.get(key);
            if (weakReference == null) continue;
            Activity activity = weakReference.get();
            if (activity == null) continue;
            if (activity.isFinishing()) continue;
            activity.finish();
        }
        mActivitiesMap.clear();
    }

    /**
     * get All activities in the Hash
     * @return List of all active activities
     */
    public List<Activity> getAllActivities() {
        ArrayList<Activity> activities = new ArrayList<>();
        if (mActivitiesMap == null) {
            return activities;
        }
        Set<String> keySet = mActivitiesMap.keySet();
        for (String key : keySet) {
            WeakReference<Activity> weakReference = mActivitiesMap.get(key);
            if (weakReference == null) continue;
            Activity activity = weakReference.get();
            if (activity == null || activity.isFinishing()) continue;
            activities.add(activity);
        }
        return activities;
    }

    /**
     * get Activie activity
     * @return
     */
    public Activity getActiveActivity() {
        if (mActivitiesMap == null) return null;
        Set<String> keySet = mActivitiesMap.keySet();
        for (String key : keySet) {
            WeakReference<Activity> weakReference = mActivitiesMap.get(key);
            if (weakReference == null) continue;
            Activity activity = weakReference.get();
            if (activity == null) continue;
            if (activity instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) activity;
                if (baseActivity.isActive()) return baseActivity;
            }
        }
        return null;
    }






}
