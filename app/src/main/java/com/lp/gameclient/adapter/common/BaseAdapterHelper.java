package com.lp.gameclient.adapter.common;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lp.gameclient.utils.GlideUtil;

/**
 * Created by LP on 2017/8/22/20:05.
 */

public abstract class BaseAdapterHelper<T>{
    protected SparseArray<View> views;

    public abstract View getItemView();

    private <V extends View> V retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null){
            view = getItemView().findViewById(viewId);
            views.put(viewId, view);
        }
    }

    public <V extends View> V getView(int viewId){
        return retrieveView(viewId);
    }

    public T setText(int viewId, CharSequence value){
        TextView view = retrieveView(viewId);
        view.setText(value);
        return (T) this;
    }

    public T setText(int viewId, @StringRes int stringRes){
        TextView view = retrieveView(viewId);
        view.setText(stringRes);
        return (T) this;
    }

    public T setEnable(int viewId, boolean isEnable){
        TextView view = retrieveView(viewId);
        view.setEnabled(isEnable);
        return (T) this;
    }

    public T setImageResource(int viewId, @DrawableRes int imageRes){
        ImageView view = retrieveView(viewId);
        GlideUtil.loadImage(imageRes, view);
        return (T) this;
    }

}
