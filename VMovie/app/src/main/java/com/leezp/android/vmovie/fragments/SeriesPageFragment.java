package com.leezp.android.vmovie.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.MainActivity;
import com.leezp.android.vmovie.adapters.SeriesRecyclerAdapter;
import com.leezp.android.vmovie.bean.SeriesBean;
import com.leezp.android.vmovie.bean.SeriesDataBean;
import com.leezp.android.vmovie.constants.HttpRequestParams;
import com.leezp.android.vmovie.constants.SeriesPageParams;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class SeriesPageFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ImageView mOpenMenu;
    private TextView mTitle;
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayout;
    private SeriesRecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_series_page;
    }

    @Override
    protected void initView() {
        mOpenMenu = (ImageView) findViewById(R.id.fragment_all_title_menu);
        mOpenMenu.setOnClickListener(this);

        mTitle = (TextView) findViewById(R.id.fragment_all_title_title);
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(SeriesPageParams.SERIES_NAME);

        mRefresh = (SwipeRefreshLayout) findViewById(R.id.fragment_series_page_refresh);
        mRecycler = (RecyclerView) findViewById(R.id.fragment_series_page_container);

        mLayout = new LinearLayoutManager(getContext());
        mLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(mLayout);

        adapter = new SeriesRecyclerAdapter(getData());
        mRecycler.setAdapter(adapter);
        mRefresh.setOnRefreshListener(this);
    }

    private List<SeriesDataBean> getData() {
        List<SeriesDataBean> data = null;
        RequestParams requestParams = new RequestParams(String.format(HttpRequestParams.HOST_URL+HttpRequestParams.SERIES_DATA_PATH+"?p=%d", SeriesPageParams.SERIES_NUM));
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SeriesBean seriesBean = gson.fromJson(result, SeriesBean.class);
                adapter.updateSeriesData(seriesBean.getData());
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
        MainActivity activity = (MainActivity) getActivity();
        activity.openMenu();
    }

    @Override
    public void onRefresh() {
        adapter.clearSeriesData();
        getData();
    }
}
