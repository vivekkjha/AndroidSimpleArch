package com.mds.android.framework.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mds.android.R;

/**
 * Created by vivekjha on 25/07/16.
 */
public class SampleFragment extends BaseFragment {

    private Activity mActivity;

    public static SampleFragment newInstance(String name) {

        Bundle args = new Bundle();
        args.putString("content", name);
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Activity getFragmentActivity() {
        return mActivity;
    }

    @Override
    protected void initializeViews(View mFragmentView) {
        TextView textView = (TextView) mFragmentView.findViewById(R.id.textView);
        textView.setText(getArguments().getString("content"));
    }

    @Override
    protected int initializeLayoutId() {
        return R.layout.layout_fragment;
    }

    @Override
    protected View getFragmentView() {
        return super.getFragmentView();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }
}
