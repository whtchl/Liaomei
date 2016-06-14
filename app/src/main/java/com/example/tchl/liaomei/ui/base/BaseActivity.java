package com.example.tchl.liaomei.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.tchl.liaomei.DrakeetFactory;
import com.example.tchl.liaomei.GankApi;
import com.example.tchl.liaomei.R;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
/**
 * Created by tchl on 2016-05-24.
 */
public class BaseActivity extends AppCompatActivity {

    public static final GankApi sGankIO = DrakeetFactory.getGankIOSingleton();
    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscription getmCompositeSubscription(){
        if(this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s){
        if(this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

   // @Override public boolean onOptionItemSelected(MenuItem item){
        //int id = item.getItemId();
  /*      switch(id){
            //case R.id.action_about:
                ;
            //case R.id.action_login:
                ;
                return true;
        }*/
     //   return super.onOptionsItemSelected(item);
   // }

    protected void loginGitHub(){
        ;
    }

    @Override protected void onDestroy(){
        super.onDestroy();
    if(this.mCompositeSubscription != null){
        this.mCompositeSubscription.unsubscribe();
    }
    }
}
