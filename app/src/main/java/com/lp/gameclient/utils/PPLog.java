package com.lp.gameclient.utils;

import com.lp.gamenewsdemo.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by LP on 2017/9/6/11:16.
 * 日志打印工具
 */

public class PPLog {
    public final static String TAG = "LP";

    private PPLog() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 是否需要打印bug，在App的debug版本里会打印日志，而在release则不会，使用Gradle自动管理
    public final static boolean isDebug = BuildConfig.DEBUG;

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Logger.i(msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Logger.e(msg);
    }

    public static void json(String tag, String jsonStr) {
        if (isDebug)
            Logger.json(tag, jsonStr);
    }
}
