package com.shanlin.camera.cameraclient.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by APhil on 16/7/13.
 */
public class CameraDevice implements Parcelable{


    private String nickName;

    private String gid;

    private String accessName;


    private String accessPwd;

    private int img;

    private String desc;

    private int deviceType ;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getAccessPwd() {
        return accessPwd;
    }

    public void setAccessPwd(String accessPwd) {
        this.accessPwd = accessPwd;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public CameraDevice(){

    }

    protected CameraDevice(Parcel in) {
        nickName = in.readString();
        gid = in.readString();
        accessName = in.readString();
        accessPwd = in.readString();
        img = in.readInt();
        desc = in.readString();
        deviceType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nickName);
        dest.writeString(gid);
        dest.writeString(accessName);
        dest.writeString(accessPwd);
        dest.writeInt(img);
        dest.writeString(desc);
        dest.writeInt(deviceType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CameraDevice> CREATOR = new Creator<CameraDevice>() {
        @Override
        public CameraDevice createFromParcel(Parcel in) {
            return new CameraDevice(in);
        }

        @Override
        public CameraDevice[] newArray(int size) {
            return new CameraDevice[size];
        }
    };

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }
}
