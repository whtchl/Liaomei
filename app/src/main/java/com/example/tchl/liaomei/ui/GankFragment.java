package com.example.tchl.liaomei.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tchl.liaomei.R;

import butterknife.ButterKnife;

/**
 * Created by tchl on 2016-06-26.
 */
public class GankFragment extends Fragment{

    private  final String TAG="GankFragment";
    public static GankFragment newInstance(){
        GankFragment fragment = new GankFragment();
        return fragment;
    }
    public GankFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
