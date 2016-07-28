package com.shanlin.camera.cameraclient.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.shanlin.camera.cameraclient.R;

/**
 * Created by APhil on 16/7/13.
 */
@DatabaseTable(tableName = CameraDevice.table_name)
public class CameraDevice implements Parcelable{

    public static final String table_name = "tb_device";
    public static final String COLUM_NICKNAME = "nickname";
    public static final String COLUM_SID = "sid";
    public static final String COLUM_AS_USERNAME = "devce_username";
    public static final String COLUM_AS_PWD  = "device_pwd";
    public static final String COLUM_IMG = "img";
    public static final String COLUM_DESC = "desc";
    public static final String COLUM_DEVICE_TYPE = "type";


    @DatabaseField(columnName = COLUM_NICKNAME)
    private String nickName;

    @DatabaseField(columnName = COLUM_SID, id = true)
    private String gid;

    @DatabaseField(columnName = COLUM_AS_USERNAME)
    private String accessName;

    @DatabaseField(columnName = COLUM_AS_PWD)
    private String accessPwd;

    @DatabaseField(columnName = COLUM_IMG)
    private int img;

    @DatabaseField(columnName = COLUM_DESC)
    private String desc;

    @DatabaseField(columnName = COLUM_DEVICE_TYPE)
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
        return (img == 0 ? R.drawable.img_1 : img);
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
