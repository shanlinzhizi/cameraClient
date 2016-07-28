package com.shanlin.camera.cameraclient.ui.fragment.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseLazyMainFragment;
import com.shanlin.camera.cameraclient.ui.fragment.shop.child.ShopFragment;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class ShopCityFragment extends BaseLazyMainFragment {

    public static ShopCityFragment newInstance() {

        Bundle args = new Bundle();

        ShopCityFragment fragment = new ShopCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cameras_info, container, false);
        return view;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // ShopFragment是flow包里的
            loadRootFragment(R.id.fl_third_container, ShopFragment.newInstance());
        } else { // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(ShopFragment.class) == null) {
                loadRootFragment(R.id.fl_third_container, ShopFragment.newInstance());
            }
        }
    }
}
