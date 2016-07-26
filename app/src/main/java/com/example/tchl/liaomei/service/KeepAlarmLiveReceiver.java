package com.example.tchl.liaomei.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tchl.liaomei.util.AlarmManagers;

/**
 * Created by happen on 2016/7/6.
 */
public class KeepAlarmLiveReceiver extends BroadcastReceiver {
    public static final String TAG = "KeepAlarmLiveReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            Log.e(TAG,"tchl onReceive");
            AlarmManagers.register(context);
        }
    }
}
