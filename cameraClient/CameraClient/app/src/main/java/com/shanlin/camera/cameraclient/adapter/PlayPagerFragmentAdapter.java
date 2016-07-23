package com.shanlin.camera.cameraclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shanlin.camera.cameraclient.MyApplication;
import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.ui.fragment.second.child.childpager.CloudPagerFragment;
import com.shanlin.camera.cameraclient.ui.fragment.second.child.childpager.LivePageFragment;
import com.shanlin.camera.cameraclient.ui.fragment.second.child.childpager.SdCardPagerFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class PlayPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab = MyApplication.getContext().getResources().getStringArray(R.array.play_type_title);
    private CameraDevice device;

    public PlayPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return LivePageFragment.newInstance(0);
        } else if( position == 1){
            return SdCardPagerFragment.newInstance(position);
        } else{
            return CloudPagerFragment.newInstance(2);
        }
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
