package com.example.tchl.liaomei.ui.base;

import com.example.tchl.liaomei.widget.MultiSwipeRefreshLayout;

/**
 * Created by happen on 2016/5/31.
 */
public interface SwipeRefreshLayer {
    void requestDataRefresh();

    void setRefresh(boolean refresh);

    void setProgressViewOffset(boolean scale, int start, int end);

    void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback callback);
}
