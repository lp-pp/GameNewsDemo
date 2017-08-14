package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.view.View;

import com.lp.gameclient.base.BaseFragment;

/**
 * Created by LP on 2017/8/14/19:20.
 */

/**
 * 新闻列表
 */
public class NewsFragment extends BaseFragment {

    public static NewsFragment newInstance(){
        return new NewsFragment();
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {

    }

    @Override
    protected View getToolBarView() {
        return null;
    }
}
