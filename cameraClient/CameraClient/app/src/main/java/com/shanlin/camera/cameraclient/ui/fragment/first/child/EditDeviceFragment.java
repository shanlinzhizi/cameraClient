package com.shanlin.camera.cameraclient.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseBackFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.ui.PlayActivity;

/**
 * Created by feng on 7/25/16.
 */
public class EditDeviceFragment extends BaseBackFragment {

    public static EditDeviceFragment newInstance(Bundle args){
        EditDeviceFragment fragmentEditDevice =
                new EditDeviceFragment();
        fragmentEditDevice.putNewBundle(args);
        return fragmentEditDevice;
    }

    private CameraDevice mEditDevice;
    private EditText mEdtNickName;
    private EditText mEdtDeviceName;
    private EditText mEdtDevicePwd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_edit_device,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditDevice = getArguments().getParcelable(PlayActivity.CODE_PRAC_DEVICE);
    }

    private void initView(View view){

        Toolbar mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_edit_device);

        mEdtNickName = (EditText) view.findViewById(R.id.edt_nickname);
        mEdtDeviceName = (EditText) view.findViewById(R.id.edt_device_user);
        mEdtDevicePwd = (EditText) view.findViewById(R.id.edt_device_pwd);

        if( mEditDevice != null){
            mEdtNickName.setText(mEditDevice.getNickName());
            mEdtDeviceName.setText(mEditDevice.getAccessName());
            mEdtDevicePwd.setText(mEditDevice.getAccessPwd());
        }

    }
}
