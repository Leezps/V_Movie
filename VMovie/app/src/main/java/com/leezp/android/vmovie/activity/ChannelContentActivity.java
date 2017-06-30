package com.leezp.android.vmovie.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.ChannelContentRecyclerAdapter;
import com.leezp.android.vmovie.bean.LatestNormalBean;
import com.leezp.android.vmovie.bean.LatestNormalDataBean;
import com.leezp.android.vmovie.constants.HttpRequestParams;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class ChannelContentActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ImageView mBack;
    private TextView mTitle;
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecycler;
    private LinearLayoutManager linearLayout;
    private ChannelContentRecyclerAdapter adapter;
    private int cateId;
    private String cateName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_channel_content;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.activity_channel_content_back);
        mTitle = (TextView) findViewById(R.id.activity_channel_content_title);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.activity_channel_content_refresh);
        mRecycler = (RecyclerView) findViewById(R.id.activity_channel_content_recycler);

        mRefresh.setOnRefreshListener(this);
        mBack.setOnClickListener(this);

        linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(linearLayout);

        Intent intent = getIntent();
        cateId = intent.getIntExtra("CATE_ID", 6);
        cateName = intent.getStringExtra("CATE_NAME");

        mTitle.setText(cateName);

        adapter = new ChannelContentRecyclerAdapter(getData());
        mRecycler.setAdapter(adapter);
    }

    private List<LatestNormalDataBean> getData() {
        List<LatestNormalDataBean> data = null;
        RequestParams requestParams = new RequestParams(String.format(HttpRequestParams.CHANNEL_CONTENT, cateId));
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                LatestNormalBean normalBean = gson.fromJson(result, LatestNormalBean.class);
                adapter.updateChannelContentData(normalBean.getData());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                mRefresh.setRefreshing(false);
            }
        });
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_channel_content_back:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh() {
        adapter.clearChannelContentData();
        getData();
    }
}
