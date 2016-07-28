package com.shanlin.camera.cameraclient.ui.fragment.home.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/25/16.
 */
public class AddDeviceFragment extends SupportFragment implements View.OnClickListener{

    public static AddDeviceFragment newInstance(){
        AddDeviceFragment addDeviceFragment = new AddDeviceFragment();
        return addDeviceFragment;
    }

    private Toolbar mToolbar;

    private EditText mEdtNickName;
    private EditText mEdtDeviceName;
    private EditText mEdtDevicePwd;
    private EditText mEdtDeviceSid;

    private Button mBtnCreate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_device,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forceOpenSoftKeyboard(getContext());
    }

    public  void forceOpenSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void initView(View view){

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_add_device);

        mEdtNickName = (EditText) view.findViewById(R.id.edt_nickname);
        mEdtDeviceName = (EditText) view.findViewById(R.id.edt_device_user);
        mEdtDevicePwd = (EditText) view.findViewById(R.id.edt_device_pwd);
        mEdtDeviceSid = (EditText) view.findViewById(R.id.edt_sid);
        mBtnCreate = (Button) view.findViewById(R.id.btn_create);
        mBtnCreate.setOnClickListener(this);

        mEdtDeviceSid.requestFocus();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                createDevice();
                break;
        }
    }

    private void createDevice(){
        resetError();

        boolean isCancel = false;
        View focusView = null;
        String nickName = mEdtNickName.getText().toString();
        String userName = mEdtDeviceName.getText().toString();
        String pwd = mEdtDevicePwd.getText().toString();
        String sid = mEdtDeviceSid.getText().toString();

        if( TextUtils.isEmpty(sid)){
            isCancel = true;
            focusView = mEdtDeviceSid;
        }else if(TextUtils.isEmpty(nickName)){
            mEdtNickName.setError(getString(R.string.error_empty_str));
            isCancel = true;
            focusView = mEdtNickName;
        }else if( TextUtils.isEmpty(userName)){
            mEdtDeviceName.setError(getString(R.string.error_empty_str));
            isCancel = true;
            focusView = mEdtDeviceName;
        }else if( TextUtils.isEmpty(pwd)){
            mEdtDevicePwd.setError(getString(R.string.error_empty_str));
            isCancel = true;
            focusView = mEdtDevicePwd;
        }

        if( isCancel ){
            focusView.requestFocus();
        }else{
            DeviceManagerProxy proxy = DeviceManagerProxy.getInstance();
            CameraDevice device = new CameraDevice();
            device.setGid(sid);
            device.setNickName(nickName);
            device.setAccessName(userName);
            device.setAccessPwd(pwd);
            List<CameraDevice> cameraDevices = new ArrayList<>(1);
            cameraDevices.add(device);
            proxy.add(cameraDevices,null);
            startWithPop(CamerasFragment.newInstance());
        }
    }

    private void resetError(){
        mEdtDeviceName.setError(null);
        mEdtNickName.setError(null);
        mEdtDevicePwd.setError(null);
    }


    @Override
    public boolean onBackPressedSupport() {
        startWithPop(CamerasFragment.newInstance());
        return true;
    }
}
