package com.shanlin.camera.cameraclient.ui.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.net.IResponse;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/20/16.
 */
public class EmptyDeviceFragment extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener{

    public static EmptyDeviceFragment newInstance(){
        return new EmptyDeviceFragment();
    }

    private SwipeRefreshLayout mSwp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_devices,null,false);
        view.findViewById(R.id.img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,R.string.empty_promt_add,Snackbar.LENGTH_LONG).show();
                startWithPop(AddDeviceFragment.newInstance());
            }
        });

        mSwp = ((SwipeRefreshLayout)view.findViewById(R.id.refresh_layout));
        mSwp.setOnRefreshListener(this);
        return view;
    }



    @Override
    public void onRefresh() {
        DeviceManagerProxy.getInstance().getCameras(new IResponse<List<CameraDevice>>() {
            @Override
            public void onErrorResponse(Object o) {

            }

            @Override
            public void onResponse(List<CameraDevice> o) {
                if( !o.isEmpty()){
                    startWithPop(CamerasFragment.newInstance());
                }
            }
        });
        mSwp.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwp.setRefreshing(false);
            }
        },2000);

    }
}
