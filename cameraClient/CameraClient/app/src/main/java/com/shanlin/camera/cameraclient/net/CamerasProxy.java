package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.util.List;

/**
 * Created by feng on 7/20/16.
 */
public class CamerasProxy {

    private IGetCamera getCameraProxy;

    public void setProxy(IGetCamera proxy){
        this.getCameraProxy = proxy;
    }

    public List<CameraDevice> getCameras(){
        if( getCameraProxy == null){
            throw new IllegalStateException("you must have to initial the proxy first");
        }
        return getCameraProxy.getCameras();
    }



}
