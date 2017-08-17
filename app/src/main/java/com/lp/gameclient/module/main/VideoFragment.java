package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.view.View;

import com.lp.gameclient.adapter.common.RecyclerAdapter;
import com.lp.gameclient.base.BaseFragment;
import com.lp.gameclient.data.models.VideoEntity;
import com.lp.gameclient.data.repository.VideoRepo;
import com.lp.gameclient.utils.LayoutHelper;
import com.lp.gameclient.widgets.MultipleStatusView;
import com.lp.gameclient.widgets.mRecyclerView.XRecyclerView;
import com.lp.gamenewsdemo.R;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by LP on 2017/8/16/18:31.
 * 视频列表
 */

public class VideoFragment extends BaseFragment {

    @BindView(R.id.content_view)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.msv_layout)
    MultipleStatusView mMultipleStatusView;

    private RecyclerAdapter<VideoEntity> mAdatpter;
    private int mPage = 1;

    public static VideoFragment newInstance() {
        return new VideoFragment();
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*if (hidden)
            JCVideoPlayer.releaseAllVideos();*/
    }

    @Override
    protected void initData() {
        initVideoListData(mPage);
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        mXRecyclerView.setLayoutManager(LayoutHelper.getLinearLayout(mContext));
        mXRecyclerView.setLoadingListenr(this);

        setAdapter();
    }

    private void initVideoListData(int mPage) {
        VideoRepo.getIntance().getVideoList();
    }

    private void setAdapter() {

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPage = 1;
        initVideoListData(mPage);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        initVideoListData(++mPage);
    }
}
