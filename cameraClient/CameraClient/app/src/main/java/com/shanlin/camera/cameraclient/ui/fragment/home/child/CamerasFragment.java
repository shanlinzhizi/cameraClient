package com.shanlin.camera.cameraclient.ui.fragment.home.child;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.MainActivity;
import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.adapter.HomeCameraAdapter;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.entity.BaseResultBean;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.entity.ErrorSLResponse;
import com.shanlin.camera.cameraclient.entity.SearchResponse;
import com.shanlin.camera.cameraclient.entity.SearchResponseBean;
import com.shanlin.camera.cameraclient.event.TabSelectedEvent;
import com.shanlin.camera.cameraclient.helper.DetailTransition;
import com.shanlin.camera.cameraclient.listener.OnItemClickListener;
import com.shanlin.camera.cameraclient.listener.OnItemPlayClickListener;
import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.net.IResponse;
import com.shanlin.camera.cameraclient.net.user.OnUserManualResultListener;
import com.shanlin.camera.cameraclient.net.user.UserManagerProxy;
import com.shanlin.camera.cameraclient.ui.fragment.play.PlayActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by APhil on 16/7/13.
 */
public class CamerasFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mFab;

    private HomeCameraAdapter mAdapter;

    private boolean mInAtTop = true;
    private int mScrollTotal;

    public static CamerasFragment newInstance() {

        Bundle args = new Bundle();

        CamerasFragment fragment = new CamerasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_cameras, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        mToolbar.setTitle("首页");
        initToolbarMenu(mToolbar);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new HomeCameraAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                CameraDetailFragment detailFragment = CameraDetailFragment.newInstance(mAdapter.getItem(position));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setExitTransition(new Fade());
                    setSharedElementReturnTransition(new DetailTransition());
//                    fragment.setEnterTransition(new Fade());
                    detailFragment.setSharedElementEnterTransition(new DetailTransition());
                    // 因为使用add的原因,Material过渡动画只有在进栈时有,返回时没有;
                    // 如果想进栈和出栈都有过渡动画,需要replace,目前库暂不支持,后续会调研看是否可以支持
                    startWithSharedElement(detailFragment, ((HomeCameraAdapter.VH) vh).imgCamera, getResources().getString(R.string.image_transition));
                } else {
                    // 这里如果5.0以下系统调用startWithSharedElement(),会无动画,所以低于5.0,start(fragment)
                    start(detailFragment);

                }
            }
        });

        mAdapter.setOnItemPlayClickListener(new OnItemPlayClickListener() {
            @Override
            public void onItemPlayClick(int position, View view, RecyclerView.ViewHolder vh) {

//                PlayFragment  playFragment = PlayFragment.newInstance();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("device",mAdapter.getItem(position));
//                playFragment.setArguments(bundle);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    setExitTransition(new Fade());
//                    setSharedElementReturnTransition(new DetailTransition());
////                    fragment.setEnterTransition(new Fade());
//                    playFragment.setSharedElementEnterTransition(new DetailTransition());
////                    replaceFragment(playFragment,false);
//                    startWithSharedElement(playFragment,((HomeCameraAdapter.VH)vh).imgCamera,"image_transition");
//                } else {
//                    // 这里如果5.0以下系统调用startWithSharedElement(),会无动画,所以低于5.0,start(fragment)
//                    start(playFragment);
//                }
                Intent intent = new Intent(getActivity(), PlayActivity.class);
//                ActivityOptionsCompat optionsCompat = new ActivityOptionsCompat();
                CameraDevice device = mAdapter.getItem(position);
                intent.putExtra(PlayActivity.CODE_PRAC_DEVICE,device);
                startActivity(intent);

            }
        });

        onRefresh();

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                } else {
                    mInAtTop = false;
                }
                if (dy > 5) {
                    mFab.hide();
                } else if (dy < -5) {
                    mFab.show();
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(AddDeviceFragment.newInstance(),SINGLETOP);
            }
        });
    }

    @Override
    public void onRefresh() {

        DeviceManagerProxy.getInstance().refresh(new IResponse<List<CameraDevice>>() {
            @Override
            public void onErrorResponse(Object o) {
                startWithPop(EmptyDeviceFragment.newInstance());
            }

            @Override
            public void onResponse(List<CameraDevice> devices) {
                if( devices.isEmpty()){

                    startWithPop(EmptyDeviceFragment.newInstance());

                }else{
                    mAdapter.setDatas(devices);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);

        UserManagerProxy proxy =  UserManagerProxy.getInstance();
        proxy.query(0, 0, "client002", new OnUserManualResultListener<BaseResultBean<SearchResponse>>() {
            @Override
            public void onResult(BaseResultBean resultBean) {
                Log.e("query","bean result" + resultBean.getResultCode()  + " sid " + resultBean.getMsg());
                SearchResponseBean bean = (SearchResponseBean) resultBean.getTarget();
            }

            @Override
            public void onError(ErrorSLResponse response) {
                Log.w("query","bean result" + response.getResultCode() + " sid " + response.getMsg());
            }
        });

        UserManagerProxy.getInstance().add("client002", 1L, new OnUserManualResultListener() {
            @Override
            public void onResult(BaseResultBean resultBean) {
                Log.e("add","bean result" + resultBean.getResultCode()  + " sid " + resultBean.getMsg());
            }

            @Override
            public void onError(ErrorSLResponse response) {
                Log.w("add","bean result" + response.getResultCode() + " sid " + response.getMsg());
            }
        });

        UserManagerProxy.getInstance().delete("client002", new OnUserManualResultListener() {
            public void onResult(BaseResultBean resultBean) {
                Log.e("delete","bean result" + resultBean.getResultCode()  + " sid " + resultBean.getMsg());
            }

            @Override
            public void onError(ErrorSLResponse response) {
                Log.w("delete","bean result" + response.getResultCode() + " sid " + response.getMsg());
            }
        });


    }

    private void scrollToTop() {
        mRecy.smoothScrollToPosition(0);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainActivity.FIRST) return;

        if (mInAtTop) {
            mRefreshLayout.setRefreshing(true);
            onRefresh();
        } else {
            scrollToTop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecy.setAdapter(null);
        EventBus.getDefault().unregister(this);
    }
}
