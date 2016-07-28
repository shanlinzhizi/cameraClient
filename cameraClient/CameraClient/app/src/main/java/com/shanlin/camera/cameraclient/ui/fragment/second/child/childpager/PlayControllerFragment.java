package com.shanlin.camera.cameraclient.ui.fragment.second.child.childpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by APhil on 16/7/21.
 */
public class PlayControllerFragment extends SupportFragment implements View.OnClickListener{

    public static PlayControllerFragment newInstance(){

        return new PlayControllerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_controller, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
