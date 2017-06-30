package com.leezp.android.vmovie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.bean.SeriesContentPostsListDataBean;
import com.leezp.android.vmovie.constants.SeriesPageParams;
import com.leezp.android.vmovie.impl.SeriesCallBackImpl;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */

public class SeriesContentViewRecyclerAdapter extends RecyclerView.Adapter<SeriesContentViewRecyclerAdapter.SeriesViewRecyclerViewHolder> {

    private List<SeriesContentPostsListDataBean> data;

    private SeriesCallBackImpl callBack;

    public SeriesContentViewRecyclerAdapter(List<SeriesContentPostsListDataBean> data, SeriesCallBackImpl callBack) {
        this.data = data;
        this.callBack = callBack;
    }

    public void updateSeriesContentData(List<SeriesContentPostsListDataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public SeriesViewRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_series_content_item, parent, false);
        final SeriesViewRecyclerViewHolder viewHolder = new SeriesViewRecyclerViewHolder(view);
        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                SeriesContentPostsListDataBean bean = data.get(position);
                String videoTitle = "第"+bean.getNumber()+"集 "+bean.getTitle();
                callBack.updateVideoWebView(bean.getSource_link(), videoTitle);
                SeriesPageParams.SERIES_CONTENT_POST_ID = bean.getSeries_postid();
                notifyDataSetChanged();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeriesViewRecyclerViewHolder holder, int position) {
        SeriesContentPostsListDataBean bean = data.get(position);
        x.image().bind(holder.mImage, bean.getThumbnail());
        holder.mPlaying.setVisibility(View.INVISIBLE);
        holder.mDuration.setText(bean.getDuration() / 60 + ":" + bean.getDuration() % 60);
        holder.mTitle.setText(bean.getTitle());
        holder.mPublish.setText(bean.getAddtime());
        if (bean.getSeries_postid() == SeriesPageParams.SERIES_CONTENT_POST_ID) {
            holder.mPlaying.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class SeriesViewRecyclerViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLayout;
        ImageView mImage;
        TextView mPlaying;
        TextView mDuration;
        TextView mTitle;
        TextView mPublish;

        public SeriesViewRecyclerViewHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout) itemView.findViewById(R.id.activity_series_content_item_layout);
            mImage = (ImageView) itemView.findViewById(R.id.activity_series_content_item_img);
            mPlaying = (TextView) itemView.findViewById(R.id.activity_series_content_item_playing);
            mDuration = (TextView) itemView.findViewById(R.id.activity_series_content_item_duration);
            mTitle = (TextView) itemView.findViewById(R.id.activity_series_content_item_title);
            mPublish = (TextView) itemView.findViewById(R.id.activity_series_content_item_publish);
            mLayout = (LinearLayout) itemView.findViewById(R.id.activity_series_content_item_layout);
        }

    }

}
