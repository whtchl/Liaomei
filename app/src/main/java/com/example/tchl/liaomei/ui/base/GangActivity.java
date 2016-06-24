package com.example.tchl.liaomei.ui.base;

import android.os.Bundle;

import com.example.tchl.liaomei.R;

/**
 * Created by tchl on 2016-06-24.
 */
public class GangActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int  provideContentViewId(){
        return R.layout.activity_gank;
    }
}
