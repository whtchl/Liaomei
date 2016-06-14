package com.example.tchl.liaomei;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.litesuits.orm.LiteOrm;
import com.example.tchl.liaomei.util.ToastUtils;
/**
 * Created by tchl on 2016-05-24.
 */
public class App extends Application {

    private static final String DB_NAME = "gank.db";
    public static Context sContext;
    public static LiteOrm sDb;
    @Override public void onCreate() {
        super.onCreate();
        sContext = this;
        ToastUtils.register(this);
            Log.e("TAG","tchl create gandk.db");
        sDb = LiteOrm.newSingleInstance(this, DB_NAME);
        if (BuildConfig.DEBUG) {
            sDb.setDebugged(true);
        }
    }
    @Override public void onTerminate(){ super.onTerminate(); }
}
