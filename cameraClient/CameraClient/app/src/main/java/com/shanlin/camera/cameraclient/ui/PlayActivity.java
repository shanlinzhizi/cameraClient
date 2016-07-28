package com.shanlin.camera.cameraclient.ui;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.ui.fragment.second.child.ViewPagerFragment;
import com.shanlin.camera.cameraclient.ui.fragment.second.child.childpager.PlayControllerFragment;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/20/16.
 */
public class PlayActivity extends SupportActivity {

    public static final String CODE_PRAC_DEVICE = "device";
    private CameraDevice mPlayDevice = null;
    private View view;
    private ViewPagerFragment pagerFragment;
    private PlayControllerFragment controllerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getDeviceData();

        if( savedInstanceState == null){
            pagerFragment = ViewPagerFragment.newInstance();
            controllerFragment = PlayControllerFragment.newInstance();
        }else{
            pagerFragment = findFragment(ViewPagerFragment.class);
            controllerFragment = findFragment(PlayControllerFragment.class);
        }
        loadFragments();
    }

    private void getDeviceData(){
        mPlayDevice = getIntent().getParcelableExtra(CODE_PRAC_DEVICE);
    }

    private void loadFragments(){
        loadRootFragment(R.id.fl_play_container,pagerFragment);
        loadRootFragment(R.id.fl_controller_container,controllerFragment);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        finish();
    }
}
