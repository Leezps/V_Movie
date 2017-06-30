package com.leezp.android.vmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/6/15.
 */

public abstract class BaseFragment extends Fragment {

    protected View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(getLayoutId(), container, false);
        initView();
        return layout;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    public View findViewById(int resId) {
        return layout.findViewById(resId);
    }

}
