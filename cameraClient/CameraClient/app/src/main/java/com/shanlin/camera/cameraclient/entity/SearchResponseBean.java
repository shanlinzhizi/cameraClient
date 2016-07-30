package com.shanlin.camera.cameraclient.entity;

/**
 * Created by DELL on 2016/7/30.
 */
public class SearchResponseBean {

    private String sid;

    private long uid;

    private int state;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
