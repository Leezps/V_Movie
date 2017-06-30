package com.leezp.android.vmovie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.bean.SettingDataBean;
import com.leezp.android.vmovie.constants.SettingPageParams;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */

public class SettingRecyclerAdapter extends RecyclerView.Adapter<SettingRecyclerAdapter.SettingViewHolder> {

    private List<SettingDataBean> data;
    private int nextHeight;
    private int preHeight;
    private int leftMargin;

    public SettingRecyclerAdapter(List<SettingDataBean> data) {
        this.data = data;
    }

    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_setting_item, parent, false);
        SettingViewHolder viewHolder = new SettingViewHolder(view);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.mBottom.getLayoutParams();
        preHeight = viewHolder.mBottom.getLayoutParams().height;
        leftMargin = layoutParams.leftMargin;
        nextHeight = viewHolder.mTop.getLayoutParams().height;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SettingViewHolder holder, int position) {
        holder.mTop.setVisibility(View.GONE);
        holder.mTitle.setVisibility(View.VISIBLE);
        holder.mInfo.setVisibility(View.GONE);
        holder.mRight.setVisibility(View.VISIBLE);
        holder.mSwitch.setVisibility(View.GONE);
        holder.mBottom.setVisibility(View.VISIBLE);

        SettingDataBean bean = data.get(position);

        if (bean.isSwitch()) {
            holder.mInfo.setVisibility(View.GONE);
            holder.mRight.setVisibility(View.GONE);
            holder.mSwitch.setVisibility(View.VISIBLE);
        } else if (bean.getInfo() != null) {
            holder.mInfo.setVisibility(View.VISIBLE);
            holder.mRight.setVisibility(View.VISIBLE);
            holder.mSwitch.setVisibility(View.GONE);
            holder.mInfo.setText(bean.getInfo());
        } else {
            holder.mInfo.setVisibility(View.GONE);
            holder.mRight.setVisibility(View.VISIBLE);
            holder.mSwitch.setVisibility(View.GONE);
        }
        if (position % SettingPageParams.PART_NUM == 0) {
            holder.mTop.setVisibility(View.VISIBLE);
        } else if (position % SettingPageParams.PART_NUM == (SettingPageParams.PART_NUM - 1) || position == (data.size() - 1)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mBottom.getLayoutParams();
            layoutParams.height = nextHeight;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.leftMargin = 0;
            holder.mBottom.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mBottom.getLayoutParams();
            layoutParams.height = preHeight;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.leftMargin = leftMargin;
            holder.mBottom.setLayoutParams(layoutParams);
        }
        holder.mTitle.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class SettingViewHolder extends RecyclerView.ViewHolder {

        View mTop;
        TextView mTitle;
        TextView mInfo;
        ImageView mRight;
        RadioButton mSwitch;
        View mBottom;

        public SettingViewHolder(View itemView) {
            super(itemView);
            mTop = itemView.findViewById(R.id.activity_setting_item_top);
            mTitle = (TextView) itemView.findViewById(R.id.activity_setting_item_title);
            mInfo = (TextView) itemView.findViewById(R.id.activity_setting_item_info);
            mRight = (ImageView) itemView.findViewById(R.id.activity_setting_item_right);
            mSwitch = (RadioButton) itemView.findViewById(R.id.activity_setting_item_switch);
            mBottom = itemView.findViewById(R.id.activity_setting_item_bottom);
        }

    }

}
