package com.shanlin.camera.cameraclient.ui.fragment.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseBackFragment;
import com.shanlin.camera.cameraclient.util.ValidateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 7/29/16.
 */
public class GetPwdFragment extends BaseBackFragment implements View.OnClickListener{

    public static GetPwdFragment newInstance(){
        GetPwdFragment fragment = new GetPwdFragment();
        return fragment;
    }

    private EditText mEdtAccount;
    private GetPwdPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_pwd,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if( presenter != null){
            presenter = null;
        }
    }

    public void initView(View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        initToolbarNav(toolbar);
        toolbar.setTitle(getString(R.string.hint_get_pwd));

        view.findViewById(R.id.btn_get_pwd).setOnClickListener(this);
        mEdtAccount = (EditText) view.findViewById(R.id.edt_account);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_pwd:
                getPwd();
                break;
        }
    }

    private void getPwd(){
        resetError();

        String account = mEdtAccount.getText().toString();
        boolean isCancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(account)){
            isCancel = true;
            mEdtAccount.setError(getString(R.string.error_empty_str));
            focusView = mEdtAccount;
        }

        if(!TextUtils.isEmpty(account) && ValidateUtils.isInvalidateAccount(account)){
            isCancel = true;
            mEdtAccount.setError(getString(R.string.error_invalidate_username));
            focusView = mEdtAccount;
        }

        if( isCancel){
            focusView.requestFocus();
        }else{
            Map<String,String> params = new HashMap<>();
            params.put("account",account);
            if(presenter == null){
                presenter = new GetPwdPresenterImpl();
                presenter.getPwd(params);
            }
            Snackbar.make(getView(),R.string.hint_get_pwd,Snackbar.LENGTH_LONG).show();
        }

    }

    private void resetError(){
        mEdtAccount.setError(null);
    }


}
