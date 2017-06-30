package com.leezp.android.vmovie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Leezp on 2017/6/17 0017.
 */

public class LatestNormalDataBean implements Serializable{

    private String postid;

    private String title;

    private String app_fu_title;

    private int is_album;

    private int duration;

    private int like_num;

    private int share_num;

    private String image;

    private List<LatestNormalDataCatesBean> cates;

    private String request_url;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApp_fu_title() {
        return app_fu_title;
    }

    public void setApp_fu_title(String app_fu_title) {
        this.app_fu_title = app_fu_title;
    }

    public int getIs_album() {
        return is_album;
    }

    public void setIs_album(int is_album) {
        this.is_album = is_album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<LatestNormalDataCatesBean> getCates() {
        return cates;
    }

    public void setCates(List<LatestNormalDataCatesBean> cates) {
        this.cates = cates;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }
}
