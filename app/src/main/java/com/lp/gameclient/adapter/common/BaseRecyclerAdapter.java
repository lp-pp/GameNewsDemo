package com.lp.gameclient.adapter.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LP on 2017/8/21/19:57.
 */

public abstract class BaseRecyclerAdapter<T, H extends RecyclerAdatpterHelper>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DataIO<T>{

    protected final Context context;
    protected final int[] layoutResIds;
    protected final LayoutInflater layoutInflater;
    protected final ArrayList<T> data;

    public BaseRecyclerAdapter(Context context, int... layoutResIds){
        super(context, layoutResIds);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, int... layoutResIds){
        this.context = context;
        this.layoutInflater = layoutResIds;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data == null ? new ArrayList<T>() : new ArrayList(data);
    }

}
