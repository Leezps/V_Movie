package com.leezp.android.vmovie.bean;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SeriesContentBean {

    private int status;

    private String msg;

    private SeriesContentDataBean data;

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

    public SeriesContentDataBean getData() {
        return data;
    }

    public void setData(SeriesContentDataBean data) {
        this.data = data;
    }
}
