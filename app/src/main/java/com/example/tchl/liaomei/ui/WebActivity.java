package com.example.tchl.liaomei.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.ui.base.ToolbarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tchl on 2016-06-28.
 */
public class WebActivity extends ToolbarActivity {
    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";
    private String mUrl, mTitle;

    @Bind(R.id.tv_title)
    TextSwitcher mTextSwitcher;

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
        ButterKnife.bind(this);
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                //textView.setTextAppearance();
                textView.setSingleLine(true);
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setSelected(true);
                    }
                },5000);
                return textView;
            }
        });
        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        if (mTitle != null) setTitle(mTitle);
    }


    @Override public void setTitle(CharSequence title) {
        super.setTitle(title);
        mTextSwitcher.setText(title);
    }

    @Override protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

}
