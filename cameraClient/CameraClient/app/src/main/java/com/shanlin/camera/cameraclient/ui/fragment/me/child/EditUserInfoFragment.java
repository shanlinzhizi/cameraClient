package com.shanlin.camera.cameraclient.ui.fragment.me.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.util.ValidateUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/29/16.
 */
public class EditUserInfoFragment extends SupportFragment implements View.OnClickListener{

    public static EditUserInfoFragment newInstance(){
        EditUserInfoFragment fragment = new EditUserInfoFragment();
        return fragment;
    }

    private ImageView mImgUserIcon;
    private EditText mEdtUserName;
    private EditText mEdtPwd;
    private EditText mEdtVerifyCode;
    private Button mBtnSave;
    private EditText mEdtMobile;
    private EditText mEdtAge;
    private EditText mEdtSex;
    private EditText mEdtEmail;
    private EditText mEdtNick;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_userinfo,container,false);
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

        mEdtMobile = (EditText) view.findViewById(R.id.edt_mobile);
        mEdtAge = (EditText) view.findViewById(R.id.edt_age);
        mEdtSex = (EditText) view.findViewById(R.id.edt_sex);
        mEdtEmail = (EditText) view.findViewById(R.id.edt_email);
        mEdtNick = (EditText) view.findViewById(R.id.edt_user_nick);
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
        String mobile = mEdtMobile.getText().toString();
        String age = mEdtAge.getText().toString();
        String sex = mEdtSex.getText().toString();
        String email = mEdtEmail.getText().toString();
        String nickName = mEdtNick.getText().toString();

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
        }else if( TextUtils.isEmpty(mobile)){
            isCancel = true;
            mEdtMobile.setError(getString(R.string.error_empty_str));
            view = mEdtMobile;
        }else if( TextUtils.isEmpty(age)){
            mEdtAge.setError(getString(R.string.error_empty_str));
            isCancel = true;
            view = mEdtAge;
        }else if( TextUtils.isEmpty(sex)){
            mEdtSex.setError(getString(R.string.error_empty_str));
            isCancel = true;
            view = mEdtSex;
        }else if( TextUtils.isEmpty(email)){
            mEdtEmail.setError(getString(R.string.error_empty_str));
            isCancel = true;
            view = mEdtEmail;
        }else if( TextUtils.isEmpty(nickName)){
            mEdtNick.setError(getString(R.string.error_empty_str));
            isCancel = true;
            view = mEdtNick;
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
        }else if( ValidateUtils.isInvalidateMobile(mobile)){
            isCancel = true;
            mEdtMobile.setError(getString(R.string.error_invalidate_phone));
            view = mEdtMobile;
        }else if( TextUtils.isEmpty(age)){
            mEdtAge.setError(getString(R.string.error_invalidate_age));
            isCancel = true;
            view = mEdtAge;
        }else if( ValidateUtils.isInvalidateEmail(email)){
            isCancel = true;
            mEdtEmail.setError(getString(R.string.error_invalid_email));
            view = mEdtEmail;
        }else if( ValidateUtils.isInvalidateAccount(nickName)){
            isCancel = true;
            mEdtNick.setError(getString(R.string.error_invalidate_username));
            view = mEdtNick;
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
            appUser.setNickName(nickName);
            appUser.setPwd(pwd);
            appUser.setEmail(email);
            appUser.setMobile(mobile);
            appUser.setNickName(userName);
            appUser.setSex(Integer.valueOf(sex));
            appUser.setAge(Integer.valueOf(age));

            Snackbar.make(getView(),"create User ", Snackbar.LENGTH_SHORT).show();

//            UserManagerProxy proxy = new UserManagerProxy();
//            proxy.register(appUser, new OnUserManualResultListener() {
//                @Override
//                public void onResult(BaseResultBean resultBean) {
//                    Log.e("register", " code = " + resultBean.getResultCode() + " msg = " + resultBean.getMsg());
//                }
//
//                @Override
//                public void onError(ErrorSLResponse response) {
//                    Log.e("register err", " code = " + response.getResultCode() + " msg = " + response.getMsg());
//                }
//            });

        }
    }

    private void resetError(){
        mEdtUserName.setError(null);
        mEdtPwd.setError(null);
        mEdtNick.setError(null);
        mEdtAge.setError(null);
        mEdtEmail.setError(null);
        mEdtSex.setError(null);
        mEdtVerifyCode.setError(null);
        mEdtMobile.setError(null);
    }


    private boolean isValidateVerifyCode(String code){
        return true;
    }
}
