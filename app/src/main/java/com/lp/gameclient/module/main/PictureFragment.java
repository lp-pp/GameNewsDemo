package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.view.View;

import com.lp.gameclient.base.BaseFragment;
import com.lp.gamenewsdemo.R;

/**
 * Created by LP on 2017/8/16/18:20.
 * 图片列表
 */

public class PictureFragment extends BaseFragment {

    public static PictureFragment newInstance(){
        return new PictureFragment();
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
