package com.shanlin.camera.cameraclient.net.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.entity.CameraDevice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by APhil on 16/7/28.
 */
public class OrmOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "device_db";

    private OrmOpenHelper(Context context){
        super(context,DB_NAME,null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, CameraDevice.class);
            TableUtils.createTable(connectionSource, AppUser.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,CameraDevice.class,true);
            TableUtils.dropTable(connectionSource, AppUser.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static OrmOpenHelper instance;
    private Map<String,Dao> daos = new HashMap<>();

    /**
     *
     * @param context  applicationContext
     * @return
     */
    public static OrmOpenHelper getInstance(Context context){
        context = context.getApplicationContext();
        if( instance == null){
            synchronized (OrmOpenHelper.class){
                if( instance == null){
                    instance = new OrmOpenHelper(context);
                }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz){
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            try {
                dao = super.getDao(clazz);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            daos.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for( String key : daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
