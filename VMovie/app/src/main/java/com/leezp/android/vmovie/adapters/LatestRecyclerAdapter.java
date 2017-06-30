package com.leezp.android.vmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.ItemActivity;
import com.leezp.android.vmovie.bean.LatestAdverDataBean;
import com.leezp.android.vmovie.bean.LatestAdverDataExtraBean;
import com.leezp.android.vmovie.bean.LatestNormalBean;
import com.leezp.android.vmovie.bean.LatestNormalDataBean;
import com.leezp.android.vmovie.constants.AllPageTypeParams;
import com.leezp.android.vmovie.constants.HttpRequestParams;
import com.leezp.android.vmovie.constants.LatestAdapterParams;
import com.leezp.android.vmovie.fragments.LatestAdverFragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class LatestRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewPager.OnPageChangeListener, View.OnTouchListener {

    private List<LatestNormalDataBean> normalData;
    private List<LatestAdverDataBean> adverData;
    private Context context;
    private FragmentManager fManager;

    private ViewPager mContainer;
    private RadioGroup mIndicator;
    private float previousX;
    private float previousY;

    public LatestRecyclerAdapter(List<LatestNormalDataBean> normalData, List<LatestAdverDataBean> adverData, Context context, FragmentManager fManager) {
        this.normalData = normalData;
        this.adverData = adverData;
        this.context = context;
        this.fManager = fManager;
    }

    public void updateLatestAdverData(List<LatestAdverDataBean> data) {
        if (adverData == null) {
            adverData = data;
        } else {
            adverData.clear();
            adverData.addAll(data);
        }
        FragmentViewPagerAdapter adapter = (FragmentViewPagerAdapter) mContainer.getAdapter();
        adapter.updateData(getAdverData());
        mContainer.setCurrentItem(1);
        mIndicator.removeAllViews();
        for (int i = 0; i < adverData.size(); i++) {
            mIndicator.addView(getRadioButton());
        }
        RadioButton childOne = (RadioButton) mIndicator.getChildAt(0);
        childOne.setChecked(true);
    }

    public void updateLatestNormalData(List<LatestNormalDataBean> data) {
        if (normalData == null) {
            normalData = new ArrayList<>();
        }
        normalData.addAll(data);
        notifyDataSetChanged();
    }

    public void clearLatestNormalData() {
        if (normalData != null) {
            normalData.clear();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return LatestAdapterParams.ADVER_VIEW;
        } else if (position == normalData.size()) {
            return LatestAdapterParams.EMPTY_VIEW;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == LatestAdapterParams.ADVER_VIEW) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_latest_item_adver, parent, false);
            AdverViewHolder adverViewHolder = new AdverViewHolder(view);
            mContainer.setAdapter(new FragmentViewPagerAdapter(fManager, getAdverData()));
            mContainer.addOnPageChangeListener(this);
            mContainer.setOnTouchListener(this);
            return adverViewHolder;
        } else if (viewType == LatestAdapterParams.EMPTY_VIEW) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_latest_item_load, parent, false);
            return new LoadViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_latest_item_normal, parent, false);
            final NormalViewHolder normalViewHolder = new NormalViewHolder(view);
            normalViewHolder.mNormalImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = normalViewHolder.getAdapterPosition();
                    LatestNormalDataBean bean = normalData.get(position - 1);
                    Intent intent = new Intent(v.getContext(), ItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("DATA_FROM", AllPageTypeParams.HOME_NORMAL_PAGE);
                    bundle.putSerializable("NORMAL_DATA", bean);
                    intent.putExtra("DATA", bundle);
                    v.getContext().startActivity(intent);
                }
            });
            return normalViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdverViewHolder) {
            mIndicator.removeAllViews();
            if (adverData != null) {
                for (int i = 0; i < adverData.size(); i++) {
                    mIndicator.addView(getRadioButton());
                }
            } else {
                for (int i = 0; i < LatestAdapterParams.ADVER_DEFAULT_NUM; i++) {
                    mIndicator.addView(getRadioButton());
                }
            }
            mContainer.setCurrentItem(1);
            RadioButton childOne = ((RadioButton) mIndicator.getChildAt(0));
            childOne.setChecked(true);
        } else if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            LatestNormalDataBean normalDataBean = normalData.get(position - 1);

            x.image().bind(normalViewHolder.mNormalImage, normalDataBean.getImage());
            normalViewHolder.mNormalTitle.setText(normalDataBean.getTitle());

            if (normalDataBean.getIs_album() == 0) {
                normalViewHolder.mNormalTopic.setVisibility(View.INVISIBLE);
                normalViewHolder.mNormalDiving.setVisibility(View.INVISIBLE);
                normalViewHolder.mNormalInfo.setText(normalDataBean.getCates().get(0).getCatename() + " / " + normalDataBean.getDuration() / 60 + "'" + normalDataBean.getDuration() % 60 + "''");
            } else {
                normalViewHolder.mNormalTopic.setVisibility(View.VISIBLE);
                normalViewHolder.mNormalDiving.setVisibility(View.VISIBLE);
                normalViewHolder.mNormalInfo.setText(normalDataBean.getApp_fu_title());
            }
        } else if (holder instanceof LoadViewHolder) {
            /**
             * 上拉加载进度条的接口，其中可以写加载更多的不同情况
             */
            RequestParams requestParams = new RequestParams(HttpRequestParams.HOST_URL + HttpRequestParams.LATEST_NORMAL_PATH + "?p=" + LatestAdapterParams.NORMAL_PAGE_NUM);
            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    LatestNormalBean latestNormalBean = gson.fromJson(result, LatestNormalBean.class);
                    List<LatestNormalDataBean> normalDataBeen = latestNormalBean.getData();
                    updateLatestNormalData(normalDataBeen);
                    ++LatestAdapterParams.NORMAL_PAGE_NUM;
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return normalData != null ? (normalData.size() + 1) : 1;
    }

    class AdverViewHolder extends RecyclerView.ViewHolder {

        public AdverViewHolder(View itemView) {
            super(itemView);
            mContainer = ((ViewPager) itemView.findViewById(R.id.fragment_latest_item_adver_container));
            mIndicator = ((RadioGroup) itemView.findViewById(R.id.fragment_latest_item_adver_indicator));
        }

    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        ImageView mNormalImage;
        TextView mNormalTopic;
        View mNormalDiving;
        TextView mNormalTitle;
        TextView mNormalInfo;

        public NormalViewHolder(View itemView) {
            super(itemView);
            mNormalImage = (ImageView) itemView.findViewById(R.id.fragment_latest_item_normal_img);
            mNormalTopic = (TextView) itemView.findViewById(R.id.fragment_latest_item_normal_topic);
            mNormalDiving = itemView.findViewById(R.id.fragment_latest_item_normal_diving);
            mNormalTitle = (TextView) itemView.findViewById(R.id.fragment_latest_item_normal_title);
            mNormalInfo = (TextView) itemView.findViewById(R.id.fragment_latest_item_normal_info);
        }

    }

    class LoadViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;
        TextView message;

        public LoadViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.fragment_latest_item_load_progress);
            message = (TextView) itemView.findViewById(R.id.fragment_latest_item_load_msg);
        }

    }

    private RadioButton getRadioButton() {
        RadioButton radioButton = new RadioButton(context);
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(LatestAdapterParams.ADVER_RADIO_WIDTH, LatestAdapterParams.AVER_RADIO_HEIGHT);
        params.setMargins(LatestAdapterParams.AVER_RADIO_MARGIN, LatestAdapterParams.AVER_RADIO_MARGIN, LatestAdapterParams.AVER_RADIO_MARGIN, LatestAdapterParams.AVER_RADIO_MARGIN);
        radioButton.setLayoutParams(params);
        radioButton.setButtonDrawable(android.R.color.transparent);
        radioButton.setClickable(false);
        radioButton.setBackgroundResource(R.drawable.fragment_latest_item_adver_indicator_selector);

        return radioButton;
    }

    private List<Fragment> getAdverData() {
        List<Fragment> data = null;
        if (adverData != null) {
            data = new ArrayList<>();

            LatestAdverFragment adverFragment = new LatestAdverFragment();
            Bundle bundle = new Bundle();
            bundle.putString("IMAGE_URL", adverData.get(adverData.size() - 1).getImage());
            adverFragment.setArguments(bundle);
            data.add(adverFragment);

            for (int i = 0; i < adverData.size(); i++) {
                adverFragment = new LatestAdverFragment();
                bundle = new Bundle();
                bundle.putString("IMAGE_URL", adverData.get(i).getImage());
                adverFragment.setArguments(bundle);
                data.add(adverFragment);
            }

            adverFragment = new LatestAdverFragment();
            bundle = new Bundle();
            bundle.putString("IMAGE_URL", adverData.get(0).getImage());
            adverFragment.setArguments(bundle);
            data.add(adverFragment);

        }
        return data;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (i < 1) {
            i = adverData.size();
            mContainer.setCurrentItem(i, false);
        } else if (i > adverData.size()) {
            i = 1;
            mContainer.setCurrentItem(i, false);
        }
        RadioButton child = (RadioButton) mIndicator.getChildAt(i - 1);
        child.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.fragment_latest_item_adver_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    previousX = event.getX();
                    previousY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (previousX == event.getX() && previousY == event.getY()) {
                        int currentItem = mContainer.getCurrentItem();
                        LatestAdverDataBean adverDataBean = adverData.get(currentItem - 1);
                        LatestAdverDataExtraBean adverDataExtraBean = adverDataBean.getExtra_data();
                        Intent intent = new Intent(v.getContext(), ItemActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("DATA_FROM", AllPageTypeParams.HOME_ADVER_PAGE);
                        bundle.putSerializable("ADVER_DATA", adverDataExtraBean);
                        intent.putExtra("DATA", bundle);
                        v.getContext().startActivity(intent);
                    }
                    break;
            }
        }
        return false;
    }

}
