package com.leezp.android.vmovie.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.adapters.GuidePageAdapter;
import com.leezp.android.vmovie.fragments.GuideOneFragment;
import com.leezp.android.vmovie.fragments.GuideThreeFragment;
import com.leezp.android.vmovie.fragments.GuideTwoFragment;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mContainer;
    private RadioGroup mIndicator;
    private GuidePageAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        mContainer = (ViewPager) findViewById(R.id.activity_guide_container);
        mIndicator = (RadioGroup) findViewById(R.id.activity_guide_indicator);
        adapter = new GuidePageAdapter(getSupportFragmentManager(), getData());
        mContainer.setAdapter(adapter);
        mContainer.addOnPageChangeListener(this);
        RadioButton childOne = (RadioButton) mIndicator.getChildAt(0);
        childOne.setChecked(true);
    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        data.add(new GuideOneFragment());
        data.add(new GuideTwoFragment());
        data.add(new GuideThreeFragment());
        return data;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton child = (RadioButton) mIndicator.getChildAt(position);
        child.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
