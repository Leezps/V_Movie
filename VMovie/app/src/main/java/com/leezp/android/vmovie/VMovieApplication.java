package com.leezp.android.vmovie;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/15.
 */

public class VMovieApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化各种框架
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
