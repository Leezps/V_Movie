package com.leezp.android.vmovie.bean;

import java.util.List;

/**
 * Created by Leezp on 2017/6/20 0020.
 */

public class BehindItemBean {

    private int status;

    private String msg;

    private List<BehindItemDataBean> data;

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

    public List<BehindItemDataBean> getData() {
        return data;
    }

    public void setData(List<BehindItemDataBean> data) {
        this.data = data;
    }
}
