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
import com.shanlin.camera.cameraclient.base.BaseBackFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.ui.PlayActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 7/25/16.
 */
public class EditDeviceFragment extends BaseBackFragment implements View.OnClickListener{

    public static EditDeviceFragment newInstance(Bundle args){
        EditDeviceFragment fragmentEditDevice =
                new EditDeviceFragment();
        fragmentEditDevice.setArguments(args);
        return fragmentEditDevice;
    }

    private CameraDevice mEditDevice;
    private EditText mEdtNickName;
    private EditText mEdtDeviceName;
    private EditText mEdtDevicePwd;
    private EditText mEdtDeviceSid;
    private Button   mBtnSave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_edit_device,container,false);
        mEditDevice = getArguments().getParcelable(PlayActivity.CODE_PRAC_DEVICE);
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

        Toolbar mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_edit_device);

        mEdtNickName = (EditText) view.findViewById(R.id.edt_nickname);
        mEdtDeviceName = (EditText) view.findViewById(R.id.edt_device_user);
        mEdtDevicePwd = (EditText) view.findViewById(R.id.edt_device_pwd);
        mEdtDeviceSid = (EditText) view.findViewById(R.id.edt_sid);
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);

        if( mEditDevice != null){
//            mEdtNickName.setHintEnabled(false);
//            mEdtDeviceName.setHintEnabled(false);
//            mEdtDevicePwd.setHintEnabled(false);
//            mEdtDeviceSid.setHintEnabled(false);

            mEdtNickName.setText(mEditDevice.getNickName());
            mEdtDeviceName.setText(mEditDevice.getAccessName());
            mEdtDevicePwd.setText(mEditDevice.getAccessPwd());
            mEdtDeviceSid.setText(mEditDevice.getGid());
            mEdtDeviceSid.requestFocus();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                saveDeviceInfo();
                break;
        }
    }

    private void saveDeviceInfo(){
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

            mEditDevice.setGid(sid);
            mEditDevice.setNickName(nickName);
            mEditDevice.setAccessName(userName);
            mEditDevice.setAccessPwd(pwd);
            List<CameraDevice> cameraDevices = new ArrayList<>(1);
            cameraDevices.add(mEditDevice);
            proxy.update(cameraDevices,null);
            getActivity().onBackPressed();
        }
    }

    private void resetError(){
        mEdtDeviceName.setError(null);
        mEdtNickName.setError(null);
        mEdtDevicePwd.setError(null);
    }

}
