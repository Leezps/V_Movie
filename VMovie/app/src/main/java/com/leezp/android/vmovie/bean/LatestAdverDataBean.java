package com.leezp.android.vmovie.bean;



/**
 * Created by Leezp on 2017/6/17 0017.
 */

public class LatestAdverDataBean{

    private String image;

    private LatestAdverDataExtraBean extra_data;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LatestAdverDataExtraBean getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(LatestAdverDataExtraBean extra_data) {
        this.extra_data = extra_data;
    }
}
