package com.shanlin.camera.cameraclient.ui.fragment.home.child;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.BaseResultBean;
import com.shanlin.camera.cameraclient.entity.BuddyType;
import com.shanlin.camera.cameraclient.entity.ErrorSLResponse;
import com.shanlin.camera.cameraclient.net.user.OnUserManualResultListener;
import com.shanlin.camera.cameraclient.net.user.UserManagerProxy;
import com.shanlin.camera.cameraclient.view.scan.RadarScanView;
import com.shanlin.camera.cameraclient.view.scan.RandomTextView;
import com.shanlin.camera.cameraclient.view.scan.RippleView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by DELL on 2016/7/30.
 */
public class ScanFragment extends SupportFragment implements View.OnClickListener{

    public static ScanFragment newInstance(){
        ScanFragment fragment = new ScanFragment();
        return fragment;
    }

    private static String TAG = ScanFragment.class.getSimpleName();

    private RadarScanView mRadarScanView;
    private RandomTextView mRandomTextView;
    private EditText mEdtSid;
    private Button mBtnScan;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan,container,false);
        initView(view);
        return view;
    }

    public  void initView(View view){
        mRadarScanView = (RadarScanView) view.findViewById(R.id.scam_radar);
        mRandomTextView = (RandomTextView) view.findViewById(R.id.random_text);
        mBtnScan = (Button) view.findViewById(R.id.btn_scan);
        mBtnScan.setOnClickListener(this);
        mEdtSid = (EditText) view.findViewById(R.id.edt_sid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_scan:
                searchDevices();
                break;
        }
    }

    private void searchDevices(){
        mEdtSid.setError(null);
        String sid = mEdtSid.getText().toString();
        if(TextUtils.isEmpty(sid)){
            mEdtSid.setError(getString(R.string.error_empty_str));
            mEdtSid.requestFocus();
            return;
        }

        UserManagerProxy proxy = UserManagerProxy.getInstance();
        proxy.query(0, BuddyType.DEVICE.ordinal(), sid, new OnUserManualResultListener() {
            @Override
            public void onResult(BaseResultBean resultBean) {
                if( resultBean.getResultCode() == 0){
                    mRandomTextView.addKeyWord(resultBean.getMsg());
                }
            }

            @Override
            public void onError(ErrorSLResponse response) {
                Log.w(TAG,"query " + response.getMsg());
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRandomTextView.addKeyWord("Client2");
                mRandomTextView.addKeyWord("Client3");
                mRandomTextView.addKeyWord("Client1");
                mRandomTextView.show();
            }
        },2000);

        mRandomTextView.setOnRippleViewClickListener(new RandomTextView.OnRippleViewClickListener() {
            @Override
            public void onRippleViewClicked(View view) {
                String key = ((RippleView)view).getText().toString();
                mRandomTextView.removeKeyWord(key);
                mRandomTextView.show();
            }
        });
    }
}
