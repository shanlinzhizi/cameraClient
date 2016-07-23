package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.entity.AppUser;

/**
 * Created by feng on 7/20/16.
 * The ApplicationContext used in here is better
 */
public class UserBzs {
    private static boolean isLogin = false;

    public static boolean isLogin(){
        return isLogin;
    }

    /**
     * login in the user-system
     * @param user
     * @return
     */
    public static boolean login(AppUser user){
        return false;
    }

    public static void autoLogin(){
        AppUser user = getAppUser();
        if( user == null){
            return;
        }
        login(user);
    }


    /**
     *
     * @return get user info from local
     */
    private static AppUser getAppUser(){
        return new AppUser();
    }

    /**
     * save user on phone after login success
     * @param user
     * @return
     */
    private static boolean saveAppUserOnPhone(AppUser user){

        return false;
    }

    /**
     * delete user info that save on phone after manual logout
     * @param user
     * @return
     */
    private static boolean deleteUserOnPhone(AppUser user){
        return false;
    }
}
