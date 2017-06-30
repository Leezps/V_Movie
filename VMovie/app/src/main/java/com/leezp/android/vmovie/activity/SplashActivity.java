package com.leezp.android.vmovie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.constants.SharedParams;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    private ImageView mBg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mBg = (ImageView) findViewById(R.id.activity_splash_bg);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_splash_scale);
        //动画移动到最后渲染并停止
        animation.setFillAfter(true);
        //设置动画监听
        animation.setAnimationListener(this);

        mBg.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //动画结束 决定页面跳转到主页面还是导航页，需使用数据持久化技术
        SharedPreferences preferences = getSharedPreferences(SharedParams.NAME, Context.MODE_PRIVATE);
        boolean firstUse = preferences.getBoolean(SharedParams.FIRST_USE, true);
        if (firstUse) {
            //跳转到导航页
            //记录使用标识
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(SharedParams.FIRST_USE, false);
            editor.apply();
            startActivity(new Intent(this, GuideActivity.class));
        } else {
            //跳转到主页
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
