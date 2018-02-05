package com.mds.android.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mds.mdstoolkit.model.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import mds.mdstoolkit.commons.InternetUtil;


/**
 * Created by vivekjha on 09/02/16.
 * this reliever will relieve the intent of internet status change also set the event to event bus to populate to all the active classes
 */
public class InternetStatusReceiver extends BroadcastReceiver {

    private static String TAG  = InternetStatusReceiver.class.getName();
    private static boolean isOnline;

    @Override
    public void onReceive(Context context, Intent intent) {
        isOnline = InternetUtil.isNetworkAvailable(context);
        MessageEvent messageEvent = new MessageEvent(MessageEvent.NETWORK_STATUS_CHANGED);
        messageEvent.setData(isOnline);
        EventBus.getDefault().post(messageEvent);
    }
}
