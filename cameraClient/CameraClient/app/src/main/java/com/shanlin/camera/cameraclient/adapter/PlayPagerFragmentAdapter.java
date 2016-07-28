package com.shanlin.camera.cameraclient.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shanlin.camera.cameraclient.MyApplication;
import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.ui.fragment.liveplay.child.childpager.CloudPagerFragment;
import com.shanlin.camera.cameraclient.ui.fragment.liveplay.child.childpager.LivePageFragment;
import com.shanlin.camera.cameraclient.ui.fragment.liveplay.child.childpager.SdCardPagerFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class PlayPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab = MyApplication.getContext().getResources().getStringArray(R.array.play_type_title);

    private Bundle bundle = null;

    public PlayPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment =  LivePageFragment.newInstance(0);
        } else if( position == 1){
            fragment = SdCardPagerFragment.newInstance(position);
        } else{
            fragment =  CloudPagerFragment.newInstance(2);
        }
        if( bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }
}
