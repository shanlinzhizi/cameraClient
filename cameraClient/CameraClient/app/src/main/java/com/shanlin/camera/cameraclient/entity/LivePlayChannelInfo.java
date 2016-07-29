package com.shanlin.camera.cameraclient.entity;

/**
 * Created by APhil on 16/7/29.
 */
public class LivePlayChannelInfo {

    private int img;

    private String title;


    private CameraDevice device;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CameraDevice getDevice() {
        return device;
    }

    public void setDevice(CameraDevice device) {
        this.device = device;
    }



}
