package com.shanlin.camera.cameraclient.ui.fragment.first.child;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shanlin.camera.cameraclient.MainActivity;
import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.adapter.HomeCameraAdapter;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.event.TabSelectedEvent;
import com.shanlin.camera.cameraclient.helper.DetailTransition;
import com.shanlin.camera.cameraclient.listener.OnItemClickListener;
import com.shanlin.camera.cameraclient.listener.OnItemPlayClickListener;
import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.net.IResponse;
import com.shanlin.camera.cameraclient.ui.fragment.second.PlayFragment;

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

    private String[] mTitles = new String[]{
            "超美艳的18位古装新娘：朱茵、唐嫣、赵丽颖、佟丽娅等",
            "盘点娱乐圈女星，你肯定想不到还有她",
            "娱圈中原配与情人相处无事的明星们，原来大傻一点都不傻",
            "娱乐圈十大演技好、绯闻绝缘体女星，无视潜规则难上位",
            "他是中国最牛X主持,是龙年春晚主持第七人"
    };

    private int[] mImgRes = new int[]{
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_1, R.drawable.img_2
    };



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
                PlayFragment  playFragment = PlayFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putParcelable("device",mAdapter.getItem(position));
                playFragment.setArguments(bundle);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setExitTransition(new Fade());
                    setSharedElementReturnTransition(new DetailTransition());
//                    fragment.setEnterTransition(new Fade());
                    playFragment.setSharedElementEnterTransition(new DetailTransition());
                    // 因为使用add的原因,Material过渡动画只有在进栈时有,返回时没有;
                    // 如果想进栈和出栈都有过渡动画,需要replace,目前库暂不支持,后续会调研看是否可以支持
//                    startWithSharedElement(playFragment, ((HomeCameraAdapter.VH) vh).imgCamera, getResources().getString(R.string.image_transition));
//                    replaceFragment(playFragment,false);
                } else {
                    // 这里如果5.0以下系统调用startWithSharedElement(),会无动画,所以低于5.0,start(fragment)
//                    start(playFragment);
//                    replaceFragment(playFragment,false);
                }

            }
        });
//        DeviceManagerProxy.getInstance().setProxy(new TestGetCameraProxy());
       DeviceManagerProxy.getInstance().getCameras(new IResponse<List<CameraDevice>>() {
           @Override
           public void onErrorResponse(Object o) {
               replaceFragment(EmptyDeviceFragment.newInstance(),true);
           }

           @Override
           public void onResponse(List<CameraDevice> devices) {
                if( devices.isEmpty()){
                    replaceFragment(EmptyDeviceFragment.newInstance(),true);
                }else{
                    mAdapter.setDatas(devices);
                }
           }
       });

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
                Toast.makeText(_mActivity, "Action", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {

        DeviceManagerProxy.getInstance().refresh(new IResponse<List<CameraDevice>>() {
            @Override
            public void onErrorResponse(Object o) {
                replaceFragment(EmptyDeviceFragment.newInstance(),true);
            }

            @Override
            public void onResponse(List<CameraDevice> devices) {
                if( devices.isEmpty()){
                    replaceFragment(EmptyDeviceFragment.newInstance(),true);
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
