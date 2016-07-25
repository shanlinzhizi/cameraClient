package com.shanlin.camera.cameraclient.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shanlin.camera.cameraclient.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/25/16.
 */
public class AddDeviceFragment extends SupportFragment {

    public static AddDeviceFragment newInstance(){
        AddDeviceFragment addDeviceFragment = new AddDeviceFragment();
        return addDeviceFragment;
    }

    private Toolbar mToolbar;

    private EditText mEdtNickName;
    private EditText mEdtDeviceName;
    private EditText mEdtDevicePwd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_device,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
       mToolbar.setTitle(R.string.title_add_device);

        mEdtNickName = (EditText) view.findViewById(R.id.edt_nickname);
        mEdtDeviceName = (EditText) view.findViewById(R.id.edt_device_user);
        mEdtDevicePwd = (EditText) view.findViewById(R.id.edt_device_pwd);


    }
}
