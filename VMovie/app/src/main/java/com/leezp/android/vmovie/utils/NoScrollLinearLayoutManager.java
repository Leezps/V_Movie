package com.leezp.android.vmovie.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Administrator on 2017/6/23.
 */

public class NoScrollLinearLayoutManager extends LinearLayoutManager{

    private boolean isScrollEnabled = true;

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        isScrollEnabled = scrollEnabled;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollHorizontally();
    }
}
