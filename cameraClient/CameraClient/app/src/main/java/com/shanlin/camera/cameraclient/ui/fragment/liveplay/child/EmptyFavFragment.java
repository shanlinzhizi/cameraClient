package com.shanlin.camera.cameraclient.ui.fragment.liveplay.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by feng on 7/20/16.
 */
public class EmptyFavFragment extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener{

    public static EmptyFavFragment newInstance(){
        return new EmptyFavFragment();
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
//                startWithPop(AddDeviceFragment.newInstance());
            }
        });

        mSwp = ((SwipeRefreshLayout)view.findViewById(R.id.refresh_layout));
        mSwp.setOnRefreshListener(this);
        return view;
    }



    @Override
    public void onRefresh() {
        Snackbar.make(getView(),R.string.empty_promt_add,Snackbar.LENGTH_LONG).show();
        mSwp.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwp.setRefreshing(false);
            }
        },2000);

    }
}
