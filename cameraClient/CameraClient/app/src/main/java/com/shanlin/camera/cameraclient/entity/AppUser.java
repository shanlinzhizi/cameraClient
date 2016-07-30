package com.shanlin.camera.cameraclient.entity;

import android.text.TextUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.shanlin.camera.cameraclient.R;

/**
 * Created by APhil on 16/7/21.
 */

@DatabaseTable(tableName = AppUser.TABLE_NAME)
public class AppUser {
    public static final String TABLE_NAME = "tb_user";
    public static final String COlUM_USERNAME = "username";
    public static final String COLUM_NICKNAME = "nickname";
    public static final String COLUM_PWD = "pwd";
    public static final String COLUM_IMG = "img";
    public static final String COLUM_EMAIL = "email";
    public static final String COLUM_AGE = "age";
    public static final String COLUM_SEX = "sex";
    public static final String COLUM_MOBILE = "mobile";
    public static final String COLUM_REMARK = "remark";
    public static final String COLUM_SID = "sid";



    @DatabaseField(columnName = COLUM_SID, id = true)
    private String sid ="";

    @DatabaseField(columnName = COlUM_USERNAME)
    private String userName;

    @DatabaseField(columnName = COLUM_PWD)
    private String pwd;

    @DatabaseField(columnName = COLUM_NICKNAME)
    private String nickName;

    @DatabaseField(columnName = COLUM_IMG)
    private String userImg;

      /*uname, passwd, email, mobile, nick, age, sex, remark*/

    @DatabaseField(columnName = COLUM_EMAIL )
    private String email;

    @DatabaseField(columnName = COLUM_MOBILE)
    private String mobile;

    @DatabaseField(columnName = COLUM_AGE)
    private int age;

    @DatabaseField(columnName = COLUM_SEX)
    private int sex;

    @DatabaseField(columnName = COLUM_REMARK)
    private String remark;

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

    public int getUserImg() {
        int id = 0;
        if(!TextUtils.isEmpty(userImg)) {
            id = Integer.valueOf(userImg);
        }
        return id < 1 ? R.drawable.ic_avatar : id;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSid() {
        return userName;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
