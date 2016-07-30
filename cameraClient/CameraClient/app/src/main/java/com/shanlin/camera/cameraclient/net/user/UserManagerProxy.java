package com.shanlin.camera.cameraclient.net.user;

import android.os.Handler;
import android.os.Looper;

import com.android.volley.Request;
import com.shanlin.camera.cameraclient.MyApplication;
import com.shanlin.camera.cameraclient.entity.AppUser;
import com.shanlin.camera.cameraclient.entity.BaseResultBean;
import com.shanlin.camera.cameraclient.entity.BaseSLRequestBean;
import com.shanlin.camera.cameraclient.entity.ErrorSLResponse;
import com.shanlin.camera.cameraclient.entity.SearchResponse;
import com.sl.SLService;
import com.sl.SLUserManager;

/**
 * Created by DELL on 2016/7/30.
 */
public class UserManagerProxy {

    private static UserManagerProxy instance;

    private UserManagerProxy(){

    }

    public static UserManagerProxy getInstance(){
        if( instance == null){
            synchronized (UserManagerProxy.class){
                if( instance == null){
                    instance = new UserManagerProxy();
                }
            }
        }
        return instance;
    }

    public static void realease(){
        SLUserManager.release();
    }

    public  void delete(String sid, final OnUserManualResultListener listener){
        SLUserManager slUserManager = SLUserManager.getInstance();
        int code = slUserManager.buddyDel(sid, new SLUserManager.OnDelListener() {
            @Override
            public void onDel( int i) {
                final BaseResultBean result = new BaseResultBean();
                result.setResultCode(i);
                postResult(listener,result);
            }
        });
        if( code < 0){
            ErrorSLResponse error = new ErrorSLResponse();
            error.setResultCode(code);
            postError(listener,error);
        }
    }

    public  void add(String sid, long buid, final OnUserManualResultListener listener){
        SLUserManager slUserManager = SLUserManager.getInstance();
       int code =  slUserManager.buddyAdd(sid, buid, new SLUserManager.OnAddListener() {
            @Override
            public void onAdd(int result) {
                BaseResultBean resultBean = new BaseResultBean();
                resultBean.setResultCode(result);
                postResult(listener,resultBean);
            }
        });

        if( code < 0){
            ErrorSLResponse error = new ErrorSLResponse();
            error.setResultCode(code);
            postError(listener,error);
        }
    }

    public void query(int startnum, int linkType, String searchId, final OnUserManualResultListener listener){
        SLUserManager slUserManager = SLUserManager.getInstance();
        int code = slUserManager.buddySearch(startnum, linkType, searchId, new SLUserManager.OnSearchListener() {
            @Override
            public void onSearch(int i, String sid) {
                SearchResponse response = new SearchResponse();
                response.setResultCode(i);
                response.setMsg(sid);
                postResult(listener,response);
            }
        });

        if( code < 0){
            ErrorSLResponse error = new ErrorSLResponse();
            error.setResultCode(code);
            postError(listener,error);
        }
    }


    public void register(AppUser user, final OnUserManualResultListener listener){
        SLUserManager userManager = SLUserManager.getInstance();
        int code = userManager.regist(user.getUserName(),
                user.getPwd(),
                user.getEmail(),
                user.getMobile(),
                user.getNickName(),
                user.getAge(),
                user.getSex(),
                user.getRemark(),
                new SLUserManager.OnRegistListener() {
            @Override
            public void onRegist(int i) {
                BaseResultBean response = new BaseResultBean();
                response.setResultCode(i);
                postResult(listener,response);
            }
        });
        ErrorSLResponse error = new ErrorSLResponse();
        error.setResultCode(code);
        postError(listener,error);

    }


    /**
     * @Todo asscess the key for field for result by reflect
     * @param result
     * @param code
     * @param args
     * @param <T>
     * @return
     */
    private <T> T parseResutl(T result,int code,String... args){
        Class clazs = result.getClass();
        T instance = null;
        try {
            instance = (T) clazs.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void postError(final OnUserManualResultListener listener, final ErrorSLResponse response){
        if( listener == null){
            return;
        }
        if( Looper.myLooper() != Looper.getMainLooper()){
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    listener.onError(response);
                }
            });
        }else {
            listener.onError(response);
        }
    }

    private void postResult(final OnUserManualResultListener listener,final BaseResultBean response){
        if(Looper.myLooper() != Looper.getMainLooper()){
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    listener.onResult(response);
                }
            });
        }else{
            listener.onResult(response);
        }
    }
}
