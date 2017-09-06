package com.lp.gameclient.base;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LP on 2017/8/17/20:23.
 */

public abstract class BaseRepo {

    //对rst进行判断再做处理
    protected Observable transform(Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
