package com.shanlin.camera.cameraclient.ui.fragment.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.ui.fragment.play.childpager.PlayControllerFragment;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by feng on 7/20/16.
 */
public class PlayActivity extends SupportActivity {

    public static final String CODE_PRAC_DEVICE = "device";
    private CameraDevice mPlayDevice = null;
    private View view;
    private PlayTypePagerFragment pagerFragment;
    private PlayControllerFragment controllerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getDeviceData();

        if( savedInstanceState == null){
            pagerFragment = PlayTypePagerFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putParcelable(CODE_PRAC_DEVICE,mPlayDevice);
            pagerFragment.setArguments(bundle);
            controllerFragment = PlayControllerFragment.newInstance();
        }else{
            pagerFragment = findFragment(PlayTypePagerFragment.class);
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
