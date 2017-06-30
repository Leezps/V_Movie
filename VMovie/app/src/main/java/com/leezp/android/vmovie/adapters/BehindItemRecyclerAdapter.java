package com.leezp.android.vmovie.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leezp.android.vmovie.R;
import com.leezp.android.vmovie.activity.ItemActivity;
import com.leezp.android.vmovie.bean.BehindItemDataBean;
import com.leezp.android.vmovie.constants.AllPageTypeParams;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leezp on 2017/6/20 0020.
 */

public class BehindItemRecyclerAdapter extends RecyclerView.Adapter<BehindItemRecyclerAdapter.BehindViewHolder> {

    private List<BehindItemDataBean> data;

    public BehindItemRecyclerAdapter(List<BehindItemDataBean> data) {
        this.data = data;
    }

    public void updateBehindItemData(List<BehindItemDataBean> data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clearBehindItemData() {
        if (this.data != null) {
            this.data.clear();
        }
    }

    @Override
    public BehindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_behind_page_item_recycler_item, parent, false);
        final BehindViewHolder viewHolder = new BehindViewHolder(view);
        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fragment_behind_page_item_recycler_item_layout:
                        int position = viewHolder.getAdapterPosition();
                        BehindItemDataBean bean = data.get(position);
                        Intent intent = new Intent(v.getContext(), ItemActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("DATA_FROM", AllPageTypeParams.BEHIND_RECYCLER_PAGE);
                        bundle.putSerializable("BEHIND_DATA", bean);
                        intent.putExtra("DATA", bundle);
                        v.getContext().startActivity(intent);
                        break;
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BehindViewHolder holder, int position) {
        BehindItemDataBean dataBean = data.get(position);
        x.image().bind(holder.mImage, dataBean.getImage());
        holder.mTitle.setText(dataBean.getTitle());
        holder.mShare.setText(dataBean.getShare_num());
        holder.mLike.setText(dataBean.getLike_num());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class BehindViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mLayout;
        private ImageView mImage;
        private TextView mTitle;
        private TextView mShare;
        private TextView mLike;

        public BehindViewHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout) itemView.findViewById(R.id.fragment_behind_page_item_recycler_item_layout);
            mImage = (ImageView) itemView.findViewById(R.id.fragment_behind_page_item_recycler_item_image);
            mTitle = (TextView) itemView.findViewById(R.id.fragment_behind_page_item_recycler_item_title);
            mShare = (TextView) itemView.findViewById(R.id.fragment_behind_page_item_recycler_item_share);
            mLike = (TextView) itemView.findViewById(R.id.fragment_behind_page_item_recycler_item_like);
        }

    }

}
