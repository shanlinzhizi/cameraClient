package com.shanlin.camera.cameraclient.ui.fragment.liveplay.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.camera.cameraclient.MainActivity;
import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.adapter.LivePlayHotAdapter;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.entity.LiveHotInfo;
import com.shanlin.camera.cameraclient.event.TabSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by APhil on 16/7/13.
 */
public class LivePlayHotFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mFab;

    private LivePlayHotAdapter mAdapter;

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



    public static LivePlayHotFragment newInstance() {

        Bundle args = new Bundle();

        LivePlayHotFragment fragment = new LivePlayHotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liveplay_hot, container, false);
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
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new LivePlayHotAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
//                CameraDetailFragment detailFragment = CameraDetailFragment.newInstance(mAdapter.getItem(position));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    setExitTransition(new Fade());
//                    setSharedElementReturnTransition(new DetailTransition());
////                    fragment.setEnterTransition(new Fade());
//                    detailFragment.setSharedElementEnterTransition(new DetailTransition());
//                    // 因为使用add的原因,Material过渡动画只有在进栈时有,返回时没有;
//                    // 如果想进栈和出栈都有过渡动画,需要replace,目前库暂不支持,后续会调研看是否可以支持
//                    startWithSharedElement(detailFragment, ((HomeCameraAdapter.VH) vh).imgCamera, getResources().getString(R.string.image_transition));
//                } else {
//                    // 这里如果5.0以下系统调用startWithSharedElement(),会无动画,所以低于5.0,start(fragment)
//                    start(detailFragment);
//
//                }
//            }
//        });

//        mAdapter.setOnItemPlayClickListener(new OnItemPlayClickListener() {
//            @Override
//            public void onItemPlayClick(int position, View view, RecyclerView.ViewHolder vh) {
//
////                PlayFragment  playFragment = PlayFragment.newInstance();
////                Bundle bundle = new Bundle();
////                bundle.putParcelable("device",mAdapter.getItem(position));
////                playFragment.setArguments(bundle);
////
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    setExitTransition(new Fade());
////                    setSharedElementReturnTransition(new DetailTransition());
//////                    fragment.setEnterTransition(new Fade());
////                    playFragment.setSharedElementEnterTransition(new DetailTransition());
//////                    replaceFragment(playFragment,false);
////                    startWithSharedElement(playFragment,((HomeCameraAdapter.VH)vh).imgCamera,"image_transition");
////                } else {
////                    // 这里如果5.0以下系统调用startWithSharedElement(),会无动画,所以低于5.0,start(fragment)
////                    start(playFragment);
////                }
//                Intent intent = new Intent(getActivity(), PlayActivity.class);
////                ActivityOptionsCompat optionsCompat = new ActivityOptionsCompat();
//                CameraDevice device = mAdapter.getItem(position);
//                intent.putExtra(PlayActivity.CODE_PRAC_DEVICE,device);
//                startActivity(intent);
//
//            }
//        });

        onRefresh();

    }

    @Override
    public void onRefresh() {

        mAdapter.setDatas(getTestList());

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

    List<LiveHotInfo> infos;
    private List<LiveHotInfo> getTestList(){
        if( infos == null) {
            infos = new ArrayList<>(15);
            LiveHotInfo item;
            CameraDevice device = new CameraDevice();
            device.setGid("dev6");
            device.setAccessName("client002");
            device.setAccessPwd("client002");
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                item = new LiveHotInfo();
                item.setDeviceName(i + "个设备");
                item.setDeviceCompanyName(i + " company");
                int index = i % 5;
                item.setImgUrl(mImgRes[index]);
                item.setImgUserIconUrl(mImgRes[index]);
                int randon = random.nextInt(20);
                item.setLikeCount(Math.pow(randon, 2) + "");
                item.setMsgCount(Math.pow(randon, 2) + "");
                item.setWatchingCount(Math.pow(randon, 2) + "watching");
                item.setDevice(device);

                infos.add(item);
            }
        }
        Collections.shuffle(infos);
        return infos;
    }
}