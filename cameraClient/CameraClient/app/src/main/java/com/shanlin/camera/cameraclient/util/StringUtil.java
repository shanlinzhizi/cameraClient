package com.shanlin.camera.cameraclient.util;

/**
 * Created by feng on 7/23/16.
 */
public class StringUtil {

    public static boolean isEmptyString(String s){
        boolean result = false;
        if( s == null){
            result = true;
        }

        if( s.trim().length() < 1){
            result = true;
        }
        return result;
    }
}
