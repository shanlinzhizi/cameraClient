package com.shanlin.camera.cameraclient.entity;

/**
 * Created by APhil on 16/7/29.
 */
public class LiveHotInfo {

    private CameraDevice device;

    private int imgUrl;

    private int imgUserIconUrl;

    private String deviceName;

    private String deviceCompanyName;

    private String msgCount;

    private String likeCount;

    private String watchingCount;

    public CameraDevice getDevice() {
        return device;
    }

    public void setDevice(CameraDevice device) {
        this.device = device;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgUserIconUrl() {
        return imgUserIconUrl;
    }

    public void setImgUserIconUrl(int imgUserIconUrl) {
        this.imgUserIconUrl = imgUserIconUrl;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCompanyName() {
        return deviceCompanyName;
    }

    public void setDeviceCompanyName(String deviceCompanyName) {
        this.deviceCompanyName = deviceCompanyName;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getWatchingCount() {
        return watchingCount;
    }

    public void setWatchingCount(String watchingCount) {
        this.watchingCount = watchingCount;
    }



}
