package com.lp.gameclient.data.models;

/**
 * Created by LP on 2017/8/17/14:02.
 */

public class BaseVideoEntity<T> {

    /**
     * returnCode : 200
     * returnMsg : Success
     * returnData : {}
     */

    public String returnCode;
    public String returnMsg;
    public T returnData;

}
