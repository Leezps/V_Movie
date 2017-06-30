package com.leezp.android.vmovie.constants;

/**
 * Created by Leezp on 2017/6/17 0017.
 */

public class HttpRequestParams {

    public static final String HOST_URL = "http://app.vmoiver.com";

    public static final String LATEST_NORMAL_PATH = "/apiv3/post/getPostByTab";

    public static final String LATEST_ADVER_PATH = "/apiv3/index/getBanner";

    public static final String CHANNEL_DATA_PATH = "/apiv3/cate/getList";

    public static final String SERIES_DATA_PATH = "/apiv3/series/getList";

    public static final String BEHIND_DATA_PATH = "/apiv3/backstage/getCate";

    public static final String BEHIND_ITEM__PATH = "/apiv3/backstage/getPostByCate";

    public static final String WEB_URL = "http://app.vmoiver.com/%s?qingapp=app_new";

    public static final String VIDEO_URL = "http://www.vmovier.com/apiv3/post/view?postid=%s";

    public static final String CHANNEL_CONTENT = "http://www.vmovier.com/apiv3/post/getPostInCate?cateid=%d";

    public static final String SERIES_CONTENT = "http://app.vmoiver.com/apiv3/series/view?seriesid=%s";
}
