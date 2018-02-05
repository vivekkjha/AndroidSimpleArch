package com.mds.android.common.data;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vivekjha on 02/12/16.
 */
public class DataHolder {

    //> Hold data against Id
    private Object data;

    //> Hold data object against a key
    private Map<String,Object> map = new HashMap<String,Object>();

    private static DataHolder ourInstance;

    public static DataHolder getInstance() {
        if(ourInstance==null)
            ourInstance = new DataHolder();
        return ourInstance;
    }

    private DataHolder() {
    }

    private void save(Object data) {
        this.data = data;
    }

    private void save(String key, Object data) {
        this.map.put(key,data);
    }

    private Object retrieve() {
        return this.data;
    }

    private Object retrieve(String key) {
        return map.get(key);
    }


}

