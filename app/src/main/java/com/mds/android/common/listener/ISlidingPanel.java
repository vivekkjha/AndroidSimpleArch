package com.mds.android.common.listener;


import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by vivekjha on 19/07/16.
 */
public interface ISlidingPanel extends SlidingUpPanelLayout.PanelSlideListener  {

    int getSlidingLayoutId();
    void slidingPanelLayout(SlidingUpPanelLayout slidingUpPanelLayout);

}
