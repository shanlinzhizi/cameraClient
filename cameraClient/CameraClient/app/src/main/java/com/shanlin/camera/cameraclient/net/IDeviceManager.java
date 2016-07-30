package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.util.List;

/**
 * Created by feng on 7/20/16.
 */
public interface IDeviceManager {
    void getCameras(IResponse response);

    void update(CameraDevice device, IResponse response);

    void delete(CameraDevice device, IResponse response);

    void add(CameraDevice device, IResponse response);

    void refresh(IResponse response);

    void close();

}
