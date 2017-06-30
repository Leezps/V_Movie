package com.leezp.android.vmovie.bean;

/**
 * Created by Administrator on 2017/6/21.
 */

public class VideoDataBean {

    private String postid;

    private String count_comment;

    private String count_like;

    private String count_share;

    private VideoDataContentBean content;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getCount_comment() {
        return count_comment;
    }

    public void setCount_comment(String count_comment) {
        this.count_comment = count_comment;
    }

    public String getCount_like() {
        return count_like;
    }

    public void setCount_like(String count_like) {
        this.count_like = count_like;
    }

    public String getCount_share() {
        return count_share;
    }

    public void setCount_share(String count_share) {
        this.count_share = count_share;
    }

    public VideoDataContentBean getContent() {
        return content;
    }

    public void setContent(VideoDataContentBean content) {
        this.content = content;
    }
}
