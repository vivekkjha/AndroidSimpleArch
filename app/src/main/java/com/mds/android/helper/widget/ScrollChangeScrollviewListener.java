package com.mds.android.helper.widget;

import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 * Created by vivekjha on 06/08/16.
 */
public abstract class ScrollChangeScrollviewListener implements ViewTreeObserver.OnScrollChangedListener {

    private ScrollView mScrollView;
    private static final int SHOW_THRESHOLD = 200;
    private static final int FINISH_THRESHOLD = 400;
    private int lastScrollPosition = 0;

    public  ScrollChangeScrollviewListener(ScrollView scrollView)
    {
        this.mScrollView = scrollView;
    }
    @Override
    public void onScrollChanged() {
        int scrollY = mScrollView.getScrollY(); //for verticalScrollView
        int diffInScroll = scrollY - lastScrollPosition;
        if(scrollY>SHOW_THRESHOLD && scrollY<=FINISH_THRESHOLD)
        {
            if(diffInScroll>0)
            {
                /**
                 * Multiply scroll position to get alpha
                 */
                doActionScrollDown(scrollY);
            }else
                doActionScrollUp(scrollY);
        }

        lastScrollPosition = scrollY;
    }

    public abstract void doActionScrollDown(int yPos);
    public abstract void doActionScrollUp(int yPos);


}
