package com.example.tchl.liaomei.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tchl.liaomei.DrakeetFactory;
import com.example.tchl.liaomei.ui.GankFragment;

import java.util.Date;

/**
 * Created by tchl on 2016-06-26.
 */
public class GankPagerAdapter extends FragmentPagerAdapter{
   Date mDate;
   public GankPagerAdapter(FragmentManager fm,Date date){
       super(fm);
       mDate= date;
   }
    @Override
    public Fragment getItem(int position) {
        return GankFragment.newInstance();
    }

    @Override
    public int getCount() {
        return DrakeetFactory.gankSize;
    }
}
