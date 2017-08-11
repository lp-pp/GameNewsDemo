package com.lp.gameclient.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by LP on 2017/8/11/12:06.
 */

public class ToastUtil {

    private static Toast mToast;
    private static Context mContext;

    public static void initToast(Context context){
        mContext = context;
    }

    /**
     * 自定义Toast
     * @param s
     */
    public static void show(String s){
        int duration = Toast.LENGTH_SHORT;
        if (s.length() > 10)
            duration = Toast.LENGTH_LONG;
        if (mToast == null)
            mToast = Toast.makeText(mContext, s, duration);
        else{
            mToast.setText(s);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    /**
     * 取消Toast
     */
    public static void cancleToast(){
        if (mToast != null)
            mToast.cancel();
    }
}
