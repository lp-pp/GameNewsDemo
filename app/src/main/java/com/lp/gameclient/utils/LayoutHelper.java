package com.lp.gameclient.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lp.gamenewsdemo.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by Lp on 2017/09/05/19:10.
 */

public class LayoutHelper {

    public static LinearLayoutManager getLinearLayout(Context context){
        return new LinearLayoutManager(context);
    }

    public static GridLayoutManager getGridLayout(Context context, int count){
        return new GridLayoutManager(context, count);
    }

    public static StaggeredGridLayoutManager getVerticalStagLayout(){
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    public static RecyclerView.ItemDecoration getHorizontalDivider_5(Context context) {
        return new HorizontalDividerItemDecoration.Builder(context)
                .color(Color.WHITE)
                .sizeResId(R.dimen.five).build();

    }

    public static RecyclerView.ItemDecoration getHorizontalDivider_6(Context context) {
        return new HorizontalDividerItemDecoration.Builder(context)
                .color(Color.parseColor("#f5f5f5"))
                .sizeResId(R.dimen.eight).build();

    }
}
