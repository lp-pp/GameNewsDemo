package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lp.gameclient.base.BaseFragment;
import com.lp.gameclient.module.news.NewsListFragment;
import com.lp.gameclient.utils.C;
import com.lp.gamenewsdemo.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Created by LP on 2017/8/17/11:06.
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

        //设置fragment的绑定参数
        Bundle RECOMMEND_Bundle = new Bundle();
        RECOMMEND_Bundle.putInt(C.CATE_ID, 0);

        Bundle HOT_Bundle = new Bundle();
        HOT_Bundle.putInt(C.CATE_ID, 1);

        Bundle NEWGAME_Bundle = new Bundle();
        NEWGAME_Bundle.putInt(C.CATE_ID, 2);

        Bundle ERCIYUAN_Bundle = new Bundle();
        ERCIYUAN_Bundle.putInt(C.CATE_ID, 3);

        Bundle PRODUCT_Bundle = new Bundle();
        PRODUCT_Bundle.putInt(C.CATE_ID, 4);

        Bundle GAME_Bundle = new Bundle();
        GAME_Bundle.putInt(C.CATE_ID, 5);

        Bundle EVALUATING_Bundle = new Bundle();
        EVALUATING_Bundle.putInt(C.CATE_ID, 6);

        //将ViewPaper与Tablayout绑定
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(getActivity())
                .add(R.string.recommend, NewsListFragment.class, RECOMMEND_Bundle)
                .add(R.string.hot,NewsListFragment.class, HOT_Bundle)
                .add(R.string.newgame, NewsListFragment.class, NEWGAME_Bundle)
                .add(R.string.erciyuan, NewsListFragment.class, ERCIYUAN_Bundle)
                .add(R.string.product, NewsListFragment.class, PRODUCT_Bundle)
                .add(R.string.game, NewsListFragment.class, GAME_Bundle)
                .add(R.string.evaluating, NewsListFragment.class, EVALUATING_Bundle)
                .create());
        mVpInfoList.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mVpInfoList);
    }
}
