package com.leezp.android.vmovie.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.ChannelRecyclerAdapter;
import com.leezp.android.vmovie.bean.ChannelBean;
import com.leezp.android.vmovie.bean.ChannelDataBean;
import com.leezp.android.vmovie.constants.HttpRequestParams;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ChannelFragment extends BaseFragment{
    private RecyclerView mContent;
    private ChannelRecyclerAdapter adapter;

    private String TAG = "Leezp";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_channel;
    }

    @Override
    protected void initView() {
        mContent = (RecyclerView) findViewById(R.id.fragment_channel_content);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mContent.setLayoutManager(layoutManager);
        adapter = new ChannelRecyclerAdapter(getData());
        mContent.setAdapter(adapter);
    }

    private List<ChannelDataBean> getData() {
        List<ChannelDataBean> data = null;
        RequestParams requestParams = new RequestParams(HttpRequestParams.HOST_URL+HttpRequestParams.CHANNEL_DATA_PATH);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Log.e(TAG, "onSuccess: "+result);
                ChannelBean channelBean = gson.fromJson(result, ChannelBean.class);
                adapter.updateChannelData(channelBean.getData());
                Log.e(TAG, "onSuccess: updateChannelData");
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
}
