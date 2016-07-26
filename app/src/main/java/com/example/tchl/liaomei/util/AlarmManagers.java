package com.example.tchl.liaomei.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tchl.liaomei.service.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by tchl on 2016-07-26.
 */
public class AlarmManagers {
    public static  final  String TAG = "AlarmManager";
    public static void register(Context context) {
        Calendar today = Calendar.getInstance();
        Calendar now = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 18);
        today.set(Calendar.MINUTE, 00);
        today.set(Calendar.SECOND, 00);
        Log.e(TAG,"now time:"+now.getTime()+"  today:"+today.getTime());
        if (now.after(today)) {
            Log.e(TAG,"now.after(today) is true");
            return;
        }

        Intent intent = new Intent("me.liaomei.alarm");
        intent.setClass(context, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 520, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), broadcast);
        //manager.set(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), broadcast);
    }
}
