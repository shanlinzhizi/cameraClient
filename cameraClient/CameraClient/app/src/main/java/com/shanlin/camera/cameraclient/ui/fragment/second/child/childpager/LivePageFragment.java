package com.shanlin.camera.cameraclient.ui.fragment.second.child.childpager;

import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.base.BaseFragment;
import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.ui.PlayActivity;
import com.sl.media.AView;
import com.sl.media.AViewRenderer;
import com.sl.media.MediaPlayer;
import com.sl.media.SLDataSource;
import com.sl.media.SLDataSourceListener;
import com.sl.media.VideoRenderer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class LivePageFragment extends BaseFragment
        implements SLDataSourceListener, VideoRenderer.OnVideoSizeChangedListener {
    private static final String ARG_TYPE = "arg_pos";
    public static int TYPE_HOT = 1;
    public static int TYPE_FAV = 2;

    private int mType = TYPE_HOT;

    private TextView mTvTitle;

    private CameraDevice mPlayDevice;


    private MediaPlayer player = null;

    private int screenWidth, screenHeight;
    private Rect rect = new Rect();

    private RelativeLayout videowindow = null;
    private AView mVideoView = null;
    private TextView rateView = null, infoView = null;


    public static LivePageFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        LivePageFragment fragment = new LivePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mType = getArguments().getInt(ARG_TYPE);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_live, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
//        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
//
//        mTvTitle.setText(view.getContext().getResources().getStringArray(R.array.play_type_title)[mType]);
        changeLayout(view.getContext().getResources().getConfiguration());

        videowindow = (RelativeLayout) view.findViewById(R.id.video_window0);

        mVideoView = new AView(view.getContext());
        mVideoView.setBackgroundColor(0xff000000);

        RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        viewParams.leftMargin = 0;
        viewParams.topMargin = 0;
        videowindow.addView(mVideoView, viewParams);

        rateView = (TextView) view.findViewById(R.id.textview0);
        infoView = (TextView) view.findViewById(R.id.textview1);

        Bundle extra = getArguments();
        mPlayDevice = extra.getParcelable(PlayActivity.CODE_PRAC_DEVICE);

        handler.sendEmptyMessage(LAYOUT_INIT);
        handler.sendEmptyMessageDelayed(VIDEO_PLAY, 0);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //hideStatusBar();
//        setContentView(R.layout.activity_play);


    }

    private boolean play() {
        mPlayDevice = new CameraDevice();
        mPlayDevice.setGid("client002");
        mPlayDevice.setAccessPwd("88888888");
        mPlayDevice.setAccessName("dev6");

        if (mPlayDevice != null && player == null) {
            VideoRenderer renderer = new AViewRenderer(getContext(), mVideoView);
            renderer.setOnVideoSizeChangedListener(this);

            SLDataSource source = new SLDataSource();

            source.setParameters(mPlayDevice.getGid(),mPlayDevice.getAccessName(),
                    mPlayDevice.getAccessPwd(), 0, 3, 0);

            source.setDataSourceListener(this);

            player = new MediaPlayer();
            player.prepare();

            player.setDataSource(source);
            player.setDisplay(renderer);
            player.start();
            return true;
        }
        return false;
    }

    private void stop(){
        if(player != null){
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        stop();
        super.onDestroy();
    }

    private void initVideoWindowLayout() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        rect.left = 0;
        rect.top = 0;
        rect.right = screenWidth;
        rect.bottom = screenHeight / h;

        setVideowindowLayoutParams(rect);
    }

    private void setVideowindowLayoutParams(Rect r){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)videowindow.getLayoutParams();
        params.leftMargin = r.left;
        params.topMargin = r.top;
        params.width = r.right;
        params.height = r.bottom;
        videowindow.setLayoutParams(params);

        videoviewLocateTo(r.right, r.bottom);
    }

    private void videoviewLocateTo(int width, int height){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mVideoView.getLayoutParams();
        params.leftMargin = 0;
        params.topMargin = 0;
        params.width = width;
        params.height = height;
        mVideoView.setLayoutParams(params);
    }

    /**
     * 对图片进行初始化操作，包括让图片居中，以及当图片大于屏幕宽高时对图片进行压缩
     */
    private void initMatrix(Matrix matrix, int videoWidth, int videoHeight) {
        matrix.reset();

        int winWidth = rect.right;
        int winHeight = rect.bottom;

        if (videoWidth > winWidth || videoHeight > winHeight) {
            if (videoWidth - winWidth > videoHeight - winHeight) {
                // 当图片宽度大于屏幕宽度时，将图片等比例压缩，使它可以完全显示出来
                float ratio = winWidth / (videoWidth * 1.0f);
                matrix.postScale(ratio, ratio);
                float translateY = (winHeight - (videoHeight * ratio)) / 2f;
                // 在纵坐标方向上进行偏移，以保证图片居中显示
                matrix.postTranslate(0, translateY);
            } else {
                // 当图片高度大于屏幕高度时，将图片等比例压缩，使它可以完全显示出来
                float ratio = winHeight / (videoHeight * 1.0f);
                matrix.postScale(ratio, ratio);
                float translateX = (winWidth - (videoWidth * ratio)) / 2f;
                // 在横坐标方向上进行偏移，以保证图片居中显示
                matrix.postTranslate(translateX, 0);
            }
        } else {
            // 当图片的宽高都小于屏幕宽高时，直接让图片居中显示
            float translateX = (winWidth - videoWidth) / 2f;
            float translateY = (winHeight - videoHeight) / 2f;
            matrix.postTranslate(translateX, translateY);
        }
    }

    int h = 1;
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeLayout(newConfig);
    }

    private void changeLayout(Configuration newConfig) {
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            h = 1;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            h = 2;
        }
        handler.sendEmptyMessage(LAYOUT_INIT);
    }

    // ----------------------------------------------
    private static final int LAYOUT_INIT = 10;
    private static final int VIDEO_PLAY = 20;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LAYOUT_INIT: {
                    initVideoWindowLayout();
                    break;
                }
                case VIDEO_PLAY: {
                    play();
                    break;
                }
            }
        }
    };

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.CHINESE);
    private StringBuffer infoBuffer = new StringBuffer();

    //--------------------------------------------------
    @Override
    public void onVideoSizeChanged(VideoRenderer renderer, int width, int height) {
        initMatrix(((AViewRenderer)renderer).getMatrix(), width, height);

        infoBuffer.append(String.format("\n[%s]onDisplay: %dx%d", dateFormat.format(new Date()), width, height));
        infoView.setText(infoBuffer.toString());
    }

    //--------------------------------------------------
    @Override
    public void onReConnecting(){
        infoBuffer.append(String.format("\n[%s]onReConnecting", dateFormat.format(new Date())));
        infoView.setText(infoBuffer.toString());
    }

    @Override
    public void onConnected(int mode, String ip, int port) {
        infoBuffer.append(String.format("\n[%s]onConnected\nmode: %d, ip: %s, port: %d", dateFormat.format(new Date()), mode, ip, port));
        infoView.setText(infoBuffer.toString());
    }

    @Override
    public void onAuth(int result) {
        infoBuffer.append(String.format("\n[%s]onAuth\nresult: %d", dateFormat.format(new Date()), result));
        infoView.setText(infoBuffer.toString());
    }

    @Override
    public void onDisconnected(int errcode) {
        infoBuffer.append(String.format("\n[%s]onDisconnected\nresult: %d", dateFormat.format(new Date()), errcode));
        infoView.setText(infoBuffer.toString());
    }

    @Override
    public void onDataRate(int bytesPresecond) {
        if(bytesPresecond > 1024){
            rateView.setText((bytesPresecond/1024) + "KB/s");
        }else{
            rateView.setText(bytesPresecond + "B/s");
        }
    }

}
