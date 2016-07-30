package com.shanlin.camera.cameraclient.net.user;

import com.shanlin.camera.cameraclient.entity.BaseResultBean;
import com.shanlin.camera.cameraclient.entity.ErrorSLResponse;

/**
 * Created by DELL on 2016/7/30.
 */
public interface OnUserManualResultListener<T extends BaseResultBean> {
    void onResult(T t);

    <F extends ErrorSLResponse> void onError(F f);
}
