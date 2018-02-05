package com.mds.android.framework.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mds.android.R;
import com.mds.android.common.app.MdsApplication;
import com.mds.android.common.app.Prefs;
import com.mds.android.common.listener.ISlidingPanel;
import com.mds.android.common.utils.LogUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import mds.mdstoolkit.model.MessageEvent;


/**
 * Created by vivekjha on 04/05/16.
 * Base Activity to be inherited by all other activities of app directly or indirectly
 * CODING TIP : Always prepend 'm' before names of member variables
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();
    protected abstract void initializeViews(Bundle bundle);

    //> layout objects
    private LinearLayout progressLayout;
    private TextView txt_plsWait;

    private FragmentManager mFragmentManager;
    private Prefs mPrefs;
    private EventBusImpl mEventBus;
    private SlidingUpPanelLayout mSldingUpPanelLayout;
    private boolean isActive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_frame);

        FrameLayout contentLayout = (FrameLayout) findViewById(R.id.base_frame);
        FrameLayout slidingLayout = (FrameLayout)findViewById(R.id.sliding_frame_wrapper);
        mSldingUpPanelLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);

        int id = getLayoutId();
        View contentView = getLayoutInflater().inflate(id, null);
        contentLayout.addView(contentView);
        if(getSlidingPanel()!=null)
        {
            View slidingView = getLayoutInflater().inflate(getSlidingPanel().getSlidingLayoutId(), null);
            slidingLayout.addView(slidingView);
            getSlidingPanel().slidingPanelLayout(mSldingUpPanelLayout);
            mSldingUpPanelLayout.setPanelSlideListener(getSlidingPanel());

        }
        initializeViews(savedInstanceState);
    }

    /**
     * Child classes need to override this method to enable sliding panel
     * @return object, if needs to enable ; null otherwise (By Default)
     */
    protected ISlidingPanel getSlidingPanel()
    {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
        this.ensureEventBus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    protected void onStop() {
        if(mEventBus!=null)
            EventBus.getDefault().unregister(mEventBus);
        super.onStop();
    }

    /**
     * Ensure event bus
     */
    private void ensureEventBus() {
        if (mEventBus != null) {
            EventBus.getDefault().unregister(mEventBus);
        }
        mEventBus = new EventBusImpl(this);
        EventBus.getDefault().register(mEventBus);
    }


    @Override
    public void onBackPressed() {

       if(getSlidingPanel()!=null && mSldingUpPanelLayout.getPanelState().equals(SlidingUpPanelLayout.PanelState.EXPANDED)) {
           mSldingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
           return;
       }
        super.onBackPressed();

    }

    @NonNull
    public String getActivityKey() {
        return BaseActivity.this.getClass().getSimpleName();
    }

    public boolean isSingleInstance() {
        return true;
    }

    public boolean killAllActivity() {
        return false;
    }

    public boolean isActive() {
        return active;
    }



    /**
     * Initialize Toolbar for children activities
     * @param titleStringResource String resource id
     *//*
    protected Toolbar initToolBar(int titleStringResource) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(titleStringResource);
        }
        return toolbar;
    }
    *//**
     * Initialize Toolbar for children activities
     * @param titleString String to show as title
     *//*
    protected Toolbar initToolBar(String titleString) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(titleString);
        }
        return toolbar;
    }*/


    /**
     * Show progress layout
     *
     * @param text resource id of text to display while loading
     */
    public void showProgressLayout(int text) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        txt_plsWait = (TextView) findViewById(R.id.txt_plsWait);
        progressLayout.setVisibility(View.VISIBLE);
        txt_plsWait.setVisibility(View.VISIBLE);
        txt_plsWait.setText(getString(R.string.please_wait) + getString(text));
    }

    public void showProgressLayout(String text) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        txt_plsWait = (TextView) findViewById(R.id.txt_plsWait);
        progressLayout.setVisibility(View.VISIBLE);
        txt_plsWait.setVisibility(View.VISIBLE);
        txt_plsWait.setText(getString(R.string.please_wait) + text);
    }

    /**
     * Show progress layout Default
     */
    public void showProgressLayout() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        txt_plsWait = (TextView) findViewById(R.id.txt_plsWait);
        progressLayout.setVisibility(View.VISIBLE);
        txt_plsWait.setVisibility(View.VISIBLE);
    }

    /**
     * Hide progress layout from screen
     */
    public void hideProgressLayout() {
        if (progressLayout != null) {
            progressLayout.setVisibility(View.GONE);
        }
    }

    /**
     * child classes need to override this method to get notified of connection changed
     * @param isOnline true, if online false otherwise
     */
    protected void onConnectionChanged(boolean isOnline)
    {
        LogUtil.d(BaseActivity.class.getSimpleName(),"On Connection changed - online ?: " + isOnline);
    }


    public DisplayImageOptions getImageLoaderOptions() {
        return MdsApplication.getInstance().getImageLoaderOptions();
    }

    public com.nostra13.universalimageloader.core.ImageLoader getImageLoader() {
        return MdsApplication.getInstance().getImageLoader();
    }


    /**
     * Event bus class to record Events and generate
     */
    public class EventBusImpl {
        Context context;

        public EventBusImpl(Context context) {
            this.context = context;
        }

        @Subscribe
        public void onEventMainThread(MessageEvent messageEvent) {
            if (messageEvent.getType().equals(MessageEvent.NETWORK_STATUS_CHANGED)){

                boolean isConnectingOrConnected = (boolean) messageEvent.getData();
                onConnectionChanged(isConnectingOrConnected);
            }
        }
    }




}
