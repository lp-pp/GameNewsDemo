package com.lp.gameclient.module.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.lp.gameclient.base.BaseActivity;
import com.lp.gameclient.utils.ActivityUtil;
import com.lp.gamenewsdemo.R;

import java.util.Arrays;
import java.util.List;

import static com.github.fafaldo.fabtoolbar.R.styleable.FABToolbarLayout;


/**
 * Created by LP on 2017/8/10/19:28.
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String QUIT_TOAST = "连按两次退出 游戏头条";

    RadioGroup mRdgToolbar;
    FABToolbarLayout mFabMenu;

    private int isFirstBack = 0;
    private static final int MODULE_NEWS = 0, MODULE_VIDEO = 1, MODULE_PIC = 2, MODULE_MINE = 3;

    private NewsFragment mNewsFragment;
    private VideoFragment mVideoFragment;
    private PictureFragment mPictureFragment;
    private MeFragment mMeFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragmentlist;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View getToolBarView() {
        return null;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        isFirstBack = 1;
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        initFragment(saveInstanceState);
        showSelectedTab();
        changeNavigation();

    }

    private void initFragment(Bundle saveInstanceState) {
        if (saveInstanceState == null) {
            createFragment();
            mTransaction = mFragmentManager.beginTransaction();
            for (Fragment f : mFragmentlist){
                mTransaction.add(R.id.llayout, f);
            }
            mTransaction.commit();
        } else {
            restoreFragment();
            hideAllFragment();
            mTransaction.show(mFragmentlist.get(MODULE_NEWS)).commit();
        }
    }

    private void showSelectedTab() {
        String strExtra = getIntent().getStringExtra(ActivityUtil.ARG_1);
        String mPostion = strExtra == null ? "" + MODULE_NEWS : strExtra;
        int tabPostion = Integer.parseInt(mPostion);
        showSelectFragment(tabPostion);
    }

    private void showSelectFragment(int tabPostion) {

    }

    private void changeNavigation() {

    }

    private void createFragment() {
        mNewsFragment = NewsFragment.newInstance();
        mVideoFragment = VideoFragment.newInstance();
        mPictureFragment = PictureFragment.newInstance();
        mMeFragment = MeFragment.newInstance();
        mFragmentlist = Arrays.asList(mNewsFragment, mVideoFragment, mPictureFragment, mMeFragment);
    }

    private void restoreFragment() {
        List<Fragment> fragments = mFragmentManager.getFragments();
        for (Fragment fragment : fragments){
            if (mNewsFragment instanceof fragment)
                mNewsFragment = (NewsFragment) fragment;
            else if (mVideoFragment instanceof fragment)
                mVideoFragment = (VideoFragment) fragment;
            else if (mPictureFragment instanceof fragment)
                mPictureFragment = (PictureFragment) fragment;
            else if (mMeFragment instanceof fragment)
                mMeFragment = (MeFragment) fragment;
        }
        mFragmentlist = Arrays.asList(mNewsFragment, mVideoFragment, mPictureFragment, mMeFragment);
    }

    private void hideAllFragment() {
        for (Fragment f: mFragmentlist) {
            mTransaction.hide(f);
        }
    }

    @Override
    protected void initData() {

    }

    public void onClick(View view){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
