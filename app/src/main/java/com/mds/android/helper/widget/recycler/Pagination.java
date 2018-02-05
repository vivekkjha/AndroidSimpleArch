package com.mds.android.helper.widget.recycler;

import java.util.List;

/**
 * Created by vivekjha on 21/08/16.
 */
public abstract class Pagination<T> {

    protected int currentPageIndex;

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    /**
     * Only asynchronous call for more data allowed under this method
     * @param pageIndex pageIndex
     * @param pageSize total pageSize to load
     * @return List of class variable T
     */
    public List<T> loadNext(int pageIndex, int pageSize) {
        currentPageIndex = pageIndex;
        return null;
    }

    public void onError(Exception exception){}
}

