package com.lp.gameclient.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Lp on 2017/8/17/18:53.
 */

public class LayoutHelper {

    public static LinearLayoutManager getLinearLayout(Context context){
        return new LinearLayoutManager(context);
    }
}
