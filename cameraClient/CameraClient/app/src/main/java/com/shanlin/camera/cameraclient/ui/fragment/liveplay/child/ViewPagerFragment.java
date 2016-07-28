package com.shanlin.camera.cameraclient.ui.fragment.liveplay.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.adapter.PlayPagerFragmentAdapter;
import com.shanlin.camera.cameraclient.base.BaseFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class ViewPagerFragment extends BaseFragment {
    private TabLayout mTab;
    private ViewPager mViewPager;
    public static final String PRC_DEV = "device";

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_pager, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        PlayPagerFragmentAdapter pagerAdapter = new PlayPagerFragmentAdapter(
                getChildFragmentManager());
        Bundle bundle = getArguments();
        if( bundle != null){
            pagerAdapter.setBundle(bundle);
        }
        mViewPager.setAdapter(pagerAdapter);
        mTab.setupWithViewPager(mViewPager);
    }

}
