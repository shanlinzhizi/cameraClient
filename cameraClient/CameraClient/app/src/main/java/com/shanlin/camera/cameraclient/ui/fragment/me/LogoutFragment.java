package com.shanlin.camera.cameraclient.ui.fragment.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.ui.fragment.me.child.AvatarFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by APhil on 16/7/29.
 */
public class LogoutFragment extends SupportFragment implements View.OnClickListener{

    public static LogoutFragment newInstance(){
        LogoutFragment fragment = new LogoutFragment();
        return fragment;
    }

    private Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        btnLogout = (Button) view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                Snackbar.make(getView(),R.string.logout,Snackbar.LENGTH_SHORT).show();
                this.onBackPressedSupport();
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        startWithPop(AvatarFragment.newInstance());
        return true;
    }
}
