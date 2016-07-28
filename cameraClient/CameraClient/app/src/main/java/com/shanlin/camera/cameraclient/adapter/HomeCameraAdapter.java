package com.shanlin.camera.cameraclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.listener.OnItemClickListener;
import com.shanlin.camera.cameraclient.listener.OnItemPlayClickListener;
import com.shanlin.camera.cameraclient.ui.PlayActivity;

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
    private OnItemPlayClickListener itemPlayClickListener;


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
        final View view;
        final VH viewHolder;
        switch (viewType){
            case type_person_device:
                view = mInflater.inflate(R.layout.item_camera_person,parent,false);
                viewHolder =  new VHPersonal(view);
                break;
            case type_share_device:
                 view = mInflater.inflate(R.layout.item_camera_share,parent,false);
                viewHolder =  new VHShare(view);
                break;
            default:
                view = mInflater.inflate(R.layout.item_camera_person,parent,false);
                viewHolder =  new VHPersonal(view);
                break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mClickListener != null){
                    mClickListener.onItemClick(viewHolder.getAdapterPosition(),v,viewHolder);
                }
            }
        });
        if( viewHolder.imgPlay != null) {
            viewHolder.imgPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemPlayClickListener != null) {
                        itemPlayClickListener.onItemPlayClick(viewHolder.getAdapterPosition(), view, viewHolder);
                    }
                }
            });
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.inflateFromModel(cameraDevices.get(position),mClickListener);

//        Article item = mItems.get(position);
//
//        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
//        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.imgCamera, String.valueOf(position) + "_image");
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
        return cameraDevices.get(position).getDeviceType();
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

    public void setOnItemPlayClickListener(OnItemPlayClickListener itemPlayClickListener){
        this.itemPlayClickListener = itemPlayClickListener;
    }

    private void goToPlayPage(Context context, CameraDevice device){
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(PlayActivity.CODE_PRAC_DEVICE,device);
        context.startActivity(intent);
    }

    //通用设备
    public abstract class VH extends RecyclerView.ViewHolder {
        public ImageView imgCamera;
        public TextView tvGid;
        public TextView tvDeviceName;
        public TextView tvDeviceDesc;
        public ImageButton imgPlay;

        public VH(View itemView) {
            super(itemView);

            tvDeviceName = (TextView) itemView.findViewById(R.id.tv_camera_name);
            imgCamera = (ImageView) itemView.findViewById(R.id.img_camera);
            tvGid = (TextView) itemView.findViewById(R.id.tv_camera_gid);
            tvDeviceDesc = (TextView) itemView.findViewById(R.id.tv_camera_desc);
            imgPlay = (ImageButton) itemView.findViewById(R.id.btn_play);
        }

        public abstract  void inflateFromModel(CameraDevice device,OnItemClickListener listener);
    }

    //共享设备
    public class VHShare extends VH{
        public VHShare (View itemView){
            super(itemView);
        }

        @Override
        public void inflateFromModel(CameraDevice device, final OnItemClickListener listener) {
            int imgRes = device.getImg();
            imgCamera.setImageResource((imgRes == 0 ? R.drawable.img_1 : imgRes));
            tvDeviceName.setText(device.getNickName());
            tvGid.setText(device.getGid());
            tvDeviceDesc.setText(TextUtils.isEmpty(device.getDesc()) ? "null" : device.getDesc());

        }
    }

    public class VHPersonal extends VH{

        public VHPersonal(View itemView){
            super(itemView);
        }

        @Override
        public void inflateFromModel(CameraDevice device,OnItemClickListener listener) {
            int imgRes = device.getImg();
            imgCamera.setImageResource((imgRes == 0 ? R.drawable.img_1 : imgRes));
            tvDeviceName.setText(device.getNickName());
            tvGid.setText("personal "+device.getGid());
            tvDeviceDesc.setText(TextUtils.isEmpty(device.getDesc()) ? "null" : device.getDesc());
        }
    }



}
