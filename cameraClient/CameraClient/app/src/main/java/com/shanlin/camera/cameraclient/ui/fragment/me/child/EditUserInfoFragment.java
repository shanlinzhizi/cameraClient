package com.shanlin.camera.cameraclient.ui.fragment.me.child;

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
import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.net.UserBzs;
import com.shanlin.camera.cameraclient.util.ValidateUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by APhil on 16/7/29.
 */
public class EditUserInfoFragment extends SupportFragment
        implements View.OnClickListener{

    public static EditUserInfoFragment newInstance(){
        EditUserInfoFragment fragment = new EditUserInfoFragment();
        return fragment;
    }

    private ImageView mImgUserIcon;
    private EditText mEdtUserName;
    private EditText mEdtPwd;
    private Button mBtnSave;

    private EditUserPresenter presenter;

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
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mEdtUserName.requestFocus();

        AppUser user = UserBzs.getAppUser();
        if( user != null){
            mEdtUserName.setText(user.getNickName());
            mEdtPwd.setText(user.getPwd());
            mImgUserIcon.setImageResource(user.getUserImg());
        }

        presenter = new EditUserPresenterImpl();
    }

    @Override
    public boolean onBackPressedSupport() {
        startWithPop(AvatarFragment.newInstance());
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                saveUserInfo();
                break;
        }
    }

    private void saveUserInfo(){
        resetError();

        String userName = mEdtUserName.getText().toString();
        String pwd = mEdtPwd.getText().toString();

        boolean isCancel = false;
        View view = null;

        if(TextUtils.isEmpty(userName)){
            isCancel = true;
            view = mEdtUserName;
        }else if( TextUtils.isEmpty(pwd)){
            isCancel = true;
            view = mEdtPwd;
        }

        if (ValidateUtils.isInvalidateAccount(userName)){
            isCancel = true;
            view = mEdtUserName;
            mEdtUserName.setError(getString(R.string.error_invalidate_username));
        }else if( ValidateUtils.isInvalidatePwd(pwd)){
            isCancel = true;
            view = mEdtPwd;
            mEdtPwd.setError(getString(R.string.error_invalidate_pwd));
        }

        if( isCancel){
            view.requestFocus();
            return;
        }else{
            AppUser user = UserBzs.getAppUser();
            if( user == null){
                user = new AppUser();
            }

            user.setNickName(userName);
            user.setPwd(pwd);
            user.setUserImg("0");

            presenter.saveUser(user);
        }
    }

    private void resetError(){
        mEdtUserName.setError(null);
        mEdtPwd.setError(null);
    }

}
