package com.mds.android.common.data;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivekjha on 02/12/16.
 */
public class DataHolderHash {

    //> Hold data against Id
    private Map<String, WeakReference<Object>> data = new HashMap<String, WeakReference<Object>>();

    private static DataHolderHash ourInstance;

    public static DataHolderHash getInstance() {
        if(ourInstance==null)
            ourInstance = new DataHolderHash();
        return ourInstance;
    }

    private DataHolderHash() {
    }

    private void save(String key, Object object) {
        data.put(key, new WeakReference<Object>(object));
    }

    private Object retrieve(String key) {
        WeakReference<Object> objectWeakReference = data.get(key);
        return objectWeakReference.get();
    }


}

