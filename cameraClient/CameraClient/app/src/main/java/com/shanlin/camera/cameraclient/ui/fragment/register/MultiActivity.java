package com.shanlin.camera.cameraclient.ui.fragment.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shanlin.camera.cameraclient.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by feng on 7/29/16.
 */
public class MultiActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        Intent intent = getIntent();
        String flag = intent.getStringExtra("class");

        if(GetPwdFragment.class.getSimpleName().equals(flag)){
            loadRootFragment(R.id.fl_container,GetPwdFragment.newInstance());
        }else {
            loadRootFragment(R.id.fl_container,RegisterFragment.newInstance());
        }
    }
}
