package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.view.View;

import com.lp.gameclient.base.BaseFragment;
import com.lp.gamenewsdemo.R;

/**
 * Created by LP on 2017/8/16/18:20.
 * 个人页面
 */

public class MeFragment extends BaseFragment {

    public static MeFragment newInstance(){
        return new MeFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_msv_recycler;
    }

    @Override
    protected View getToolBarView() {
        return null;
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {

    }
}
