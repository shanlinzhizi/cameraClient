package com.shanlin.camera.cameraclient.net.device;

import android.util.Log;

import com.shanlin.camera.cameraclient.util.StringUtil;

/**
 * Created by feng on 7/23/16.
 * for connect to device
 * manager the connection and show the process
 *
 */
public class DeviceRequest {

    private static String TAG = DeviceRequest.class.getSimpleName();

    private String sid;
    private String pwd;
    private int channel;
    private int connectMode;

    private String requestString;


    private ConnectionProcess connectionProcess;

    private DeviceRequestParse requestParse;
    private DeviceResponseParse responseParse;



    private DeviceRequest(String sid,String pwd){
        this.sid = sid;
        this.pwd = pwd;
    }

    public void run(){
        checkAndInitArgs();
        //real connect and send data here

    }

    private void checkAndInitArgs(){
        if(StringUtil.isEmptyString(sid)){
            throw new NullPointerException("deivce sid is null");
        }

        if( StringUtil.isEmptyString(pwd)){
            Log.w(TAG,"device pwd is empty");
        }

        if( StringUtil.isEmptyString(requestString)){
            Log.w(TAG,"device request is empty");
        }

        if( requestParse == null){
            requestParse = new BaseDeviceRequestParse();
        }
        if( responseParse == null){
            responseParse = new BaseDeviceResponseParse();
        }

        if( connectionProcess == null){
            connectionProcess = new BaseConnectionProcess();
        }
    }

    public static class RequestBuilder {


        private String sid;
        private String pwd;
        private int channel;
        private int connectMode;
        private String requestString;

        private DeviceRequestParse requestParse;
        private DeviceResponseParse responseParse;

        private ConnectionProcess connectionProcess;

        public RequestBuilder(String sid, String pwd){
            this.sid = sid;
            this.pwd = pwd;
        }

        public void setChannel(int channel){
            this.channel = channel;
        }

        public void setConnectMode(int connectMode){
            this.connectMode = connectMode;
        }

        public void setReqeust(String request){
            this.requestString = request;
        }

        public void setRequestParse(DeviceRequestParse requestParse){
            this.requestParse = requestParse;
        }

        public void setResponseParse(DeviceResponseParse responseParse){
            this.responseParse = responseParse;
        }

        private void setConnectionProcess(ConnectionProcess connectionProcess){
            this.connectionProcess = connectionProcess;
        }

        public DeviceRequest build(){
            DeviceRequest request = new DeviceRequest(sid,pwd);
            request.channel = channel;
            request.connectMode = connectMode;
            request.requestString = requestString;
            request.requestParse = requestParse;
            request.responseParse = responseParse;
            request.connectionProcess = connectionProcess;

            return request;
        }

    }


}
