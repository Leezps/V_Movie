package com.leezp.android.vmovie.bean;

/**
 * Created by Administrator on 2017/6/24.
 */

public class SettingDataBean {

    private boolean isSwitch;

    private String title;

    private String info;

    public SettingDataBean(boolean isSwitch, String title, String info) {
        this.isSwitch = isSwitch;
        this.title = title;
        this.info = info;
    }

    public boolean isSwitch() {
        return isSwitch;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

}
