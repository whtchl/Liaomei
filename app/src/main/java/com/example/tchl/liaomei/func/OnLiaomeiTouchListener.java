package com.example.tchl.liaomei.func;

import android.view.View;

import com.example.tchl.liaomei.data.entity.Liaomei;

/**
 * Created by tchl on 2016-05-26.
 */
public interface OnLiaomeiTouchListener {
    void onTouch(View v, View liaomeiView, View card, Liaomei liaomei);
}
