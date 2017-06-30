package com.leezp.android.vmovie.bean;

import java.util.List;

/**
 * Created by Leezp on 2017/6/17 0017.
 */

public class LatestAdverBean {

    private int status;

    private String msg;

    private List<LatestAdverDataBean> data;

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

    public List<LatestAdverDataBean> getData() {
        return data;
    }

    public void setData(List<LatestAdverDataBean> data) {
        this.data = data;
    }
}
