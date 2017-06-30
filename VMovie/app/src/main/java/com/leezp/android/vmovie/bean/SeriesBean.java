package com.leezp.android.vmovie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class SeriesBean {

    private int status;

    private String msg;

    private List<SeriesDataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SeriesDataBean> getData() {
        return data;
    }

    public void setData(List<SeriesDataBean> data) {
        this.data = data;
    }
}
