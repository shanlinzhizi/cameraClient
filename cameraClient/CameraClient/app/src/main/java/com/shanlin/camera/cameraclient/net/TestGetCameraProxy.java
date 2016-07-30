package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by feng on 7/20/16.
 */
public class TestGetCameraProxy implements IDeviceManager {

    private int[] mImgRes = new int[]{
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_1, R.drawable.img_2
    };

    private List<CameraDevice> devices = new ArrayList<>();

    @Override
    public void getCameras(IResponse response) {
        generateDevice();
        if( response != null) {
            response.onResponse(devices);
        }
    }

    @Override
    public void update(CameraDevice device, IResponse response) {

    }

    @Override
    public void delete(CameraDevice device, IResponse response) {
        if( devices.contains(device)){
            devices.remove(device);
        }
    }

    @Override
    public void add(CameraDevice device, IResponse response) {
        if(devices == null){
            devices = new ArrayList<>();
        }
        devices.add(device);
    }


    @Override
    public void refresh(IResponse response) {

        if( response!= null){
            Collections.shuffle(devices);
            response.onResponse(devices);
        }
    }

    @Override
    public void close() {
        if( devices != null){
            devices.clear();;
            devices = null;
        }
    }

    private void generateDevice(){
        CameraDevice camera;
        for(int i = 0; i < 20; i ++){
            camera = new CameraDevice();
            camera.setAccessName("admin");
            camera.setAccessPwd("admin-xxxx");
            camera.setDesc("device_" + i + " test string");
            camera.setNickName("name_" + i + "nick name");
            camera.setGid("0000" + new Random().nextInt(10)  * 1);
//            camera.setDeviceType(i % 2);
            camera.setImg(mImgRes[i % 5]);
            devices.add(camera);
        }
    }
}
