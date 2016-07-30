package com.shanlin.camera.cameraclient.net.sql;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.shanlin.camera.cameraclient.entity.AppUser;

import java.sql.SQLException;

/**
 * Created by DELL on 2016/7/30.
 */
public class AppUserDao {

    private Dao<AppUser,Integer> dao;
    private OrmOpenHelper helper;

    public AppUserDao(Context context){
        context = context.getApplicationContext();
        helper = OrmOpenHelper.getInstance(context);
        dao = helper.getDao(AppUser.class);
    }

    public void addUser(AppUser user){
        try {
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateUser(AppUser user){
        try {
            dao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(AppUser user){
        try {
            DeleteBuilder delete = dao.deleteBuilder();
            delete.where().eq(AppUser.COlUM_USERNAME,user.getUserName());
            dao.delete(delete.prepare());

            delete.where().eq(AppUser.COLUM_SID,user.getSid());
            dao.delete(delete.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
