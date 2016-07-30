package com.shanlin.camera.cameraclient.entity;

/**
 * Created by DELL on 2016/7/30.
 */
public class ErrorSLResponse extends BaseResultBean{

    @Override
    public String getMsg() {
        return parseErrorMsg(getResultCode());
    }

    private String parseErrorMsg(int errorCode){
        String msg = "";
        if( errorCode < 0){
           msg =  "send error by connection";
        }
        return msg;
    }
}
