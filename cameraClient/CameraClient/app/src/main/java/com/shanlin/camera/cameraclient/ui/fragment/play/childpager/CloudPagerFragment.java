package com.shanlin.camera.cameraclient.ui.fragment.play.childpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class CloudPagerFragment extends BaseFragment {
    private static final String ARG_TYPE = "arg_pos";
    public static int TYPE_LIVE = 0;
    public static int TYPE_SD = 1;
    public static int TYPE_CLOUD = 2;

    private int mType = TYPE_SD;

    private TextView mTvTitle;

    public static CloudPagerFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        CloudPagerFragment fragment = new CloudPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(ARG_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_cloud, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);

        mTvTitle.setText(view.getContext().getResources().getStringArray(R.array.play_type_title)[mType]);
    }
}
