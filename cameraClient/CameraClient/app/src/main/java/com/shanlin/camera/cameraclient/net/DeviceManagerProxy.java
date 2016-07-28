package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.util.List;

/**
 * Created by feng on 7/20/16.
 * manager the device all here
 * Single proxy in app from net or sql
 *
 */
public class DeviceManagerProxy implements IDeviceManager {

    private IDeviceManager getCameraProxy;
    private static DeviceManagerProxy proxy;

    private DeviceManagerProxy(){

    }

    public void setProxy(IDeviceManager proxy){
        this.getCameraProxy = proxy;
    }

    public void getCameras(IResponse response){
        if( getCameraProxy == null){
            throw new IllegalStateException("you must have to initial the proxy first");
        }
        getCameraProxy.getCameras(response);
    }

    @Override
    public void update(List<CameraDevice> devices,IResponse response) {
        getCameraProxy.update(devices,response);
    }

    @Override
    public void delete(List<CameraDevice> devices,IResponse response) {
        getCameraProxy.delete(devices,response);
    }

    @Override
    public void add(List<CameraDevice> devices,IResponse response) {
        getCameraProxy.add(devices,response);
    }

    @Override
    public void refresh(IResponse response) {
        getCameraProxy.refresh(response);
    }


    public static DeviceManagerProxy getInstance(){
        if( proxy == null){
            synchronized (DeviceManagerProxy.class){
                if( proxy == null){
                    proxy = new DeviceManagerProxy();
                }
            }
        }
        return proxy;
    }

}
