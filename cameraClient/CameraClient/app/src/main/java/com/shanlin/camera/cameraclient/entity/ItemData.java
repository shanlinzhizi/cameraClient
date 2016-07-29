package com.shanlin.camera.cameraclient.entity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by APhil on 16/7/29.
 */
public class ItemData {

    private String name;
    private Class<? extends SupportFragment> target;
    private Integer imgResId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends SupportFragment> getTarget() {
        return target;
    }

    public void setTarget(Class<? extends SupportFragment> target) {
        this.target = target;
    }

    public Integer getImgResId() {
        return imgResId;
    }

    public void setImgResId(Integer imgResId) {
        this.imgResId = imgResId;
    }
}
