package com.leezp.android.vmovie.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;

    public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    public void updateData(List<Fragment> data) {
        if (this.data != null) {
            this.data = null;
        }
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }
}
