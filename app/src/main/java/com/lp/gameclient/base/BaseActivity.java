package com.lp.gameclient.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LP on 2017/8/10/15:47.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected Activity mContext;
    private Unbinder mUnbinder;
    protected boolean isFirstShow = true;

    //获取布局
    protected abstract int getLayout();
    //获取顶部工具栏
    protected abstract View getToolBarView();
    //初始化数据
    protected abstract void initData();
    //初始化View
    protected abstract void initView(Bundle saveInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        if (isFirstShow) {
            isFirstShow = false;
            initData();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
