package com.example.tchl.liaomei.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

import com.example.tchl.liaomei.App;
import com.example.tchl.liaomei.DrakeetFactory;
import com.example.tchl.liaomei.GankApi;
import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.data.LiaomeiData;
import com.example.tchl.liaomei.data.entity.Liaomei;
import com.example.tchl.liaomei.func.OnLiaomeiTouchListener;
import com.example.tchl.liaomei.ui.adapter.LiaomeiListAdapter;
import com.example.tchl.liaomei.ui.base.SwipeRefreshBaseActivity;
import com.example.tchl.liaomei.ui.base.ToolbarActivity;
import com.example.tchl.liaomei.util.Once;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
public class MainActivity extends SwipeRefreshBaseActivity {
    private static final String TAG = "MainActivity tchl";
    private static final int PRELOAD_SIZE = 6;

    @Bind(R.id.rv_meizhi)
    RecyclerView mRecyclerView;

    private LiaomeiListAdapter mLiaomeiListAdapter;
    private List<Liaomei> mLiaomeiList;
    private int mPage = 1;
    private boolean mLiaomeiBeTouched;
    @Override protected int provideContentViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mLiaomeiList = new ArrayList<>();
        QueryBuilder query = new QueryBuilder(Liaomei.class);
        query.appendOrderDescBy("publishedAt");
        query.limit(0,10);
        mLiaomeiList.addAll(App.sDb.query(query));
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mLiaomeiListAdapter = new LiaomeiListAdapter(this, mLiaomeiList);
        mRecyclerView.setAdapter(mLiaomeiListAdapter);
        new Once(this).show("tip_guide_6", () -> {
            Snackbar.make(mRecyclerView, getString(R.string.tip_guide), Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.i_know, v -> {
                    }).show();
        });

        mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));
        mLiaomeiListAdapter.setOnLiaomeiTouchListener(
               new OnLiaomeiTouchListener() {
                   @Override
                   public void onTouch(View v, View liaomeiView, View card, Liaomei liaomei) {
                       Log.e(TAG,"tchl onTouch0");
                       if(liaomei == null) return;
                       Log.e(TAG,"tchl onTouch1");
                       if (v==liaomeiView && !mLiaomeiBeTouched){
                           mLiaomeiBeTouched =  true;
                           Log.e(TAG,"tchl onTouch2");
                           Picasso.with(getApplicationContext()).load(liaomei.url).fetch(new Callback() {
                               @Override
                               public void onSuccess() {
                                   mLiaomeiBeTouched = false;
                                   Log.e(TAG,"tchl onTouch3");
                                   Intent intent =new Intent(getApplicationContext(), PictureActivity.class);
                                   startPictureActivity(liaomei,liaomeiView);
                                   //Toast.makeText(getApplicationContext(),"load pic",Toast.LENGTH_LONG);
                               }
                               @Override
                               public void onError() {
                                   mLiaomeiBeTouched = false;
                                   Log.e(TAG,"tchl onTouch4");
                                   //Toast.makeText(getApplicationContext(),"load error pic",Toast.LENGTH_LONG);
                               }
                           });
                       }
                   }
               }
       );
    }

    private RecyclerView.OnScrollListener getOnBottomListener(StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG,"isSlideToBottom:" + isSlideToBottom(recyclerView));
                if (!mSwipeRefreshLayout.isRefreshing() && isSlideToBottom(recyclerView)) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    mPage += 1;
                    loadData(false);
                }
            }
        };
    }
	
	private void startPictureActivity(Liaomei liaomei,View transitView){
        Intent intent = PictureActivity.newIntent(MainActivity.this,liaomei.url,liaomei.desc);
        ActivityOptionsCompat optionCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity.this,transitView,PictureActivity.TRANSIT_PIC);
        try{
            ActivityCompat.startActivity(MainActivity.this,intent,optionCompat.toBundle());
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            startActivity(intent);
        }
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        Log.e(TAG,"computeVerticalScrollExtent+computeVerticalScrollOffset>= computeVerticalScrollRange  ? ||"+
                recyclerView.computeVerticalScrollExtent() +"  + " + recyclerView.computeVerticalScrollOffset()+"  ?  "
                +recyclerView.computeVerticalScrollRange());
        if (recyclerView.computeVerticalScrollExtent()+ recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.e("TAG tchl","onPostCreate");
        new Handler().postDelayed(() -> setRefresh(true), 358);
        loadData(true);
    }

    private void loadData(boolean clean) {
        Log.e("TAG tchl", "loadData:"+clean);
        Subscription sb = sGankIO.getMeizhiData(mPage)
                .map(new Func1<LiaomeiData, List<Liaomei>>() {
                    @Override
                    public List<Liaomei> call(LiaomeiData liaomeiData) {

                        Log.e("TAG tchl", "map" + " results size:" + liaomeiData.results.size());
                        PrintLiaomei(liaomeiData);
                        return liaomeiData.results;

                    }
                })
                .doOnNext(
                        new Action1<List<Liaomei>>() {
                            @Override
                            public void call(List<Liaomei> liaomeis) {
                                Log.e("TAG tchl", "doOnNext");
                                saveLiaomeis(liaomeis);
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .finallyDo(
                        new Action0() {
                            @Override
                            public void call(){
                                Log.e("TAG tchl", "finallyDo setRefresh to false.");
                                setRefresh(false);
                            }
                        }
                )
                .subscribe(
                        new Action1<List<Liaomei>>() {
                            @Override
                            public void call(List<Liaomei> liaomeis) {
                                Log.e("TAG tchl", " subscribe");
                                if (clean) mLiaomeiList.clear();
                                Log.e(TAG, "LiaomeiList size = " +
                                        mLiaomeiList.size());
                                mLiaomeiList.addAll(liaomeis);
                                mLiaomeiListAdapter.notifyDataSetChanged();
                                setRefresh(false);

                            }
                        }
                        ,
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("TAG tchl", "tchl throwable");
                            }
                        }
                );
    }

    private void PrintLiaomei(LiaomeiData liaomeiData) {
        for (int i = 0; i < liaomeiData.results.size(); i++) {
            Log.e(TAG, "tchl123 desc: " + liaomeiData.results.get(i).desc);
            Log.e(TAG, "tchl123 type:" + liaomeiData.results.get(i).type);
            Log.e(TAG, "tchl123 url:" + liaomeiData.results.get(i).url);
            Log.e(TAG, "tchl123 who:" + liaomeiData.results.get(i).who);
            Log.e(TAG, "tchl123 objectId:" + liaomeiData.results.get(i).objectId);
            Log.e(TAG, "tchl123 createAt:" + liaomeiData.results.get(i).createAt);
            Log.e(TAG, "tchl123 updateAt: " + liaomeiData.results.get(i).updatedAt);
            Log.e(TAG, "tchl123 used:" + liaomeiData.results.get(i).used);
            Log.e(TAG, "tchl123 id:" + liaomeiData.results.get(i).id);
            Log.e(TAG, "tchl123 publidth:" + liaomeiData.results.get(i).publidth);
        }
    }

    private void saveLiaomeis(List<Liaomei> liaomeis) {
        Log.e("TAG", "tchl saveMeizhi");
        App.sDb.insert(liaomeis, ConflictAlgorithm.Replace);
    }

    @Override public void requestDataRefresh() {
        super.requestDataRefresh();
        mPage = 1;
        loadData(true);
    }



}
