package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.lp.gameclient.base.BaseActivity;
import com.lp.gameclient.data.repository.VideoRepo;
import com.lp.gameclient.utils.ActivityUtil;
import com.lp.gameclient.utils.ToastUtil;
import com.lp.gamenewsdemo.R;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by LP on 2017/8/11/12:11.
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String QUIT_TOAST = "连按两次退出游戏头条";

    @BindView(R.id.fabtoolbar_toolbar)
    RadioGroup mRdgToolbar;
    @BindView(R.id.fabtoolbar)
    FABToolbarLayout mFabMenu;

    private int isFirstBack = 0;
    private static final int MODULE_NEWS = 0, MODULE_VIDEO = 1, MODULE_PIC = 2, MODULE_MINE = 3;

    private NewsFragment mNewsFragment;
    private VideoFragment mVideoFragment;
    private PictureFragment mPictureFragment;
    private MeFragment mMeFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragmentlists;

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
            for (Fragment f : mFragmentlists){
                mTransaction.add(R.id.llayout, f);
            }
            mTransaction.commit();
        } else {
            restoreFragment();
            hideAllFragment();
            mTransaction.show(mFragmentlists.get(MODULE_NEWS)).commit();
        }
    }

    private void showSelectedTab() {
        String strExtra = getIntent().getStringExtra(ActivityUtil.ARG_1);
        String mPostion = strExtra == null ? "" + MODULE_NEWS : strExtra;
        int tabPostion = Integer.parseInt(mPostion);
        showSelectFragment(tabPostion);
    }

    private void changeNavigation() {
        mRdgToolbar.setOnCheckedChangeListener((group, checkedId) -> {
            mTransaction = mFragmentManager.beginTransaction();
            int postion = 0;
            switch (checkedId){
                case R.id.rdb_news:
                    postion = MODULE_NEWS;
                    break;
                case R.id.rdb_video:
                    postion = MODULE_VIDEO;
                    break;
                case R.id.rdb_pic:
                    postion = MODULE_PIC;
                    break;
                case R.id.rdb_me:
                    postion = MODULE_MINE;
                    default:
                        break;
            }
            isFirstBack = 1;
            mFabMenu.hide();
            hideAllFragment();
            mTransaction.show(mFragmentlists.get(postion)).commit();
        });
    }

    private void createFragment() {
        mNewsFragment = NewsFragment.newInstance();
        mVideoFragment = VideoFragment.newInstance();
        mPictureFragment = PictureFragment.newInstance();
        mMeFragment = MeFragment.newInstance();
        mFragmentlists = Arrays.asList(mNewsFragment, mVideoFragment, mPictureFragment, mMeFragment);
    }

    private void restoreFragment() {
        List<Fragment> fragments = mFragmentManager.getFragments();
        for (Fragment fragment : fragments){
            if (fragment instanceof NewsFragment){
                mNewsFragment = (NewsFragment) fragment;
            else if (fragment instanceof VideoFragment)
                mVideoFragment = (VideoFragment) fragment;
            else if (fragment instanceof PictureFragment)
                mPictureFragment = (PictureFragment) fragment;
            else if (fragment instanceof MeFragment)
                mMeFragment = (MeFragment) fragment;
        }
        mFragmentlists = Arrays.asList(mNewsFragment, mVideoFragment, mPictureFragment, mMeFragment);
    }

    private void showSelectFragment(int tabPostion) {
        hideAllFragment();
        mTransaction = mFragmentManager.beginTransaction();
        if (tabPostion == MODULE_NEWS) mRdgToolbar.check(R.id.rdb_news);
        else if (tabPostion == MODULE_VIDEO) mRdgToolbar.check(R.id.rdb_video);
        else if (tabPostion == MODULE_PIC) mRdgToolbar.check(R.id.rdb_pic);
        else if (tabPostion == MODULE_MINE) mRdgToolbar.check(R.id.rdb_me);
        mTransaction.show(mFragmentlists.get(tabPostion)).commit();
    }

    private void hideAllFragment() {
        for (Fragment f: mFragmentlists) {
            mTransaction.hide(f);
        }
    }

    @Override
    protected void initData() {
        VideoRepo.getIntance().getUserToken(mContext);
    }

    public void onClick(View view){
        mFabMenu.show();
        isFirstBack = 0;
    }

    /**
     * 双击退出程序
     */
    @Override
    public void onBackPressed() {
        if (mFabMenu.isToolbar()){
            mFabMenu.hide();
            isFirstBack = 1;
        } else if (isFirstBack == 1){
            ToastUtil.show(QUIT_TOAST);
            isFirstBack = 3;
            //开启异步线程，当用户超过2s没有再次点击返回键，则取消退出
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                   isFirstBack = 1; //取消退出
                }
            }, 1000);
        } else if (isFirstBack == 3){ //当用户连续点击2次时，退出程序
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
    }

}
