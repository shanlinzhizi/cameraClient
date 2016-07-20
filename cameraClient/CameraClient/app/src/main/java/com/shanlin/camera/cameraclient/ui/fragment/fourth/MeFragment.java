package com.shanlin.camera.cameraclient.ui.fragment.fourth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseLazyMainFragment;
import com.shanlin.camera.cameraclient.ui.fragment.fourth.child.AvatarFragment;
import com.shanlin.camera.cameraclient.ui.fragment.fourth.child.MeComponetFragment;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class MeFragment extends BaseLazyMainFragment {
    private Toolbar mToolbar;
    private View mView;

    public static MeFragment newInstance() {

        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, container, false);
        return mView;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(AvatarFragment.class) == null) {
                loadFragment();
            }
        }

        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        mToolbar.setTitle("我的");
        initToolbarMenu(mToolbar);
    }

    private void loadFragment() {
            loadRootFragment(R.id.fl_fourth_container_upper, AvatarFragment.newInstance());
            loadRootFragment(R.id.fl_fourth_container_lower, MeComponetFragment.newInstance());

    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }
}
