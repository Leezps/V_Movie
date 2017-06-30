package com.leezp.android.vmovie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SeriesContentPostsDataBean {

    private String from_to;

    private List<SeriesContentPostsListDataBean> list;

    public String getFrom_to() {
        return from_to;
    }

    public void setFrom_to(String from_to) {
        this.from_to = from_to;
    }

    public List<SeriesContentPostsListDataBean> getList() {
        return list;
    }

    public void setList(List<SeriesContentPostsListDataBean> list) {
        this.list = list;
    }
}
