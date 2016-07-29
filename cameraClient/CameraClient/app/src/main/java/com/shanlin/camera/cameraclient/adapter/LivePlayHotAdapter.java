package com.shanlin.camera.cameraclient.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.LiveHotInfo;
import com.shanlin.camera.cameraclient.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class LivePlayHotAdapter extends RecyclerView.Adapter<LivePlayHotAdapter.VH> {
    private List<LiveHotInfo> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public LivePlayHotAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_live_play_hot, parent, false);
        final VH holder = new VH(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        LiveHotInfo item = mItems.get(position);

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
        holder.inflate(item);

    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public void setDatas(List<LiveHotInfo> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public LiveHotInfo getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView tvDeviceName;
        public TextView tvDeviceCompanyName;
        public ImageView imgUserIcon;
        public TextView  tvMsgCount;
        public TextView tvLikeCount;
        public TextView tvWatchCount;
        public ImageView img;

        public VH(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgUserIcon = (ImageView) itemView.findViewById(R.id.user_icon);
            tvDeviceName = (TextView) itemView.findViewById(R.id.tv_device_name);
            tvDeviceCompanyName = (TextView) itemView.findViewById(R.id.tv_device_company);
            tvMsgCount = (TextView) itemView.findViewById(R.id.tv_msg_count);
            tvLikeCount = (TextView) itemView.findViewById(R.id.tv_like_count);
            tvWatchCount = (TextView) itemView.findViewById(R.id.tv_watching_count);
        }

        public void inflate(LiveHotInfo info){
            img.setImageResource(info.getImgUrl());
            imgUserIcon.setImageResource(info.getImgUserIconUrl());
            tvDeviceName.setText(info.getDeviceName());
            tvDeviceCompanyName.setText(info.getDeviceCompanyName());
            tvMsgCount.setText(info.getMsgCount());
            tvLikeCount.setText(info.getLikeCount());
            tvWatchCount.setText(info.getWatchingCount());
            //bind device
            itemView.setTag(info.getDevice());
        }

    }
}
