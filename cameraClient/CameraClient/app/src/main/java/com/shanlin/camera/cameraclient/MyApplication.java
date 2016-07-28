package com.shanlin.camera.cameraclient;

import android.app.Application;
import android.content.Context;

import com.shanlin.camera.cameraclient.net.DeviceManagerProxy;

import com.shanlin.camera.cameraclient.net.SqlDeviceManager;

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
        context = this;
        init();
    }

    private void init(){
        //if user had login succ before
        //get remote device
        UserBzs.autoLogin();
//        if( UserBzs.isLogin()){
            initDeviceSource();
//        }

        //device connection service
       initDeviceConnectionService();
    }

    private void initDeviceSource(){
//        DeviceManagerProxy.getInstance().setProxy(new TestGetCameraProxy());
        DeviceManagerProxy.getInstance().setProxy(new SqlDeviceManager(context));
        DeviceManagerProxy.getInstance().getCameras(null);
    }

    private void initDeviceConnectionService(){
        SLService.SLDeviceInfo deviceInfo = new SLService.SLDeviceInfo();
//        deviceInfo.sid = "dev6";
//        deviceInfo.passwd = "88888888";
//        SLService slService = SLService.getInstance();
//        slService.init(this,deviceInfo);
//        slService.start();

        SLService.SLDeviceInfo info = new SLService.SLDeviceInfo();
        info.sid = "client003";
        info.passwd = "client003";

        SLService service = SLService.getInstance();
        service.init(getContext(), info);

        service.start();

    }

    public static Context getContext(){
        return context;
    }


}
