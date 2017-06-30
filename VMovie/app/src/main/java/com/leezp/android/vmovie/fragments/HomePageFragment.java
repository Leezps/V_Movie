package com.leezp.android.vmovie.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.MainActivity;
import com.leezp.android.vmovie.adapters.FragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class HomePageFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ImageView mOpenMenu;
    private FrameLayout mHome;
    private ViewPager mContent;
    private TextView mLatest;
    private TextView mChannel;
    private View mSlide;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    protected void initView() {
        mOpenMenu = (ImageView) findViewById(R.id.fragment_all_title_menu);
        mOpenMenu.setOnClickListener(this);

        mHome = (FrameLayout) findViewById(R.id.fragment_all_title_home);
        mHome.setVisibility(View.VISIBLE);

        mLatest = (TextView) findViewById(R.id.fragment_all_title_latest);
        mLatest.setOnClickListener(this);
        mChannel = (TextView) findViewById(R.id.fragment_all_title_channel);
        mChannel.setOnClickListener(this);
        mSlide = findViewById(R.id.fragment_all_title_slide);

        mContent = (ViewPager) findViewById(R.id.fragment_home_page_container);
        mContent.setAdapter(new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager(), getData()));
        mContent.addOnPageChangeListener(this);
    }

    private List<Fragment> getData() {
        List<Fragment> data = new ArrayList<>();
        data.add(new LatestFragment());
        data.add(new ChannelFragment());
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_all_title_menu:
                MainActivity activity = (MainActivity) getActivity();
                activity.openMenu();
                break;
            case R.id.fragment_all_title_latest:
                changeTitleTextColor(0);
                mContent.setCurrentItem(0);
                break;
            case R.id.fragment_all_title_channel:
                changeTitleTextColor(1);
                mContent.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int contrastX = mLatest.getWidth();
        mSlide.setTranslationX(contrastX*(position+positionOffset));
    }

    @Override
    public void onPageSelected(int position) {
        changeTitleTextColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //改变标题Text的颜色
    private void changeTitleTextColor(int position) {
        switch (position) {
            case 0:
                mLatest.setTextColor(getResources().getColor(R.color.colorWhite));
                mChannel.setTextColor(getResources().getColor(R.color.colorGray_6));
                break;
            case 1:
                mLatest.setTextColor(getResources().getColor(R.color.colorGray_6));
                mChannel.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
        }
    }
}
