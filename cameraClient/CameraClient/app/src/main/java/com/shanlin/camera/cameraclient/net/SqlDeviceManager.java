package com.shanlin.camera.cameraclient.net;

import android.content.Context;

import com.shanlin.camera.cameraclient.entity.CameraDevice;
import com.shanlin.camera.cameraclient.net.sql.DeviceDao;

import java.util.List;

/**
 * Created by APhil on 16/7/21.
 */
public class SqlDeviceManager implements IDeviceManager {

    private DeviceDao deviceDao;

    public SqlDeviceManager(Context context){
        deviceDao = new DeviceDao(context);
    }

    @Override
    public void getCameras(IResponse response) {
        List<CameraDevice> cameras = deviceDao.queryAll();
        if( response != null){
            response.onResponse(cameras);
        }
    }

    @Override
    public void update(CameraDevice device, IResponse response) {
       int result = deviceDao.updateDevice(device);
        if( response != null) {
            response.onResponse(result);
        }
    }

    @Override
    public void delete(CameraDevice device, IResponse response) {
        long result = deviceDao.deleteDevice(device);
        if( response != null) {
            response.onResponse(result);
        }
    }

    @Override
    public void add(CameraDevice device, IResponse response) {
        int result = deviceDao.createDevice(device);
        if( response != null) {
            response.onResponse(result);
        }
    }

    @Override
    public void refresh(IResponse response) {
        List<CameraDevice> devices = deviceDao.queryAll();
        if( response != null) {
            response.onResponse(devices);
        }
    }

    @Override
    public void close() {
        deviceDao.close();
    }
}
