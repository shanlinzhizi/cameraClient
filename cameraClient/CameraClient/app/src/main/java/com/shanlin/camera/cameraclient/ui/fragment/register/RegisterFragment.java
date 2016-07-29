package com.shanlin.camera.cameraclient.ui.fragment.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.net.UserBzs;
import com.shanlin.camera.cameraclient.util.ValidateUtils;

import java.util.HashMap;
import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/29/16.
 */
public class RegisterFragment extends SupportFragment implements View.OnClickListener{

    public static RegisterFragment newInstance(){
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    private ImageView mImgUserIcon;
    private EditText mEdtUserName;
    private EditText mEdtPwd;
    private EditText mEdtVerifyCode;
    private Button mBtnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initView(view);
        return view;
    }

    public void initView(View view){
        mImgUserIcon = (ImageView) view.findViewById(R.id.img_user_icon);
        mEdtUserName = (EditText) view.findViewById(R.id.edt_username);
        mEdtPwd = (EditText) view.findViewById(R.id.edt_pwd);
        mEdtVerifyCode = (EditText) view.findViewById(R.id.edt_verify_code);
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mEdtUserName.requestFocus();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                createUserInfo();
                break;
        }
    }

    private void createUserInfo(){
        resetError();

        String userName = mEdtUserName.getText().toString();
        String pwd = mEdtPwd.getText().toString();
        String verifyCode = mEdtVerifyCode.getText().toString();

        boolean isCancel = false;
        View view = null;

        if(TextUtils.isEmpty(userName)){
            isCancel = true;
            view = mEdtUserName;
        }else if( TextUtils.isEmpty(pwd)){
            isCancel = true;
            view = mEdtPwd;
        }else if( TextUtils.isEmpty(verifyCode)){
            isCancel = true;
            view = mEdtVerifyCode;
        }

        if (ValidateUtils.isInvalidateAccount(userName)){
            isCancel = true;
            view = mEdtUserName;
            mEdtUserName.setError(getString(R.string.error_invalidate_username));
        }else if( ValidateUtils.isInvalidatePwd(pwd)){
            isCancel = true;
            view = mEdtPwd;
            mEdtPwd.setError(getString(R.string.error_invalidate_pwd));
        }else if( isValidateVerifyCode(verifyCode)){
            isCancel = true;
            view = mEdtVerifyCode;
        }

        if( isCancel){
            view.requestFocus();
            return;
        }else{


            Map<String,String> params = new HashMap<>();
            params.put("nickName","");
            params.put("userName",userName);
            params.put("pwd",pwd);
            params.put("code",verifyCode);
            UserBzs.registerUser(params);
        }
    }

    private void resetError(){
        mEdtUserName.setError(null);
        mEdtPwd.setError(null);
    }


    private boolean isValidateVerifyCode(String code){
        return true;
    }
}
