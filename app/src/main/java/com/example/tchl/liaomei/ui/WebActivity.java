package com.example.tchl.liaomei.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.tchl.liaomei.Constants;
import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.ui.base.ToolbarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.daimajia.numberprogressbar.NumberProgressBar;
/**
 * Created by tchl on 2016-06-28.
 */
public class WebActivity extends ToolbarActivity {
    public static final String TAG="WebActivity";
    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";
    private String mUrl, mTitle;

    @Bind(R.id.tv_title)
    TextSwitcher mTextSwitcher;

    @Bind(R.id.webView)
    WebView mWebView;

    @Bind(R.id.progressbar) NumberProgressBar mProgressbar;

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

    @Override public boolean canBack() {
        return true;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);  //是否支持javascript
        settings.setLoadWithOverviewMode(true);  //
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度    2.NORMAL：正常显示不做任何渲染   3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中
        settings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放。
        settings.setSupportZoom(true);  // 支持缩放


        settings.setBuiltInZoomControls(true);//显示放大缩小 controler
        settings.setDisplayZoomControls(false);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new LoveClient());

        mWebView.loadUrl(mUrl);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setTextAppearance(getApplicationContext(),R.style.WebTitle);
                textView.setSingleLine(true);
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.setSelected(true);
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setSelected(true);
                    }
                }, Constants.MARQUEE_DELAY);
                return textView;
            }
        });
        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        if (mTitle != null) setTitle(mTitle);
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if(mProgressbar !=null){
                mProgressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressbar.setVisibility(View.GONE);
                } else {
                    mProgressbar.setVisibility(View.VISIBLE);
                }
            }
        }
        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    private class LoveClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            Log.e(TAG,"tchl shouldOverrideUrlLoading");
            return true;
        }
    }

    @Override public void setTitle(CharSequence title) {
        super.setTitle(title);
        Log.e(TAG,"tchl title:"+title);
        mTextSwitcher.setText(title);
    }

    @Override protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

}
