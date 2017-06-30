package com.leezp.android.vmovie.bean;


/**
 * Created by Administrator on 2017/6/21.
 */

public class VideoBean {

    private int status;

    private String msg;

    private VideoDataBean data;

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

    public VideoDataBean getData() {
        return data;
    }

    public void setData(VideoDataBean data) {
        this.data = data;
    }
}
