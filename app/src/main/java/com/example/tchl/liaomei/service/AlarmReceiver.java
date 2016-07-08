package com.example.tchl.liaomei.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.ui.MainActivity;
import com.example.tchl.liaomei.util.PreferencesLoader;

/**
 * Created by tchl on 2016-07-08.
 */
public class AlarmReceiver extends BroadcastReceiver{

    @Override public void onReceive(Context context, Intent intent) {
        PreferencesLoader loader = new PreferencesLoader(context);
        if (loader.getBoolean(R.string.action_notifiable, true)) {
            HeadsUps.show(context, MainActivity.class,
                    context.getString(R.string.headsup_title),
                    context.getString(R.string.headsup_content),
                    R.mipmap.ic_meizhi_150602, R.mipmap.ic_female, 123123);
        }
    }
}
