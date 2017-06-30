package com.leezp.android.vmovie.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/19.
 */

public class SeriesDataBean implements Serializable{

    private String seriesid;

    private String title;

    private String weekly;

    private String image;

    private String content;

    private int update_to;

    private String follower_num;

    public String getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(String seriesid) {
        this.seriesid = seriesid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(int update_to) {
        this.update_to = update_to;
    }

    public String getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(String follower_num) {
        this.follower_num = follower_num;
    }
}
