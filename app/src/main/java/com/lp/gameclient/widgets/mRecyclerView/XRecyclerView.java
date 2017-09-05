package com.lp.gameclient.widgets.mRecyclerView;

import android.support.v7.widget.RecyclerView;

/**
 * Created by LP on 2017/8/3.
 */

public class XRecyclerView extends RecyclerView {

    private LoadingListener mLoadingListener;

    public void setLoadingListenr(LoadingListener lls){
        this.mLoadingListener = lls;
    }

    public interface LoadingListener{
        void onRefresh();

        void onLoadMore();
    }

    public void loadMoreComplete() {

    }

    public void refreshComplete() {

    }
}
