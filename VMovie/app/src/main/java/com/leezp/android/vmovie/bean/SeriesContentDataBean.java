package com.leezp.android.vmovie.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SeriesContentDataBean {

    private String tag_name;

    private List<SeriesContentPostsDataBean> posts;

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public List<SeriesContentPostsDataBean> getPosts() {
        return posts;
    }

    public void setPosts(List<SeriesContentPostsDataBean> posts) {
        this.posts = posts;
    }
}
