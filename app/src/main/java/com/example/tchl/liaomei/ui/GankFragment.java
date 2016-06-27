package com.example.tchl.liaomei.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.bumptech.glide.Glide;
import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.data.GankData;
import com.example.tchl.liaomei.data.entity.Gank;
import com.example.tchl.liaomei.ui.adapter.GankListAdapter;
import com.example.tchl.liaomei.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by tchl on 2016-06-26.
 */
public class GankFragment extends Fragment{
    int mYear, mMonth, mDay;
    private  final String TAG="GankFragment";
    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";
    List<Gank> mGankList;
    GankListAdapter mAdapter;
    Subscription mSubscription;
    @Bind(R.id.rv_gank)
    RecyclerView mRecyclerView;

    @Bind(R.id.stub_empty_view)
    ViewStub mEmptyViewStub;
    public static GankFragment newInstance(int year, int month, int day){

        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YEAR, year);
        args.putInt(ARG_MONTH, month);
        args.putInt(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }
    public GankFragment(){
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGankList = new ArrayList<>();
        mAdapter = new GankListAdapter(mGankList);
        parseArguments();
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void parseArguments() {
        Bundle bundle = getArguments();
        mYear = bundle.getInt(ARG_YEAR);
        mMonth = bundle.getInt(ARG_MONTH);
        mDay = bundle.getInt(ARG_DAY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }


    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"onViewCreated:"+mGankList.size() );
        if (mGankList.size() == 0) loadData();
/*        if (mVideoPreviewUrl != null) {
            Glide.with(this).load(mVideoPreviewUrl).into(mVideoImageView);
        }*/
    }


    private void loadData() {
      /*  loadVideoPreview();*/
        // @formatter:off
        mSubscription = BaseActivity.sGankIO
                .getGankData(mYear, mMonth, mDay)
                .map(new Func1<GankData, GankData.Result>() {
                         @Override
                         public GankData.Result call(GankData gankData) {
                             return gankData.results;
                         }
                     }
                )
                .map(new Func1<GankData.Result, List<Gank>>() {
                    @Override
                    public  List<Gank> call(GankData.Result result) {
                        return addAllResults(result);
                }
                }
                )//  this::addAllResults
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Gank>>() {
                    @Override
                    public void call(List<Gank> ganks) {
                        if(ganks.isEmpty()){
                            Log.e(TAG,"tchl gank s empty. showEmptyView");
                            showEmptyView();
                        }
                        else{
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                },Throwable::printStackTrace);
              /*  .subscribe(list -> {
                    if (list.isEmpty()) {showEmptyView();}
                    else {mAdapter.notifyDataSetChanged();}
                }, Throwable::printStackTrace);*/
        // @formatter:on
    }

    private List<Gank> addAllResults(GankData.Result results) {
        if (results.androidList != null) mGankList.addAll(results.androidList);
        if (results.iOSList != null) mGankList.addAll(results.iOSList);
        if (results.appList != null) mGankList.addAll(results.appList);
        if (results.拓展资源List != null) mGankList.addAll(results.拓展资源List);
        if (results.瞎推荐List != null) mGankList.addAll(results.瞎推荐List);
        if (results.休息视频List != null) mGankList.addAll(0, results.休息视频List);
        Log.e(TAG,"addAllResult:"+ mGankList.size());

        return mGankList;
    }
    private void showEmptyView() {mEmptyViewStub.inflate();}
}
