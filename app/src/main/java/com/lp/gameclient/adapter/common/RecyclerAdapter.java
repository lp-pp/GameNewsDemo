package com.lp.gameclient.adapter.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * Created by LP on 2017/8/17/19:13.
 */

public abstract class RecyclerAdapter<T> extends BaseRecyclerAdapter<T, RecyclerAdatpterHelper>
        implements FlexibleDividerDecoration.PaintProvider,
        //FlexibleDividerDecoration.SizeProvider,
        //FlexibleDividerDecoration.ColorProvider,
        FlexibleDividerDecoration.VisibilityProvider,
        HorizontalDividerItemDecoration.MarginProvider {

    public RecyclerAdapter(Context context, @NonNull int... layoutResIds){
        super(context, layoutResIds);
    }

    public RecyclerAdapter(Context context, @NonNull List<T>data, @NonNull int... layoutResIds){
        super(context, data, layoutResIds);
    }


}
