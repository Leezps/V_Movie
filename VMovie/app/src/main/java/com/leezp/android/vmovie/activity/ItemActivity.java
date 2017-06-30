package com.leezp.android.vmovie.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.bean.BehindItemDataBean;
import com.leezp.android.vmovie.bean.LatestAdverDataExtraBean;
import com.leezp.android.vmovie.bean.LatestNormalDataBean;
import com.leezp.android.vmovie.bean.VideoBean;
import com.leezp.android.vmovie.constants.AllPageTypeParams;
import com.leezp.android.vmovie.constants.HttpRequestParams;
import com.leezp.android.vmovie.utils.FullScreenVideoView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/6/21.
 */

public class ItemActivity extends BaseActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private ImageView mBack;
    private TextView mTitle;
    private ImageView mShare;
    private FrameLayout mMedia;
    private FullScreenVideoView mVideo;
    private CheckBox mPlay;
    private TextView mCurrentTime;
    private SeekBar mSeek;
    private TextView mTotal;
    private CheckBox mScreen;
    private WebView mWebView;
    private LinearLayout mFooter;
    private TextView mLikeNum;
    private TextView mShareNum;
    private TextView mMessage;
    private TextView mCache;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AllPageTypeParams.UPDATE_TIME:
                    //更新事件
                    int currentPosition = mVideo.getCurrentPosition();
                    mCurrentTime.setText(DateFormat.format("mm:ss", currentPosition));
                    mSeek.setProgress(currentPosition);

                    handler.sendEmptyMessageDelayed(AllPageTypeParams.UPDATE_TIME, 1000);
                    break;
                case AllPageTypeParams.VIDEO_CONTROLES_GONE:
                    //将Video控件隐藏
                    mControls.setVisibility(View.INVISIBLE);
                    mPlay.setClickable(false);
                    mSeek.setClickable(false);
                    mScreen.setClickable(false);
                    break;
            }
        }
    };
    private int mMedioHeight;
    private LinearLayout mHead;
    private FrameLayout mControls;
    private LinearLayout mLikePart;
    private LinearLayout mSharePart;
    private LinearLayout mMessagePart;
    private LinearLayout mCachePart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_item_content;
    }

    @Override
    protected void initView() {
        mHead = (LinearLayout) findViewById(R.id.activity_item_content_head);
        mBack = (ImageView) findViewById(R.id.activity_item_content_back);
        mTitle = (TextView) findViewById(R.id.activity_item_content_title);
        mShare = (ImageView) findViewById(R.id.activity_item_content_share);
        mMedia = (FrameLayout) findViewById(R.id.activity_item_content_media);
        mVideo = (FullScreenVideoView) findViewById(R.id.activity_item_content_video);
        mControls = (FrameLayout) findViewById(R.id.activity_item_content_video_controls);
        mPlay = (CheckBox) findViewById(R.id.activity_item_content_video_play);
        mCurrentTime = (TextView) findViewById(R.id.activity_item_content_video_currentTime);
        mSeek = (SeekBar) findViewById(R.id.activity_item_content_video_seek);
        mTotal = (TextView) findViewById(R.id.activity_item_content_video_totalTime);
        mScreen = (CheckBox) findViewById(R.id.activity_item_content_video_screen);
        mWebView = (WebView) findViewById(R.id.activity_item_content_webView);
        mFooter = (LinearLayout) findViewById(R.id.activity_item_content_footer);
        mLikePart = (LinearLayout) findViewById(R.id.activity_item_content_like_part);
        mLikeNum = (TextView) findViewById(R.id.activity_item_content_like_num);
        mSharePart = (LinearLayout) findViewById(R.id.activity_item_content_share_part);
        mShareNum = (TextView) findViewById(R.id.activity_item_content_share_num);
        mMessagePart = (LinearLayout) findViewById(R.id.activity_item_content_message_part);
        mMessage = (TextView) findViewById(R.id.activity_item_content_message_num);
        mCachePart = (LinearLayout) findViewById(R.id.activity_item_content_cache_part);
        mCache = (TextView) findViewById(R.id.activity_item_content_cache);

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("DATA");
        //设置WebChromeClient的客户端，然后将标题设置到页面上
        WebChromeClient chromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(title);
            }
        };
        if (data.getString("DATA_FROM").equals(AllPageTypeParams.HOME_ADVER_PAGE)) {
            LatestAdverDataExtraBean adver_data = (LatestAdverDataExtraBean) data.getSerializable("ADVER_DATA");
            if (adver_data.getApp_banner_type() == 1) {
                mMedia.setVisibility(View.GONE);
                //需要打开JavaScript功能
                mWebView.getSettings().setJavaScriptEnabled(true);
                //为WebView设置自己的浏览客户端
                mWebView.setWebViewClient(new WebViewClient());
                //加载进度以及JS调用监控的客户端
                mWebView.setWebChromeClient(chromeClient);
                mWebView.loadUrl(adver_data.getApp_banner_param());
                mFooter.setVisibility(View.GONE);
            } else if (adver_data.getApp_banner_type() == 2) {
                mPlay.setOnCheckedChangeListener(this);
                mSeek.setOnSeekBarChangeListener(this);
                mScreen.setOnCheckedChangeListener(this);

                //需要打开JavaScript功能
                mWebView.getSettings().setJavaScriptEnabled(true);
                //为WebView设置自己的浏览客户端
                mWebView.setWebViewClient(new WebViewClient());
                //加载进度以及JS调用监控的客户端
                mWebView.setWebChromeClient(chromeClient);
                mWebView.loadUrl(String.format(HttpRequestParams.WEB_URL, adver_data.getApp_banner_param()));

                httpRequest(String.format(HttpRequestParams.VIDEO_URL, adver_data.getApp_banner_param()));

            }
        } else if (data.getString("DATA_FROM").equals(AllPageTypeParams.HOME_NORMAL_PAGE)) {
            LatestNormalDataBean normal_data = (LatestNormalDataBean) data.getSerializable("NORMAL_DATA");
            if (normal_data.getIs_album() == 1) {
                mMedia.setVisibility(View.GONE);
                //需要打开JavaScript功能
                mWebView.getSettings().setJavaScriptEnabled(true);
                //为WebView设置自己的浏览客户端
                mWebView.setWebViewClient(new WebViewClient());
                //加载进度以及JS调用监控的客户端
                mWebView.setWebChromeClient(chromeClient);
                mWebView.loadUrl(normal_data.getRequest_url());
                mLikeNum.setText(String.valueOf(normal_data.getLike_num()));
                mShareNum.setText(String.valueOf(normal_data.getShare_num()));
                mCachePart.setVisibility(View.GONE);
            } else if (normal_data.getIs_album() == 0) {
                mPlay.setOnCheckedChangeListener(this);
                mSeek.setOnSeekBarChangeListener(this);
                mScreen.setOnCheckedChangeListener(this);

                //需要打开JavaScript功能
                mWebView.getSettings().setJavaScriptEnabled(true);
                //为WebView设置自己的浏览客户端
                mWebView.setWebViewClient(new WebViewClient());
                //加载进度以及JS调用监控的客户端
                mWebView.setWebChromeClient(chromeClient);
                mWebView.loadUrl(normal_data.getRequest_url());

                httpRequest(String.format(HttpRequestParams.VIDEO_URL, normal_data.getPostid()));
            }
        } else if (data.getString("DATA_FROM").equals(AllPageTypeParams.BEHIND_RECYCLER_PAGE)) {
            BehindItemDataBean behind_data = (BehindItemDataBean) data.getSerializable("BEHIND_DATA");
            mMedia.setVisibility(View.GONE);
            //需要打开JavaScript功能
            mWebView.getSettings().setJavaScriptEnabled(true);
            //为WebView设置自己的浏览客户端
            mWebView.setWebViewClient(new WebViewClient());
            //加载进度以及JS调用监控的客户端
            mWebView.setWebChromeClient(new WebChromeClient());
            mWebView.loadUrl(behind_data.getRequest_url());
            mLikeNum.setText(String.valueOf(behind_data.getLike_num()));
            mShareNum.setText(String.valueOf(behind_data.getShare_num()));
            mTitle.setText("幕后文章");
            mCachePart.setVisibility(View.GONE);
        }

        mMedia.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_item_content_back:
                finish();
                break;
            case R.id.activity_item_content_media:
                mControls.setVisibility(View.VISIBLE);
                mPlay.setClickable(true);
                mSeek.setClickable(true);
                mScreen.setClickable(true);
                handler.sendEmptyMessageDelayed(AllPageTypeParams.VIDEO_CONTROLES_GONE, 5000);
        }
    }

    private void httpRequest(String url) {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                setVideoInfo(result);
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

    private void setVideoInfo(String result) {
        Gson gson = new Gson();
        VideoBean videoBean = gson.fromJson(result, VideoBean.class);
        mLikeNum.setText(videoBean.getData().getCount_like());
        mShareNum.setText(videoBean.getData().getCount_share());
        mMessage.setText(videoBean.getData().getCount_comment());
        String qiniu_url = videoBean.getData().getContent().getVideo().get(0).getQiniu_url();
        mVideo.setVideoPath(qiniu_url);
        //加载资源，加载完成了，准备好了
        mVideo.setOnPreparedListener(this);
        //视频播放完成，默认调用此下监听器，结束更新播放时间的handler
        mVideo.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //加载完成，准备完成，调用播放
        mVideo.start();
        //发送消息，通知当前时间进行更改 播放进度更新
        handler.sendEmptyMessageDelayed(AllPageTypeParams.UPDATE_TIME, 1000);
        //获取视频总时长
        int duration = mVideo.getDuration();
        mSeek.setMax(duration);
        //设置总时间
        mTotal.setText(DateFormat.format("mm:ss", duration));

        handler.sendEmptyMessage(AllPageTypeParams.VIDEO_CONTROLES_GONE);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        handler.removeMessages(AllPageTypeParams.UPDATE_TIME);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.activity_item_content_video_play:
                if (isChecked) {
                    mVideo.pause();
                    handler.removeMessages(AllPageTypeParams.UPDATE_TIME);
                } else {
                    mVideo.start();
                    handler.sendEmptyMessageDelayed(AllPageTypeParams.UPDATE_TIME, 1000);
                }
                break;
            case R.id.activity_item_content_video_screen:
                ViewGroup.LayoutParams layoutParams = mMedia.getLayoutParams();
                if (isChecked) {
                    mMedioHeight = layoutParams.height;
                    mHead.setVisibility(View.GONE);
                    //切换到横屏模式   添加一个全屏的标记
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //请求横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //设置高度为match_parent
                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                    mMedia.setLayoutParams(layoutParams);
                } else {
                    mHead.setVisibility(View.VISIBLE);
                    //切换到默认模式   清除全屏标记
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    //请求纵屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    //还原高度
                    layoutParams.height = mMedioHeight;
                    mMedia.setLayoutParams(layoutParams);
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.activity_item_content_video_seek:
                if (fromUser) {
                    //将视频移动到指定位置进行播放
                    mVideo.seekTo(progress);
                }
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
