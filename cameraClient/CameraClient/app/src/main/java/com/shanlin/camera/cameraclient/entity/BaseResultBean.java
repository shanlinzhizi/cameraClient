package com.shanlin.camera.cameraclient.entity;

/**
 * Created by DELL on 2016/7/30.
 */
public class BaseResultBean<T> {

    private int resultCode;

    private String msg = "";

    private T target;

    public String getMsg() {
        return parseMsg(resultCode);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    private String parseMsg(int code){
        String msg = "failed";
        if( code == 0){
            msg = "suc";
        }else if( code < 0){
            msg = "failed";
        }else{
            msg = this.msg;
        }

        return msg;
    }


    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }
}
