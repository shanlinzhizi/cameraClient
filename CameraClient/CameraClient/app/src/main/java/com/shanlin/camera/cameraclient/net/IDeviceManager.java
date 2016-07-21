package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.util.List;

/**
 * Created by feng on 7/20/16.
 */
public interface IDeviceManager {
    void getCameras(IResponse response);

    void update(List<CameraDevice> devices, IResponse response);

    void delete(List<CameraDevice> devices, IResponse response);

    void add(List<CameraDevice> devices, IResponse response);

    void refresh(IResponse response);

}
