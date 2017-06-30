package com.leezp.android.vmovie.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.SettingRecyclerAdapter;
import com.leezp.android.vmovie.bean.SettingDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayout;
    private ImageView mBack;
    private SettingRecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.activity_setting_recycler);
        mBack = (ImageView) findViewById(R.id.activity_setting_back);

        mLayout = new LinearLayoutManager(this);
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mRecycler.setLayoutManager(mLayout);

        adapter = new SettingRecyclerAdapter(getData());
        mRecycler.setAdapter(adapter);

        mBack.setOnClickListener(this);
    }

    private List<SettingDataBean> getData() {
        List<SettingDataBean> data = new ArrayList<>();
        SettingDataBean bean = new SettingDataBean(false, "用户反馈", null);
        data.add(bean);
        bean = new SettingDataBean(false, "清除图片缓存", "0KB");
        data.add(bean);
        bean = new SettingDataBean(false, "播放设置", null);
        data.add(bean);
        bean = new SettingDataBean(true, "允许非wifi网络下载", null);
        data.add(bean);
        bean = new SettingDataBean(false, "离线缓存路径设置", "手机存储");
        data.add(bean);
        bean = new SettingDataBean(false, "给V电影评分", null);
        data.add(bean);
        bean = new SettingDataBean(false, "版本更新", "V5.1.9(Build 170)");
        data.add(bean);
        bean = new SettingDataBean(false, "分享给好友", null);
        data.add(bean);
        bean = new SettingDataBean(false, "给我投稿", null);
        data.add(bean);
        bean = new SettingDataBean(false, "关注微信公众号", null);
        data.add(bean);
        bean = new SettingDataBean(false, "加入用户QQ群", null);
        data.add(bean);
        bean = new SettingDataBean(false, "关于我们", null);
        data.add(bean);
        bean = new SettingDataBean(false, "免责声明", null);
        data.add(bean);
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_setting_back:
                finish();
                break;
        }
    }
}
