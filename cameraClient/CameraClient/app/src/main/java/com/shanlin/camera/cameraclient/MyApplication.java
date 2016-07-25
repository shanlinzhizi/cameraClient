package com.shanlin.camera.cameraclient;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;
import com.shanlin.camera.cameraclient.net.TestGetCameraProxy;
import com.shanlin.camera.cameraclient.net.UserBzs;
import com.sl.SLService;

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

    private void init(){
        //if user had login succ before
        //get remote device
        UserBzs.autoLogin();
//        if( UserBzs.isLogin()){
        DeviceManagerProxy.getInstance().setProxy(new TestGetCameraProxy());
        DeviceManagerProxy.getInstance().getCameras(null);
//        }

        //device connection service
       initDeviceConnectionService();
    }

    private void initDeviceConnectionService(){
        SLService.SLDeviceInfo deviceInfo = new SLService.SLDeviceInfo();
        deviceInfo.sid = "client002";
        deviceInfo.passwd = "client002";
        SLService slService = SLService.getInstance();
        slService.init(this,deviceInfo);
        slService.start();
    }

    public static Context getContext(){
        return context;
    }


}
