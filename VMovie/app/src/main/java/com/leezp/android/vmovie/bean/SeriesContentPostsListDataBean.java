package com.leezp.android.vmovie.bean;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SeriesContentPostsListDataBean {

    private int series_postid;

    private String number;

    private String title;

    private String addtime;

    private int duration;

    private String thumbnail;

    private String source_link;

    public int getSeries_postid() {
        return series_postid;
    }

    public void setSeries_postid(int series_postid) {
        this.series_postid = series_postid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSource_link() {
        return source_link;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }
}
