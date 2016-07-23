package com.shanlin.camera.cameraclient;

import android.app.Application;
import android.content.Context;

import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.net.TestGetCameraProxy;
import com.shanlin.camera.cameraclient.net.UserBzs;

/**
 * Created by feng on 7/20/16.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        context = this;
    }

    public void init(){
        //if user had login succ before
        //get remote device
        UserBzs.autoLogin();
//        if( UserBzs.isLogin()){
        DeviceManagerProxy.getInstance().setProxy(new TestGetCameraProxy());
        DeviceManagerProxy.getInstance().getCameras(null);
//        }

    }

    public static Context getContext(){
        return context;
    }
}
