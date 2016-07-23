package com.shanlin.camera.cameraclient.net.device;

/**
 * Created by feng on 7/23/16.
 */
public abstract class ConnectionProcess<T> {

    public abstract void prepare(int code);

    public abstract void onError(int code);

    public abstract void onSuccess(T t);

}
