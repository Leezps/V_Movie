package com.leezp.android.vmovie.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.leezp.android.vmovie.BaseFragment;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.MainActivity;

/**
 * Created by Administrator on 2017/6/16.
 */

public class GuideThreeFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mGoMain;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guide_three;
    }

    @Override
    protected void initView() {
        mGoMain = (ImageView) findViewById(R.id.fragment_guide_three_go_main);
        mGoMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_guide_three_go_main:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
