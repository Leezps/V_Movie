package com.leezp.android.vmovie.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.fragments.BehindPageFragment;
import com.leezp.android.vmovie.fragments.HomePageFragment;
import com.leezp.android.vmovie.fragments.SeriesPageFragment;
import com.leezp.android.vmovie.utils.CircleImg;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private HomePageFragment homeFragment;
    private SeriesPageFragment seriesFragment;
    private BehindPageFragment behindFragment;
    private RadioGroup mController;
    private FrameLayout mMenu;
    private ImageView mCloseMenu;
    private RadioButton mHome;
    private RadioButton mSeries;
    private RadioButton mBehind;
    private CircleImg mHeadImg;
    private TextView mLogin;
    private ImageView mMessage;
    private TextView mLike;
    private ImageView mSetting;
    private TextView mCache;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMenu = (FrameLayout) findViewById(R.id.activity_main_menu);
        mCloseMenu = (ImageView) findViewById(R.id.activity_main_menu_close);
        mCloseMenu.setOnClickListener(this);
        mHome = (RadioButton) findViewById(R.id.activity_main_menu_home);
        mHome.setOnClickListener(this);
        mSeries = (RadioButton) findViewById(R.id.activity_main_menu_series);
        mSeries.setOnClickListener(this);
        mBehind = (RadioButton) findViewById(R.id.activity_main_menu_behind);
        mBehind.setOnClickListener(this);
        mHeadImg = (CircleImg) findViewById(R.id.activity_main_menu_head);
        mHeadImg.setOnClickListener(this);
        mLogin = (TextView) findViewById(R.id.activity_main_menu_login);
        mLogin.setOnClickListener(this);
        mMessage = (ImageView) findViewById(R.id.activity_main_menu_message);
        mMessage.setOnClickListener(this);
        mLike = (TextView) findViewById(R.id.activity_main_menu_like);
        mLike.setOnClickListener(this);
        mSetting = (ImageView) findViewById(R.id.activity_main_menu_set);
        mSetting.setOnClickListener(this);
        mCache = (TextView) findViewById(R.id.activity_main_menu_cache);
        mCache.setOnClickListener(this);
        /**
         * 动态加载Fragment流程
         *      1.获取FragmentManager
         *      2.开启一个FragmentTransaction
         *      3.添加动作
         *      4.提交事务
         */
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //动作
        homeFragment = new HomePageFragment();
        seriesFragment = new SeriesPageFragment();
        behindFragment = new BehindPageFragment();
        //将页面添加进来
        transaction.add(R.id.activity_main_container, homeFragment)
                .add(R.id.activity_main_container, seriesFragment)
                .add(R.id.activity_main_container, behindFragment);
        //将页面隐藏
        transaction.hide(homeFragment)
                .hide(seriesFragment)
                .hide(behindFragment);

        transaction.commit();

        //初始化控件
        mController = (RadioGroup) findViewById(R.id.activity_main_menu_controller);
        mController.setOnCheckedChangeListener(this);
        mHome.setChecked(true);
    }

    public void openMenu() {
        mMenu.setVisibility(View.VISIBLE);
        //添加动画  关闭按钮的放大动画，控制按钮的顺序显示动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mCloseMenu, "scaleX", 0, 0.8f, 1, 1.2f, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mCloseMenu, "scaleY", 0, 0.8f, 1, 1.2f, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX)
                .with(scaleY);
        animatorSet.setDuration(3000);
        animatorSet.start();
        Animation homeIn = AnimationUtils.loadAnimation(this, R.anim.homepage_in);
        Animation seriesIn = AnimationUtils.loadAnimation(this, R.anim.seriespage_in);
        Animation behindIn = AnimationUtils.loadAnimation(this, R.anim.behindpage_in);
        mHome.startAnimation(homeIn);
        mSeries.startAnimation(seriesIn);
        mBehind.startAnimation(behindIn);
    }

    public void closeMenu() {
        mMenu.setVisibility(View.GONE);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (checkedId) {
            case R.id.activity_main_menu_home:
                transaction.hide(seriesFragment)
                        .hide(behindFragment)
                        .show(homeFragment);
                break;
            case R.id.activity_main_menu_series:
                transaction.hide(homeFragment)
                        .hide(behindFragment)
                        .show(seriesFragment);
                break;
            case R.id.activity_main_menu_behind:
                transaction.hide(homeFragment)
                        .hide(seriesFragment)
                        .show(behindFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_menu_close:
                closeMenuAnim();
                break;
            case R.id.activity_main_menu_home:
            case R.id.activity_main_menu_series:
            case R.id.activity_main_menu_behind:
                closeMenu();
                break;
            case R.id.activity_main_menu_head:
            case R.id.activity_main_menu_message:
            case R.id.activity_main_menu_login:
            case R.id.activity_main_menu_like:
                gotoLoginActivity();
                break;
            case R.id.activity_main_menu_set:
                gotoSettingActivity();
                break;
            case R.id.activity_main_menu_cache:
                gotoCacheActivity();
                break;
        }
    }

    private void gotoCacheActivity() {
        Intent intent = new Intent(this, CacheActivity.class);
        startActivity(intent);
    }

    private void gotoSettingActivity() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void closeMenuAnim() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mCloseMenu, "scaleX", 1, 1.2f, 1, 0.8f, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mCloseMenu, "scaleY", 1, 1.2f, 1, 0.8f, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX)
                .with(scaleY);
        animatorSet.setDuration(3000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                closeMenu();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }
}
