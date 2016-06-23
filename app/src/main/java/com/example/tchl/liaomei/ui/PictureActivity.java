package com.example.tchl.liaomei.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.ui.base.ToolbarActivity;
import com.example.tchl.liaomei.util.RxLiaomei;
import com.example.tchl.liaomei.util.Shares;
import com.example.tchl.liaomei.util.Toasts;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tchl on 2016-06-21.
 */
public class PictureActivity extends ToolbarActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";
    String mImageUrl, mImageTitle;
    @Bind(R.id.picture)
    ImageView mImageView;

    @Override
    protected  int provideContentViewId(){  return R.layout.activity_picture;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        parseIntent();
        ViewCompat.setTransitionName(mImageView,TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mImageView);
        setAppBarAlpha(0.6f);
        setTitle(mImageTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_share:
                RxLiaomei.saveImageAndGetPathObservable(this,mImageUrl,mImageTitle)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uri -> Shares.shareImage(this, uri,
                        getString(R.string.share_liaomei_to)),
                        error ->Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show());

                     /*   new Subscriber<Uri>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(Uri uri) {
                        Shares.shareImage(getApplicationContext(),uri,getApplicationContext().getString(R.string.share_liaomei_to));
                    }
                });*/
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }

    public static Intent newIntent(Context context,String url,String desc){
        Intent intent = new Intent(context,PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL,url);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE,desc);
        return intent;
    }

    @Override protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

}
