package com.leezp.android.vmovie.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.BehindItemRecyclerAdapter;
import com.leezp.android.vmovie.bean.BehindItemBean;
import com.leezp.android.vmovie.bean.BehindItemDataBean;
import com.leezp.android.vmovie.constants.HttpRequestParams;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class BehindPageItemFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayout;
    private String  cateId;
    private BehindItemRecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_behind_page_item;
    }

    @Override
    protected void initView() {
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.fragment_behind_page_item_refresh);
        mRecycler = (RecyclerView) findViewById(R.id.fragment_behind_page_item_recycler);

        mLayout = new LinearLayoutManager(getContext());
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(mLayout);
        Bundle bundle = getArguments();
        cateId = bundle.getString("CATE_ID");
        adapter = new BehindItemRecyclerAdapter(getData());
        mRecycler.setAdapter(adapter);

        mRefresh.setOnRefreshListener(this);
    }

    private List<BehindItemDataBean> getData() {
        List<BehindItemDataBean> data = null;
        RequestParams requestParams = new RequestParams(String.format(HttpRequestParams.HOST_URL+HttpRequestParams.BEHIND_ITEM__PATH+"?cateid=%s", cateId));
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BehindItemBean itemBean = gson.fromJson(result, BehindItemBean.class);
                adapter.updateBehindItemData(itemBean.getData());
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
    public void onRefresh() {
        adapter.clearBehindItemData();
        getData();
    }
}
