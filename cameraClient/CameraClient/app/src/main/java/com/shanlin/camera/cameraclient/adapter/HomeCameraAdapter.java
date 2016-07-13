package com.shanlin.camera.cameraclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APhil on 16/7/13.
 */
public class HomeCameraAdapter extends RecyclerView.Adapter<HomeCameraAdapter.VH> {

    private static final int type_share_device = 0;
    private static final int type_person_device = 1;

//    private List<Article> mItems = new ArrayList<>();
    private List<CameraDevice> cameraDevices = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public HomeCameraAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.item_zhihu_home_first, parent, false);
//        final VH holder = new VH(view);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (mClickListener != null) {
//                    mClickListener.onItemClick(position, v, holder);
//                }
//            }
//        });
        View view;
        switch (viewType){
            case type_person_device:
                view = mInflater.inflate(R.layout.item_zhihu_home_first,parent,false);
                return new VHPersonal(view);
            case type_share_device:
                 view = mInflater.inflate(R.layout.item_zhihu_home_first,parent,false);
                return new VHShare(view);
            default:
                view = mInflater.inflate(R.layout.item_zhihu_home_first,parent,false);
                return new VHPersonal(view);
        }

    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.inflateFromModel(cameraDevices.get(position));

//        Article item = mItems.get(position);
//
//        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
//        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
//        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
//
//        holder.img.setImageResource(item.getImgRes());
//        holder.tvTitle.setText(item.getTitle());
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return cameraDevices.get(0).getDeviceType();
    }


    public void setDatas(List<CameraDevice> items) {
        cameraDevices.clear();
        cameraDevices.addAll(items);
    }

    @Override
    public int getItemCount() {
        return cameraDevices.size();
    }

    public CameraDevice getItem(int position) {
        return cameraDevices.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //通用设备
    public abstract class VH extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView img;

        public VH(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }

        public abstract  void inflateFromModel(CameraDevice device);
    }

    //共享设备
    public class VHShare extends VH{
        public VHShare (View itemView){
            super(itemView);
        }

        @Override
        public void inflateFromModel(CameraDevice device) {

        }
    }

    public class VHPersonal extends VH{

        public VHPersonal(View itemView){
            super(itemView);
        }

        @Override
        public void inflateFromModel(CameraDevice device) {

        }
    }

}
