package com.example.tchl.liaomei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.tchl.liaomei.Constants;
import com.example.tchl.liaomei.R;
import com.example.tchl.liaomei.data.entity.Liaomei;
import com.example.tchl.liaomei.func.OnLiaomeiTouchListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.tchl.liaomei.widget.RatioImageView;
/**
 * Created by tchl on 2016-05-26.
 */
public class LiaomeiListAdapter extends RecyclerView.Adapter<LiaomeiListAdapter.ViewHolder> {

    public static final String TAG = "LiaomeiListAdapter";
    private List<Liaomei> mList;
    private Context mContext;
    private OnLiaomeiTouchListener mOnLiaomeiTouchListener;

    public LiaomeiListAdapter(Context context,List<Liaomei> liaomeiList){
        mList = liaomeiList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liaomei,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int position){
        Liaomei liaomei = mList.get(position);
        String text = liaomei.desc.length() > Constants.limit ? liaomei.desc.substring(0,Constants.limit)
                +Constants.ellipsis : liaomei.desc;
        Log.e(TAG,"text:"+text);
        viewHolder.liaomei = liaomei;
        viewHolder.titleView.setText(text);
        viewHolder.card.setTag(liaomei.desc);
        Glide.with(mContext)
                .load(liaomei.url)
                .centerCrop()
                .into(viewHolder.liaomeiView);
    }

    @Override public void onViewRecycled(ViewHolder  holder){
        super.onViewRecycled(holder);
        Log.i(TAG,"onViewRecycled");
    }

    @Override public int getItemCount(){  return mList.size(); }

    public void setOnLiaomeiTouchListener(OnLiaomeiTouchListener onLiaomeiTouchListener){
        this.mOnLiaomeiTouchListener = onLiaomeiTouchListener;
    }

    //
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.iv_liaomei) RatioImageView liaomeiView;
        @Bind(R.id.tv_title) TextView titleView;
        View card;
        Liaomei liaomei;
        public ViewHolder(View itemView){
            super(itemView);
            card = itemView;
            ButterKnife.bind(this,itemView);
            liaomeiView.setOnClickListener(this);
            card.setOnClickListener(this);
            liaomeiView.setOriginalSize(50,50);
        }

        @Override public void onClick(View v){
            mOnLiaomeiTouchListener.onTouch(v,liaomeiView,card,liaomei);
        }

    }
}
