package com.leezp.android.vmovie.activity;

import android.view.View;
import android.widget.ImageView;

import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;

/**
 * Created by Administrator on 2017/6/24.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.activity_login_back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_back:
                finish();
                break;
        }
    }
}
