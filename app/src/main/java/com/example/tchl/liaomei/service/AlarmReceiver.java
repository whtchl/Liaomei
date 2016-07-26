package com.example.tchl.liaomei.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.ui.MainActivity;
import com.example.tchl.liaomei.util.HeadsUps;
import com.example.tchl.liaomei.util.PreferencesLoader;

/**
 * Created by tchl on 2016-07-08.
 */
public class AlarmReceiver extends BroadcastReceiver{
    public static final String TAG = "AlarmReceiver";
    @Override public void onReceive(Context context, Intent intent) {
        PreferencesLoader loader = new PreferencesLoader(context);
        Log.e(TAG,"ddd");
        if (loader.getBoolean(R.string.action_notifiable, true)) {
            Log.e(TAG,"每天中午提醒");
            HeadsUps.show(context, MainActivity.class,
                    context.getString(R.string.headsup_title),
                    context.getString(R.string.headsup_content),
                    R.mipmap.ic_meizhi_150602, R.mipmap.ic_female, 123123);
        }
    }
}
