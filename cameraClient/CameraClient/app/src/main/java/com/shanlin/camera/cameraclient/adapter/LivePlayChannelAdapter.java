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
import com.shanlin.camera.cameraclient.entity.LivePlayChannelInfo;
import com.shanlin.camera.cameraclient.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class LivePlayChannelAdapter extends RecyclerView.Adapter<LivePlayChannelAdapter.VH> {
    private List<LivePlayChannelInfo> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public LivePlayChannelAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_live_play_channel, parent, false);
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
        LivePlayChannelInfo item = mItems.get(position);

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


    public void setDatas(List<LivePlayChannelInfo> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public LivePlayChannelInfo getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView tvDeviceName;

        public ImageView img;

        public VH(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvDeviceName = (TextView) itemView.findViewById(R.id.tv_device_name);

        }

        public void inflate(LivePlayChannelInfo info){
            img.setImageResource(info.getImg());
            tvDeviceName.setText(info.getTitle());
            //bind device
            itemView.setTag(info.getDevice());
        }

    }
}
