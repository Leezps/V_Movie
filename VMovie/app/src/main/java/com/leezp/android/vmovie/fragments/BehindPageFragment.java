package com.leezp.android.vmovie.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.MainActivity;
import com.leezp.android.vmovie.adapters.BehindViewPagerAdapter;
import com.leezp.android.vmovie.bean.BehindTitleBean;
import com.leezp.android.vmovie.bean.BehindTitleDataBean;
import com.leezp.android.vmovie.constants.BehindPageParams;
import com.leezp.android.vmovie.constants.HttpRequestParams;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class BehindPageFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mOpenMenu;
    private TextView mTitle;
    private TabLayout mIndicator;
    private ViewPager mViewPager;
    private BehindViewPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_behind_page;
    }

    @Override
    protected void initView() {
        mOpenMenu = (ImageView) findViewById(R.id.fragment_all_title_menu);
        mOpenMenu.setOnClickListener(this);

        mTitle = (TextView) findViewById(R.id.fragment_all_title_title);
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(BehindPageParams.BEHIND_NAME);

        mIndicator = (TabLayout) findViewById(R.id.fragment_behind_page_indicator);
        mViewPager = (ViewPager) findViewById(R.id.fragment_behind_page_container);

        adapter = new BehindViewPagerAdapter(getActivity().getSupportFragmentManager(), getData());
        mViewPager.setAdapter(adapter);
        mIndicator.setupWithViewPager(mViewPager);

        LinearLayout linearLayout = (LinearLayout) mIndicator.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.behind_tab_cut_off_rule));
        linearLayout.setDividerPadding(BehindPageParams.INDICATOR_PADDING_HEIGHT);

    }

    private List<BehindTitleDataBean> getData() {
        List<BehindTitleDataBean> data = null;
        RequestParams requestParams = new RequestParams(HttpRequestParams.HOST_URL + HttpRequestParams.BEHIND_DATA_PATH);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BehindTitleBean bean = gson.fromJson(result, BehindTitleBean.class);
                adapter.updateBehindTitleData(bean.getData());
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

    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) getActivity();
        activity.openMenu();
    }
}
