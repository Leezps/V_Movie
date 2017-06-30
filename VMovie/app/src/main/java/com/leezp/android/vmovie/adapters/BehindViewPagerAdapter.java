package com.leezp.android.vmovie.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.leezp.android.vmovie.bean.BehindTitleDataBean;
import com.leezp.android.vmovie.fragments.BehindPageItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class BehindViewPagerAdapter extends FragmentPagerAdapter {

    private List<BehindTitleDataBean> titleData;

    public BehindViewPagerAdapter(FragmentManager fm, List<BehindTitleDataBean> titleData) {
        super(fm);
        this.titleData = titleData;
    }

    public void updateBehindTitleData(List<BehindTitleDataBean> titleData) {
        if (this.titleData != null) {
            this.titleData.clear();
        } else if(this.titleData == null) {
            this.titleData = new ArrayList<>();
        }
        this.titleData.addAll(titleData);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        BehindTitleDataBean dataBean = titleData.get(position);
        BehindPageItemFragment fragment = new BehindPageItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CATE_ID", String.valueOf(dataBean.getCateid()));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titleData != null ? titleData.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleData.get(position).getCatename();
    }
}
