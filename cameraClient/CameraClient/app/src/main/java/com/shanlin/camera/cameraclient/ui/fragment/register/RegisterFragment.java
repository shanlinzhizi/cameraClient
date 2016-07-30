package com.shanlin.camera.cameraclient.ui.fragment.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.entity.BaseResultBean;
import com.shanlin.camera.cameraclient.entity.ErrorSLResponse;
import com.shanlin.camera.cameraclient.net.SLUserProxy;
import com.shanlin.camera.cameraclient.net.UserBzs;
import com.shanlin.camera.cameraclient.net.user.OnUserManualResultListener;
import com.shanlin.camera.cameraclient.net.user.UserManagerProxy;
import com.shanlin.camera.cameraclient.util.ValidateUtils;

import org.w3c.dom.Text;

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
    private EditText mEdtConfirmPwd;
    private EditText mEdtMobile;
    private EditText mEdtAge;
    private EditText mEdtSex;
    private EditText mEdtEmail;
    private EditText mEdtNick;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        UserManagerProxy.realease();
    }

    public void initView(View view){
        mImgUserIcon = (ImageView) view.findViewById(R.id.img_user_icon);
        mEdtUserName = (EditText) view.findViewById(R.id.edt_username);
        mEdtPwd = (EditText) view.findViewById(R.id.edt_pwd);
        mEdtVerifyCode = (EditText) view.findViewById(R.id.edt_verify_code);
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mEdtUserName.requestFocus();

        mEdtConfirmPwd = (EditText) view.findViewById(R.id.edt_confirm_pwd);


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
        String confirmPwd = mEdtConfirmPwd.getText().toString();


        boolean isCancel = false;
        View view = null;

        if(TextUtils.isEmpty(userName)){
            isCancel = true;
            mEdtUserName.setError(getString(R.string.error_empty_str));
            view = mEdtUserName;
        }else if( TextUtils.isEmpty(pwd)){
            isCancel = true;
            view = mEdtPwd;
            mEdtPwd.setError(getString(R.string.error_empty_str));
        }else if( TextUtils.isEmpty(verifyCode)){
            isCancel = true;
            view = mEdtVerifyCode;
            mEdtVerifyCode.setError(getString(R.string.error_empty_str));
        }else if(TextUtils.isEmpty(confirmPwd)){
            isCancel = true;
            mEdtConfirmPwd.setError(getString(R.string.error_empty_str));
            view = mEdtConfirmPwd;
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

        if( !isSamePwd(pwd,confirmPwd)){
            isCancel = true;
            mEdtPwd.setError("different pwd");
            mEdtConfirmPwd.setError("different pwd");
            view = mEdtPwd;
            mEdtConfirmPwd.setText("");
        }

        if( isCancel){
            view.requestFocus();
            return;
        }else{


//            Map<String,String> params = new HashMap<>();
//            params.put("nickName","");
//            params.put("userName",userName);
//            params.put("pwd",pwd);
//            params.put("code",verifyCode);
//            UserBzs.registerUser(params);

            AppUser appUser = new AppUser();
            appUser.setUserName(userName);
            appUser.setNickName("nick");
            appUser.setPwd(pwd);
            appUser.setEmail("email");
            appUser.setMobile("mobile");
            appUser.setNickName(userName);
            appUser.setSex(1);
            appUser.setAge(11);

            Snackbar.make(mEdtUserName,"create User ", Snackbar.LENGTH_SHORT).show();

            UserManagerProxy proxy = UserManagerProxy.getInstance();
            proxy.register(appUser, new OnUserManualResultListener() {
                @Override
                public void onResult(BaseResultBean resultBean) {
                    Log.e("register", " code = " + resultBean.getResultCode() + " msg = " + resultBean.getMsg());
                    Snackbar.make(mEdtUserName,"code =  " + resultBean.getResultCode(), Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onError(ErrorSLResponse response) {
                    Log.e("register err", " code = " + response.getResultCode() + " msg = " + response.getMsg());
                    Snackbar.make(mEdtUserName,"code =  " + response.getResultCode(), Snackbar.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void resetError(){
        mEdtUserName.setError(null);
        mEdtPwd.setError(null);
//        mEdtNick.setError(null);
//        mEdtAge.setError(null);
//        mEdtEmail.setError(null);
//        mEdtSex.setError(null);
        mEdtVerifyCode.setError(null);
//        mEdtMobile.setError(null);
        mEdtConfirmPwd.setError(null);
    }


    private boolean isValidateVerifyCode(String code){
        return false;
    }

    private boolean isSamePwd(String oldPwd, String newPwd){
        if( TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)){
            return false;
        }
        return oldPwd.equals(newPwd);
    }
}
