package com.lp.gameclient.data.models;

import io.realm.RealmObject;

/**
 * Created by LP on 2017/8/17/12:07.
 */

public class ImageEntity extends RealmObject{

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
