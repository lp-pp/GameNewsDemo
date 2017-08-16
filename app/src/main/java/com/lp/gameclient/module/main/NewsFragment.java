package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lp.gameclient.base.BaseFragment;
import com.lp.gamenewsdemo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by LP on 2017/8/16/18:20.
 * 新闻列表
 */

public class NewsFragment extends BaseFragment {

    public static NewsFragment newInstance(){
        return new NewsFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.news_fragment;
    }

    @Override
    protected View getToolBarView() {
        return null;
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        ViewPager mVpInfoList = (ViewPager) view.findViewById(R.id.vp_infolist);
        SmartTabLayout mSmartTabLayout = (SmartTabLayout) view.findViewById(R.id.viewpagertab);

    }
}
