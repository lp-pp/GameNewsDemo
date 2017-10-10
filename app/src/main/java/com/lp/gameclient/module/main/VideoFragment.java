package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.view.View;

import com.lp.gameclient.adapter.common.RecyclerAdapter;
import com.lp.gameclient.adapter.common.RecyclerAdatpterHelper;
import com.lp.gameclient.base.BaseFragment;
import com.lp.gameclient.data.models.BaseVideoEntity;
import com.lp.gameclient.data.models.VideoEntity;
import com.lp.gameclient.data.models.VideoListEntity;
import com.lp.gameclient.data.repository.VideoRepo;
import com.lp.gameclient.data.retrofit.DefaultSubscriber;
import com.lp.gameclient.utils.GlideUtil;
import com.lp.gameclient.utils.LayoutHelper;
import com.lp.gameclient.widgets.MultipleStatusView;
import com.lp.gameclient.widgets.mRecyclerView.XRecyclerView;
import com.lp.gamenewsdemo.R;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by LP on 2017/9/5/19:25.
 * 视频列表
 */

public class VideoFragment extends BaseFragment {

    @BindView(R.id.content_view)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.msv_layout)
    MultipleStatusView mMultipleStatusView;

    private RecyclerAdapter<VideoEntity> mAdapter;
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
        mXRecyclerView.setLoadingListener(this);

        setAdapter();
    }

    private void initVideoListData(int mPage) {
        VideoRepo.getIntance().getVideoList(mPage, mContext)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new DefaultSubscriber<BaseVideoEntity<VideoListEntity>>() {

                    @Override
                    public void _onNext(BaseVideoEntity<VideoListEntity> entity) {
                        if (isRefresh) mAdapter.clear();
                        VideoListEntity videoListEntity = entity.returnData;
                        List<VideoEntity> videoList = videoListEntity.videoList;
                        mAdapter.addAll(videoList);
                    }

                    @Override
                    public void onCompleted() {
                        mXRecyclerView.loadMoreComplete();
                        mXRecyclerView.refreshComplete();
                    }
                });
    }

    private void setAdapter() {
        mAdapter = new RecyclerAdapter<VideoEntity>(mContext, R.layout.item_video){

            @Override
            protected void convert(RecyclerAdatpterHelper helper, VideoEntity item) {
                helper.setText(R.id.tv_pubtime, item.videoAddtime)
                        .setText(R.id.tv_watchNum, "播放次数" + String.valueOf(item.videoViews))
                        .setText(R.id.tv_sourceName, item.videoProviders);

                JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) helper.getItemView().findViewById(R.id.video_view);
                jcVideoPlayerStandard.setUp(item.videoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, item.videoTitle);
                GlideUtil.loadImage(item.videoThumb, jcVideoPlayerStandard.thumbImageView);
            }
        };
        mXRecyclerView.setAdapter(mAdapter);
        mXRecyclerView.addItemDecoration(LayoutHelper.getHorizontalDivider_6(mContext));
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
