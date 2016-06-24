package com.example.tchl.liaomei.ui.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.tchl.liaomei.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tchl on 2016-06-24.
 */
public class GankActivity extends ToolbarActivity {

    public static final String EXTRA_GANK_DATE = "gank_date";
    public static final String TAG = "GankActivity";
    Date mGankDate;
/*    @Bind(R.id.pager)
    ViewPager mViewPager;*/
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mGankDate = (Date)getIntent().getSerializableExtra(EXTRA_GANK_DATE);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Log.e(TAG,"date:"+dateFormat.format(mGankDate));
        setTitle(dateFormat.format(mGankDate));
      //  setTitle(new SimpleDateFormat("yyyy/mm/dd").format(mGankDate));
    }

    @Override
    protected int  provideContentViewId(){
        return R.layout.activity_gank;
    }

    @Override public boolean canBack() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
