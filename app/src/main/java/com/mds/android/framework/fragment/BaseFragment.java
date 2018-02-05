package com.mds.android.framework.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vivekjha on 04/05/16.
 */
public abstract class BaseFragment extends Fragment {
    // Root view for this fragment
    private View mFragmentView;

    public BaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(initializeLayoutId(), null);
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentActivity();
        initializeViews(mFragmentView);
    }

    @Override
    public void onStop() {
        super.onStop();
    }



    /**
     * Get activity it is attached to
     */
    protected abstract Activity getFragmentActivity();
    /**
     * Initialize fragment views.
     */
    protected abstract void initializeViews(View mFragmentView);

    /**
     * @return returns layout id of the fragment.
     */
    protected abstract int initializeLayoutId();

    /**
     * @return root view of the fragment.
     */
    protected View getFragmentView() {
        return mFragmentView;
    }


}

