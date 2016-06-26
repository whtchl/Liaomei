package com.example.tchl.liaomei.ui.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.ui.adapter.GankPagerAdapter;

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
    GankPagerAdapter mPagerAdapter;
    @Bind(R.id.pager)
    ViewPager mViewPager;
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

        initViewPager();
        initTabLayout();
    }
    private void initTabLayout(){
        //mPagerAdapter.forEach(mpageradapter)->mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i=0; i<mPagerAdapter.getCount();i++){
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private  void initViewPager(){
         mPagerAdapter = new GankPagerAdapter(getSupportFragmentManager(),mGankDate);
         mViewPager.setAdapter(mPagerAdapter);
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
