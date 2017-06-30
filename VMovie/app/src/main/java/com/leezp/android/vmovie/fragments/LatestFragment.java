package com.leezp.android.vmovie.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.LatestRecyclerAdapter;
import com.leezp.android.vmovie.bean.LatestAdverBean;
import com.leezp.android.vmovie.bean.LatestAdverDataBean;
import com.leezp.android.vmovie.bean.LatestNormalBean;
import com.leezp.android.vmovie.bean.LatestNormalDataBean;
import com.leezp.android.vmovie.constants.HttpRequestParams;
import com.leezp.android.vmovie.constants.LatestAdapterParams;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class LatestFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mContent;
    private LatestRecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    protected void initView() {
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.fragment_latest_refresh);
        mContent = (RecyclerView) findViewById(R.id.fragment_latest_content);

        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        mContent.setLayoutManager(layout);
        adapter = new LatestRecyclerAdapter(getNormalData(), getAdverData(), getContext(), getActivity().getSupportFragmentManager());
        mContent.setAdapter(adapter);

        mRefresh.setColorSchemeResources(R.color.colorLightBlue);
        mRefresh.setOnRefreshListener(this);
    }

    private List<LatestAdverDataBean> getAdverData() {
        List<LatestAdverDataBean> data = null;
        RequestParams requestParams = new RequestParams(HttpRequestParams.HOST_URL + HttpRequestParams.LATEST_ADVER_PATH);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                LatestAdverBean latestAdverBean = gson.fromJson(result, LatestAdverBean.class);
                List<LatestAdverDataBean> adverDataBeen = latestAdverBean.getData();
                adapter.updateLatestAdverData(adverDataBeen);
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
        return data;
    }

    private List<LatestNormalDataBean> getNormalData() {
        List<LatestNormalDataBean> data = null;
        LatestAdapterParams.NORMAL_PAGE_NUM = 1;
        RequestParams requestParams = new RequestParams(HttpRequestParams.HOST_URL + HttpRequestParams.LATEST_NORMAL_PATH + "?p=" + LatestAdapterParams.NORMAL_PAGE_NUM);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                LatestNormalBean latestNormalBean = gson.fromJson(result, LatestNormalBean.class);
                List<LatestNormalDataBean> normalDataBeen = latestNormalBean.getData();
                adapter.updateLatestNormalData(normalDataBeen);
                ++LatestAdapterParams.NORMAL_PAGE_NUM;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //设置刷新数据结束
                mRefresh.setRefreshing(false);
            }
        });
        return data;
    }

    @Override
    public void onRefresh() {
        //更行广告中的数据
        getAdverData();
        //清除Normal中的数据以及重新加载数据
        adapter.clearLatestNormalData();
        getNormalData();
    }
}
