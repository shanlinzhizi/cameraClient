package com.shanlin.camera.cameraclient.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseBackFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.ui.fragment.CycleFragment;

/**
 * Created by APhil on 16/7/14.
 */
public class CameraDetailFragment extends BaseBackFragment {

    private static final String ARG_ITEM = "arg_item";

    private CameraDevice device;

    private Toolbar mToolbar;
    private ImageView mImgDetail;
    private TextView mTvTitle;
    private TextView mTvDesc;
    private TextView mTvGid;


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

        mToolbar.setTitle("");
        initToolbarNav(mToolbar);
        mImgDetail.setImageResource(device.getImg());
        mTvTitle.setText(device.getNickName());
        mTvDesc.setText("其他功能设置在这里   /n /n"+device.getDesc());
        mTvGid.setText(device.getGid());
        mImgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }

}