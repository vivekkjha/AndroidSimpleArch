package com.mds.android.web.okhttp;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by vivekjha on 14/06/16.
 */
public interface IApiProgress {

    @NonNull
    Activity getActivityContext();
    String getDescription();
    void onProgressEnd();
    void onProgressStart();
    boolean isCancellable();
}
