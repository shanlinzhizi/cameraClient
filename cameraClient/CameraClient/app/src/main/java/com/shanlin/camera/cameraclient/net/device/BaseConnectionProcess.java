package com.shanlin.camera.cameraclient.net.device;

import android.os.Looper;
import android.util.Log;

import com.shanlin.camera.cameraclient.MyApplication;

import java.util.logging.Handler;

/**
 * Created by feng on 7/23/16.
 */
public class BaseConnectionProcess extends ConnectionProcess {
    private  static final String TAG = BaseConnectionProcess.class.getSimpleName();

    @Override
    public void prepare(int code) {
        Log.i(TAG,"prepare : " + code);
    }

    @Override
    public void onError(int code) {
        Log.i(TAG,"prepare : " + code);
    }

    @Override
    public void onSuccess(Object o) {
        Log.i(TAG,"prepare : " + o.toString());
    }
}
