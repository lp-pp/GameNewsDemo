package com.lp.gameclient.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lp.gameclient.widgets.mRecyclerView.XRecyclerView;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lp on 2017/8/14/19:17.
 */

public abstract class BaseFragment extends RxFragment implements XRecyclerView.LoadingListener{
    private static final String TAG = BaseFragment.class.getSimpleName();

    protected Activity mContext;
    private Unbinder mUnbinder;
    protected boolean isFirstShow = true;
    protected boolean isRefresh = false;

    //获取布局Fragment文件
    protected abstract int getLayout();
    //获取顶部工具栏
    protected abstract View getToolBarView();
    //初始化数据
    protected abstract void initData();
    //初始化Fragment布局
    protected abstract void initView(View view, Bundle saveInstanceState);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this);
        initView(view, savedInstanceState);
        initData();
        return view;
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
    }
}
