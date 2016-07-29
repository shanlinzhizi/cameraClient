package com.shanlin.camera.cameraclient.ui.fragment.me.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by APhil on 16/7/29.
 */
public class EditUserInfoFragment extends SupportFragment{

    public static EditUserInfoFragment newInstance(){
        EditUserInfoFragment fragment = new EditUserInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_userinfo,container,false);
        initView(view);
        return view;
    }

    public void initView(View view){

    }

    @Override
    public boolean onBackPressedSupport() {
        startWithPop(AvatarFragment.newInstance());
        return true;
    }
}
