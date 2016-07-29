package com.shanlin.camera.cameraclient.ui.fragment.liveplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseLazyMainFragment;
import com.shanlin.camera.cameraclient.ui.fragment.me.child.AvatarFragment;
import com.shanlin.camera.cameraclient.ui.fragment.play.PlayTypePagerFragment;
import com.shanlin.camera.cameraclient.ui.fragment.play.childpager.PlayControllerFragment;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class PlayFragment extends BaseLazyMainFragment {

    public static PlayFragment newInstance() {

        Bundle args = new Bundle();

        PlayFragment fragment = new PlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        initView(savedInstanceState);
        return view;
    }

    private void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadFragments();
        }

    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        // 这里可以不用懒加载,因为Adapter的场景下,Adapter内的子Fragment只有在父Fragment是show状态时,才会被Attach,Create
        if (savedInstanceState == null) {
            loadFragments();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(AvatarFragment.class) == null) {
                loadFragments();
            }
        }
    }

    private void loadFragments(){
        loadRootFragment(R.id.video_container, PlayTypePagerFragment.newInstance());
        loadRootFragment(R.id.controller_container, PlayControllerFragment.newInstance());
    }
}
