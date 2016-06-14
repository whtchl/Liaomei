package com.example.tchl.liaomei.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by happen on 2016/5/26.
 */
public class RatioImageView extends ImageView {
    private int originalWidth;
    private int originalHeight;

    public RatioImageView(Context context){  super(context); }

    public RatioImageView(Context context,AttributeSet attrs){  super(context,attrs); }

    public RatioImageView(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    public void setOriginalSize(int originalWidth,int originalHeight){
        this.originalHeight = originalHeight;
        this.originalWidth = originalWidth;
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            Log.e("TAG","originalWidth:"+originalWidth+"  originalHeight:"+originalHeight);
            float ratio = (float) originalWidth / (float) originalHeight;
            Log.e("TAG","widthMeasureSpec:"+widthMeasureSpec+"  heightMeasureSpec:"+heightMeasureSpec);
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            Log.e("TAG","width:"+width+"  height:"+height);

            // TODO: 现在只支持固定宽度
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }

            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
