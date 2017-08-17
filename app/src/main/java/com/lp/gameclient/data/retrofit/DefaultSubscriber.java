package com.lp.gameclient.data.retrofit;

import rx.Subscriber;

/**
 * Created by LP on 2017/8/17/20:11.
 */

public abstract class DefaultSubscriber<T> extends Subscriber<T>{

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T entity) {
        _onNext(entity);
    }

    public abstract void _onNext(T entity);
}
