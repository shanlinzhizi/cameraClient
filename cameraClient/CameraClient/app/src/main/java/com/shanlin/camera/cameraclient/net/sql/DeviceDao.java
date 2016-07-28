package com.shanlin.camera.cameraclient.net.sql;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by APhil on 16/7/28.
 * @Todo need to op it
 */
public class DeviceDao  {

    private OrmOpenHelper helper;
    private Dao<CameraDevice,Integer> cameraDao;


    public DeviceDao(Context context){
        context = context.getApplicationContext();
        helper = OrmOpenHelper.getInstance(context);
        cameraDao = helper.getDao(CameraDevice.class);
    }

    public void close(){
        helper.close();
    }

    public List<CameraDevice> queryAll(){
        List<CameraDevice> cameras = null;
        try {
            cameras =  cameraDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras == null ? Collections.<CameraDevice>emptyList() : cameras;
    }

    public int createDevice(CameraDevice cameraDevice){
        List<CameraDevice> result;
        try {
            result = queryDeviceBySid(cameraDevice.getGid());
            if( !result.isEmpty()){
                return -1;
            }

            result = queryDeviceByName(cameraDevice.getNickName());
            if( !result.isEmpty()){
                return -1;
            }
            return cameraDao.create(cameraDevice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<CameraDevice> queryDeviceBySid(String sid){
        List<CameraDevice> cameras = null;
        try {
            cameras =  cameraDao.queryBuilder().where().eq(CameraDevice.COLUM_SID,sid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras == null ? Collections.<CameraDevice>emptyList() : cameras;
    }

    public List<CameraDevice> queryDeviceByName(String name){
        List<CameraDevice> cameras = null;
        try{
            cameras =  cameraDao.queryBuilder().where().eq(CameraDevice.COLUM_NICKNAME,name)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cameras == null ? Collections.<CameraDevice>emptyList() : cameras;
    }

    public long deleteDevice(CameraDevice device){
        try {
//            cameraDao.deleteBuilder().where().eq(CameraDevice.COLUM_SID,device.getGid());
            DeleteBuilder builder = cameraDao.deleteBuilder();
            builder.where().eq(CameraDevice.COLUM_SID,device.getGid());
            cameraDao.delete(builder.prepare());
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public int updateDevice(CameraDevice device){
        try {
            return cameraDao.update(device);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



    public int createOrUpdate(CameraDevice device){
        try {
            return cameraDao.createOrUpdate(device).getNumLinesChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
