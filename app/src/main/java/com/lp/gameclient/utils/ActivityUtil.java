package com.lp.gameclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by LP on 2017/8/10/20:20.
 */

public class ActivityUtil {

    public static final String ARG_1 = "arg1";

    public static void start(Context context, Class<? extends Activity> clazz, String arg1){
        Intent intent = new Intent(context, clazz);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_1, arg1);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
