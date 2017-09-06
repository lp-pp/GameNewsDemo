package com.lp.gameclient.module.main;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import com.lp.gameclient.adapter.common.RecyclerAdapter;
import com.lp.gameclient.adapter.common.RecyclerAdatpterHelper;
import com.lp.gameclient.base.BaseFragment;
import com.lp.gameclient.data.models.ImageEntity;
import com.lp.gameclient.data.models.ImageListEntity;
import com.lp.gameclient.data.repository.ImageRepo;
import com.lp.gameclient.data.retrofit.DefaultSubscriber;
import com.lp.gameclient.utils.LayoutHelper;
import com.lp.gameclient.widgets.MultipleStatusView;
import com.lp.gameclient.widgets.mRecyclerView.XRecyclerView;
import com.lp.gamenewsdemo.R;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by LP on 2017/9/5/19:25.
 * 图片列表
 */

public class PictureFragment extends BaseFragment {

    @BindView(R.id.content_view)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.msv_layout)
    MultipleStatusView mMultipleStatusView;

    private RecyclerAdapter<ImageEntity> mAdapter;
    private int mPage = 1;

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
        mXRecyclerView.setHasFixedSize(true);
        mXRecyclerView.setLayoutManager(LayoutHelper.getVerticalStagLayout());
        mXRecyclerView.setLoadingListenr(this);

        setAdapter();
    }

    private void setAdapter() {
        mAdapter = new RecyclerAdapter<ImageEntity>(mContext, R.layout.item_image){

            @Override
            protected void convert(RecyclerAdatpterHelper helper, ImageEntity item) {
                helper.setImageUrl(R.id.iv_meizi, item.getUrl(), false);

            }
        };
        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(mAdapter);
        slideAdapter.setFirstOnly(true);
        slideAdapter.setDuration(500);
        slideAdapter.setInterpolator(new AnticipateInterpolator(1f));

        mXRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        getImageListData(mPage);
    }

    private void getImageListData(int page) {
        ImageRepo.getInstance().getImageList(page)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new DefaultSubscriber<ImageListEntity>() {
                    @Override
                    public void _onNext(ImageListEntity entity) {
                        if (isRefresh) mAdapter.clear();
                        List<ImageEntity> imgList = entity.results;
                        mAdapter.addAll(imgList);
                    }

                    @Override
                    public void onCompleted() {
                        mXRecyclerView.loadMoreComplete();
                        mXRecyclerView.refreshComplete();
                    }
                });

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPage = 1;
        getImageListData(mPage);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getImageListData(++mPage);
    }
}
