package com.shanlin.camera.cameraclient.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by APhil on 16/7/21.
 */

@DatabaseTable(tableName = AppUser.TABLE_NAME)
public class AppUser {
    public static final String TABLE_NAME = "tb__user";
    public static final String COlUM_USERNAME = "username";
    public static final String COLUM_NICKNAME = "nickname";
    public static final String COLUM_PWD = "pwd";
    public static final String COLUM_IMG = "img";

    @DatabaseField(columnName = COlUM_USERNAME)
    private String userName;

    @DatabaseField(columnName = COLUM_PWD)
    private String pwd;

    @DatabaseField(columnName = COLUM_NICKNAME)
    private String nickName;

    @DatabaseField(columnName = COLUM_IMG)
    private String userImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

}
