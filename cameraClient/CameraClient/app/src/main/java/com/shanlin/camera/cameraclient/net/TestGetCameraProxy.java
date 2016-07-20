package com.shanlin.camera.cameraclient.net;

import android.util.SparseArray;

import com.shanlin.camera.cameraclient.R;
import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by feng on 7/20/16.
 */
public class TestGetCameraProxy implements IGetCamera{

    private int[] mImgRes = new int[]{
            R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_1, R.drawable.img_2
    };

    @Override
    public List<CameraDevice> getCameras() {
        List<CameraDevice> cameras = new ArrayList<>();
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
            cameras.add(camera);
        }
        return cameras;
    }
}
