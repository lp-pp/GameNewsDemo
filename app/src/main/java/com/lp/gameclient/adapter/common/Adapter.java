package com.lp.gameclient.adapter.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LP on 2017/9/1/15:05.
 */

public abstract class Adapter<T> extends BaseAdapter<T, AdapterHelper> {

    public Adapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
    }

    public Adapter(Context context, @NonNull List<T> data, @NonNull int... layoutResIds) {
        super(context, data, layoutResIds);
    }

    @Override
    public AdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent, int layoutResId) {
        return AdapterHelper.get(context, convertView, parent, layoutResId, position);
    }
}
