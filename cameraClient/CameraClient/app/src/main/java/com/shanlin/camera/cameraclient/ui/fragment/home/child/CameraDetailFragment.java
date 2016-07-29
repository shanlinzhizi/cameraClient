package com.shanlin.camera.cameraclient.ui.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseBackFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.ui.fragment.play.PlayActivity;
import com.shanlin.camera.cameraclient.ui.fragment.shop.ShopCameraDetailFragment;

import java.util.Arrays;

/**
 * Created by APhil on 16/7/14.
 */
public class CameraDetailFragment extends BaseBackFragment
        implements View.OnClickListener {

    private static final String ARG_ITEM = "arg_item";

    private CameraDevice device;

    private Toolbar mToolbar;
    private ImageView mImgDetail;
    private TextView mTvTitle;
    private TextView mTvDesc;
    private TextView mTvGid;
    private CheckBox mCbxModify;
    private Button mBtnDelete;


    public static CameraDetailFragment newInstance(CameraDevice cameraDevice){
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, cameraDevice);
        CameraDetailFragment fragment = new CameraDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mImgDetail = (ImageView) view.findViewById(R.id.img_detail);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvDesc = (TextView) view.findViewById(R.id.tv_desc_test);
        mTvGid = (TextView) view.findViewById(R.id.tv_gid);
        mCbxModify = (CheckBox) view.findViewById(R.id.btn_modify);
        mBtnDelete = (Button) view.findViewById(R.id.btn_delete);

        mToolbar.setTitle("");
        initToolbarNav(mToolbar);
        mImgDetail.setImageResource(device.getImg());
        mTvTitle.setText(device.getNickName());
        mTvDesc.setText("其他功能设置在这里   /n /n"+device.getDesc());
        mTvGid.setText(device.getGid());
        mImgDetail.setOnClickListener(this);
        mCbxModify.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.img_detail:
               start(ShopCameraDetailFragment.newInstance(1));
               break;
           case R.id.btn_modify:
               boolean editable = mCbxModify.isChecked();
//               if( editable) {
//                   Snackbar.make(view, "here or |new Fragment ?", Snackbar.LENGTH_LONG).show();
//               }
               setEditable(editable);
               break;
           case R.id.btn_delete:
               deleteDevice();
               break;
       }
    }

    private void setEditable(boolean editable){
        Bundle bundle = new Bundle();
        bundle.putParcelable(PlayActivity.CODE_PRAC_DEVICE,device);
        startWithPop(EditDeviceFragment.newInstance(bundle));
    }

    private void deleteDevice(){
        DeviceManagerProxy proxy = DeviceManagerProxy.getInstance();
        CameraDevice[] devices = {device};
        proxy.delete(Arrays.asList(devices),null);
        startWithPop(CamerasFragment.newInstance());
    }
}
