package com.example.tchl.liaomei.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.tchl.liaomei.R;

/**
 * Created by tchl on 2016-06-28.
 */
public class WebActivity extends ToolbarActivity {
    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web;
    }

    public static Intent newIntent(Context context, String url, String desc){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra(EXTRA_TITLE,desc);
        intent.putExtra(EXTRA_URL,url);
        return intent;
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
