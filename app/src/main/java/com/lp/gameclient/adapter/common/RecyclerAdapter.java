package com.lp.gameclient.adapter.common;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

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

    @Override
    public RecyclerAdatpterHelper getAdapterHelper(RecyclerView.ViewHolder viewHolder) {
        return RecyclerAdatpterHelper.get(viewHolder);
    }

    @Override
    public int dividerLeftMargin(int position, RecyclerView parent) {
        return 0;
    }

    @Override
    public int dividerRightMargin(int position, RecyclerView parent) {
        return 0;
    }

    @Override
    public Paint dividerPaint(int position, RecyclerView parent) {
        return null;
    }

    @Override
    public boolean shouldHideDivider(int position, RecyclerView parent) {
        return false;
    }
}
