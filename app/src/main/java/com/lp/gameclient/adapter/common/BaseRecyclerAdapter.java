package com.lp.gameclient.adapter.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LP on 2017/8/31/19:24.
 */

public abstract class BaseRecyclerAdapter<T, H extends RecyclerAdatpterHelper>
        extends RecyclerView.Adapter<ViewHolder> implements DataIO<T>{

    protected final Context context;
    protected final int[] layoutResIds;
    protected final LayoutInflater layoutInflater;
    protected final ArrayList<T> data;

    public BaseRecyclerAdapter(Context context, int... layoutResIds){
        this(context, null, layoutResIds);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, int... layoutResIds){
        this.context = context;
        this.layoutResIds = layoutResIds;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data == null ? new ArrayList<T>() : new ArrayList(data);
    }

    @Override
    public int getItemViewType(int position) {
        if (getViewTypeCount() == 1)
            return super.getItemViewType(position);
        throw new RuntimeException("Required method getItemViewType was not overridden");
    }

    public int getViewTypeCount() {
        return layoutResIds.length;
    }

    public int getLayoutresId(int viewType){
        throw new RuntimeException("Required method getLayoutresId was not overridden");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResId;
        if (getViewTypeCount() > 1)
            layoutResId = getLayoutresId(getItemViewType(viewType));
        else
            layoutResId = layoutResIds[0];
        return new ViewHolder(layoutInflater.inflate(layoutResId, parent, false)){};
    }


}
