package com.shanlin.camera.cameraclient.net;

/**
 * Created by APhil on 16/7/21.
 */
public interface IErrorResponse<T> {

    void onErrorResponse(T t);
}
