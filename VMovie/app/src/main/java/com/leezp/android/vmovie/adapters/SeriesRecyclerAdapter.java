package com.leezp.android.vmovie.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.SeriesContentActivity;
import com.leezp.android.vmovie.bean.SeriesDataBean;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<SeriesRecyclerAdapter.SeriesViewHolder> {

    private List<SeriesDataBean> data;

    public SeriesRecyclerAdapter(List<SeriesDataBean> data) {
        this.data = data;
    }

    public void updateSeriesData(List<SeriesDataBean> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(data);
    }

    public void clearSeriesData() {
        if (this.data != null) {
            this.data.clear();
        }
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_series_page_item, viewGroup, false);
        final SeriesViewHolder viewHolder = new SeriesViewHolder(inflate);
        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fragment_series_page_item_layout:
                        int position = viewHolder.getAdapterPosition();
                        SeriesDataBean bean = data.get(position);
                        Intent intent = new Intent(v.getContext(), SeriesContentActivity.class);
                        intent.putExtra("SERIES_DATA", bean);
                        v.getContext().startActivity(intent);
                        break;
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder seriesViewHolder, int i) {
        SeriesDataBean dataBean = data.get(i);
        seriesViewHolder.mTitle.setText(dataBean.getTitle());
        seriesViewHolder.mInfo.setText("已更新至"+dataBean.getUpdate_to()+"集 "+dataBean.getFollower_num()+"人已订阅");
        seriesViewHolder.mContent.setText(dataBean.getContent());
        x.image().bind(seriesViewHolder.mImage, dataBean.getImage());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class SeriesViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLayout;
        ImageView mImage;
        TextView mTitle;
        TextView mInfo;
        TextView mContent;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout) itemView.findViewById(R.id.fragment_series_page_item_layout);
            mImage = (ImageView) itemView.findViewById(R.id.fragment_series_page_item_image);
            mTitle = (TextView) itemView.findViewById(R.id.fragment_series_page_item_title);
            mInfo = (TextView) itemView.findViewById(R.id.fragment_series_page_item_info);
            mContent = (TextView) itemView.findViewById(R.id.fragment_series_page_item_content);
        }

    }
}