package com.leezp.android.vmovie.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.SeriesContentViewRecyclerAdapter;
import com.leezp.android.vmovie.bean.SeriesContentBean;
import com.leezp.android.vmovie.bean.SeriesContentDataBean;
import com.leezp.android.vmovie.bean.SeriesContentPostsDataBean;
import com.leezp.android.vmovie.bean.SeriesContentPostsListDataBean;
import com.leezp.android.vmovie.bean.SeriesDataBean;
import com.leezp.android.vmovie.constants.HttpRequestParams;
import com.leezp.android.vmovie.constants.SeriesPageParams;
import com.leezp.android.vmovie.impl.SeriesCallBackImpl;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SeriesContentActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    private ImageView mBack;
    private TextView mSeriesTitle;
    private TextView mUpdateTime;
    private TextView mContent;
    private TextView mUpdateTo;
    private TextView mShareNum;
    private TextView mMessageNum;
    private TabLayout mEpisodeTab;
    private RecyclerView mEpisodeContent;
    private SeriesDataBean series_data;
    private TextView mTagName;
    private SeriesContentViewRecyclerAdapter adapter;
    private SeriesContentDataBean data;
    private WebView mWebVideo;
    private TextView mVideoTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_series_content;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.activity_series_content_back);
        mWebVideo = (WebView) findViewById(R.id.activity_series_content_webView);
        mVideoTitle = (TextView) findViewById(R.id.activity_series_content_currentVideo_title);
        mSeriesTitle = (TextView) findViewById(R.id.activity_series_content_series_title);
        mUpdateTime = (TextView) findViewById(R.id.activity_series_content_update_time);
        mTagName = (TextView) findViewById(R.id.activity_series_content_tag_name);
        mContent = (TextView) findViewById(R.id.activity_series_content_content);
        mUpdateTo = (TextView) findViewById(R.id.activity_series_content_update_to);
        mShareNum = (TextView) findViewById(R.id.activity_series_content_share_num);
        mMessageNum = (TextView) findViewById(R.id.activity_series_content_message_num);
        mEpisodeTab = (TabLayout) findViewById(R.id.activity_series_content_episode);
        mEpisodeContent = (RecyclerView) findViewById(R.id.activity_series_content_recycler);

        mBack.setOnClickListener(this);
        //设置WebView能够播放视频
        WebSettings settings = mWebVideo.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSaveFormData(false);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLoadWithOverviewMode(false);
        settings.setUseWideViewPort(true);
        //为WebView设置自己的浏览客户端
        mWebVideo.setWebViewClient(new WebViewClient());
        //加载进度以及JS调用监控的客户端
        mWebVideo.setWebChromeClient(new WebChromeClient());

        Intent intent = getIntent();
        series_data = (SeriesDataBean) intent.getSerializableExtra("SERIES_DATA");
        mSeriesTitle.setText(series_data.getTitle());
        mUpdateTime.setText("更新：" + series_data.getWeekly());
        mContent.setText(series_data.getContent());
        mUpdateTo.setText("集数：更新至" + series_data.getUpdate_to() + "集");
        mShareNum.setText("235");
        mMessageNum.setText("28");

        LinearLayout linearLayout = (LinearLayout) mEpisodeTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.behind_tab_cut_off_rule));
        linearLayout.setDividerPadding(SeriesPageParams.SERIES_CONTENT_TAB_PADDING);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        mEpisodeContent.setLayoutManager(layout);

        getData();

        adapter = new SeriesContentViewRecyclerAdapter(null, new SeriesCallBackImpl() {
            @Override
            public void updateVideoWebView(String url, String videoTitle) {
                Toast.makeText(mEpisodeContent.getContext(), "更新界面" + url, Toast.LENGTH_SHORT).show();
                mWebVideo.loadUrl(url);
                mVideoTitle.setText(videoTitle);
            }
        });
        mEpisodeContent.setAdapter(adapter);
        mEpisodeTab.addOnTabSelectedListener(this);
    }

    private void getData() {
        RequestParams requestParams = new RequestParams(String.format(HttpRequestParams.SERIES_CONTENT, series_data.getSeriesid()));
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                data = gson.fromJson(result, SeriesContentBean.class).getData();
                mTagName.setText(data.getTag_name());
                List<SeriesContentPostsDataBean> posts = data.getPosts();
                for (SeriesContentPostsDataBean post : posts) {
                    mEpisodeTab.addTab(mEpisodeTab.newTab().setText(post.getFrom_to()));
                }
                List<SeriesContentPostsListDataBean> list = posts.get(0).getList();
                SeriesPageParams.SERIES_CONTENT_POST_ID = list.get(0).getSeries_postid();
                adapter.updateSeriesContentData(list);
                mWebVideo.loadUrl(list.get(0).getSource_link());
                mVideoTitle.setText("第"+list.get(0).getNumber()+"集 "+list.get(0).getTitle());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_series_content_back:
                finish();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        List<SeriesContentPostsDataBean> posts = data.getPosts();
        adapter.updateSeriesContentData(posts.get(position).getList());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //恢复播放
        mWebVideo.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //暂停播放
        mWebVideo.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //一定要销毁，否则无法停止播放
        mWebVideo.destroy();
    }
}
