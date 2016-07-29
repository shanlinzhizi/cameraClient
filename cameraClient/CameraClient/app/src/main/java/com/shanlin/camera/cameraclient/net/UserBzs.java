package com.shanlin.camera.cameraclient.net;

import com.shanlin.camera.cameraclient.entity.AppUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 7/20/16.
 * The ApplicationContext used in here is better
 */
public class UserBzs {
    private static boolean isLogin = true;

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
    public static AppUser getAppUser(){
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

    /**
     * key:     img
     *          nickName
     *          pwd
     *          userName
     *          code
     * @param params
     * @return
     */
    public static AppUser registerUser(Map<String,String> params){

        AppUser user = new AppUser();
        user.setUserImg(params.get("img"));
        user.setNickName(params.get("nickName"));
        user.setPwd(params.get("pwd"));
        user.setUserName(params.get("userName"));
        String verifyCode = params.get("code");

        return user;
    }
}
