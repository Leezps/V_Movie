package com.leezp.android.vmovie.bean;

import java.util.List;

/**
 * Created by Leezp on 2017/6/17 0017.
 */

public class LatestNormalBean {

    private int status;

    private String msg;

    private List<LatestNormalDataBean> data;

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

    public List<LatestNormalDataBean> getData() {
        return data;
    }

    public void setData(List<LatestNormalDataBean> data) {
        this.data = data;
    }
}
