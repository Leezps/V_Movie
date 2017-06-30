package com.leezp.android.vmovie.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/18.
 */

public class LatestAdverFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView mImage = new ImageView(getContext());
        Bundle bundle = getArguments();
        String imageUrl = bundle.getString("IMAGE_URL");
        x.image().bind(mImage, imageUrl);
        return mImage;
    }

}
