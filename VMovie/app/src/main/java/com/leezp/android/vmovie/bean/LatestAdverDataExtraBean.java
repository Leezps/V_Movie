package com.leezp.android.vmovie.bean;

import java.io.Serializable;

/**
 * Created by Leezp on 2017/6/17 0017.
 */

public class LatestAdverDataExtraBean implements Serializable{

    private int app_banner_type;

    private String app_banner_param;

    public int getApp_banner_type() {
        return app_banner_type;
    }

    public void setApp_banner_type(int app_banner_type) {
        this.app_banner_type = app_banner_type;
    }

    public String getApp_banner_param() {
        return app_banner_param;
    }

    public void setApp_banner_param(String app_banner_param) {
        this.app_banner_param = app_banner_param;
    }
}
