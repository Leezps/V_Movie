package com.leezp.android.vmovie.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.ChannelContentActivity;
import com.leezp.android.vmovie.bean.ChannelDataBean;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class ChannelRecyclerAdapter extends RecyclerView.Adapter<ChannelRecyclerAdapter.ChannelViewHolder> {

    private List<ChannelDataBean> data;

    public ChannelRecyclerAdapter(List<ChannelDataBean> data) {
        this.data = data;
    }

    public void updateChannelData(List<ChannelDataBean> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_channel_item, viewGroup, false);
        final ChannelViewHolder viewHolder = new ChannelViewHolder(view);
        viewHolder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                ChannelDataBean bean = data.get(position);
                Intent intent = new Intent(v.getContext(), ChannelContentActivity.class);
                intent.putExtra("CATE_ID", bean.getCateid());
                intent.putExtra("CATE_NAME", bean.getCatename());
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder channelViewHolder, int i) {
        ChannelDataBean dataBean = data.get(i);
        channelViewHolder.mTitle.setText("#"+dataBean.getCatename()+"#");
        x.image().bind(channelViewHolder.mImage, dataBean.getIcon());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTitle;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.fragment_channel_item_image);
            mTitle = (TextView) itemView.findViewById(R.id.fragment_channel_item_title);
        }
    }

}
