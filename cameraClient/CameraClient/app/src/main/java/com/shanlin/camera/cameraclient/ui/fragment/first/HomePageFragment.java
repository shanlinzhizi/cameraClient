package com.shanlin.camera.cameraclient.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseLazyMainFragment;
import com.shanlin.camera.cameraclient.ui.fragment.first.child.CamerasFragment;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class HomePageFragment extends BaseLazyMainFragment {

    public static HomePageFragment newInstance() {

        Bundle args = new Bundle();

        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fl_first_container, FirstHomeFragment.newInstance());
            loadRootFragment(R.id.fl_first_container, CamerasFragment.newInstance());
        }
    }
}
