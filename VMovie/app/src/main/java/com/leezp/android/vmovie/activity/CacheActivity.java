package com.leezp.android.vmovie.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leezp.android.vmovie.BaseActivity;
import com.leezp.android.vmovie.R;

/**
 * Created by Administrator on 2017/6/25.
 */

public class CacheActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private TextView mEditor;
    private LinearLayout mFooter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cache;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.activity_cache_back);
        mEditor = (TextView) findViewById(R.id.activity_cache_editor);
        mFooter = (LinearLayout) findViewById(R.id.activity_cache_footer);

        mBack.setOnClickListener(this);
        mEditor.setOnClickListener(this);
        mFooter.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_cache_back:
                finish();
                break;
            case R.id.activity_cache_editor:
                if (mFooter.getVisibility() == View.VISIBLE) {
                    mFooter.setVisibility(View.GONE);
                } else {
                    mFooter.setVisibility(View.VISIBLE);
                }
        }
    }
}
