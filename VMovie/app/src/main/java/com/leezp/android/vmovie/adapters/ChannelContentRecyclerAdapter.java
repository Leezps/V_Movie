package com.leezp.android.vmovie.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.ItemActivity;
import com.leezp.android.vmovie.bean.LatestNormalDataBean;
import com.leezp.android.vmovie.constants.AllPageTypeParams;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class ChannelContentRecyclerAdapter extends RecyclerView.Adapter<ChannelContentRecyclerAdapter.ChannelContentViewHolder> {

    private List<LatestNormalDataBean> data;

    public ChannelContentRecyclerAdapter(List<LatestNormalDataBean> data) {
        this.data = data;
    }

    public void updateChannelContentData(List<LatestNormalDataBean> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clearChannelContentData() {
        if (this.data != null) {
            this.data.clear();
        }
    }

    @Override
    public ChannelContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_channel_content_item, parent, false);
        final ChannelContentViewHolder viewHolder = new ChannelContentViewHolder(view);
        viewHolder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                LatestNormalDataBean bean = data.get(position);
                Intent intent = new Intent(v.getContext(), ItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("DATA_FROM", AllPageTypeParams.HOME_NORMAL_PAGE);
                bundle.putSerializable("NORMAL_DATA", bean);
                intent.putExtra("DATA", bundle);
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChannelContentViewHolder holder, int position) {
        LatestNormalDataBean dataBean = data.get(position);
        x.image().bind(holder.mImage, dataBean.getImage());
        holder.mTitle.setText(dataBean.getTitle());
        holder.mInfo.setText(dataBean.getCates().get(0).getCatename() + " / " + dataBean.getDuration() / 60 + "'" + dataBean.getDuration() % 60 + "''");
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ChannelContentViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTitle;
        TextView mInfo;

        public ChannelContentViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.activity_channel_content_item_image);
            mTitle = (TextView) itemView.findViewById(R.id.activity_channel_content_item_title);
            mInfo = (TextView) itemView.findViewById(R.id.activity_channel_content_item_info);
        }
    }

}
