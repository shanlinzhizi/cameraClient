package com.shanlin.camera.cameraclient.ui.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/20/16.
 */
public class EmptyDeviceFragment extends SupportFragment {

    public static EmptyDeviceFragment newInstance(){
        return new EmptyDeviceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_devices,null,false);
        view.findViewById(R.id.img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,R.string.empty_promt_add,Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
