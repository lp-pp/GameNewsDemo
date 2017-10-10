package com.lp.gameclient;

import android.app.Application;
import android.content.Context;

import com.lp.gameclient.utils.PPLog;
import com.lp.gameclient.utils.ToastUtil;
import com.lp.gamenewsdemo.BuildConfig;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by LP on 2017/10/10/16:12.
 */

public class MyGameApp extends Application {
    private final static String TAG = MyGameApp.class.getSimpleName();

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ToastUtil.initToast(mContext);

        //配置Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(mContext).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        //Logger
        Logger.init(TAG);//隐藏线程信息
        //对意外闪退做处理
        if (!BuildConfig.DEBUG)
            Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
    }

    //手动处理异常信息
    class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            PPLog.e(e.toString());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());

        }
    }

    //全局获取ApplicationContext
    public static Context getAppContext() {
        return mContext;
    }
}
