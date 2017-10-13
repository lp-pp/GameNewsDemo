package com.lp.gameclient.module.news;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import com.lp.gameclient.adapter.NewsAdapter;
import com.lp.gameclient.adapter.common.RecyclerAdapter;
import com.lp.gameclient.adapter.common.RecyclerAdatpterHelper;
import com.lp.gameclient.base.BaseFragment;
import com.lp.gameclient.data.models.BaseNewsEntity;
import com.lp.gameclient.data.models.NewsEntity;
import com.lp.gameclient.data.repository.NewsRepo;
import com.lp.gameclient.data.retrofit.DefaultSubscriber;
import com.lp.gameclient.utils.C;
import com.lp.gameclient.utils.LayoutHelper;
import com.lp.gameclient.utils.PPLog;
import com.lp.gameclient.widgets.MultipleStatusView;
import com.lp.gameclient.widgets.mRecyclerView.XRecyclerView;
import com.lp.gamenewsdemo.R;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by LP on 2017/10/13/15:00.
 */

/**
 * 文章分类展示列表
 */
public class NewsListFragment extends BaseFragment{

    @BindView(R.id.content_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.msv_layout)
    MultipleStatusView mMultipleStatusView;

    private int mCateId;
    private int mPage;

    private RecyclerAdapter mRecyclerAdapter;
    private Realm mRealm;
    private RealmResults<NewsEntity> mRealmResults;

    @Override
    protected int getLayout() {
        return R.layout.layout_msv_recycler;
    }

    @Override
    protected View getToolBarView() {
        return null;
    }

    private void getBundle() {
        mCateId = getArguments().getInt(C.CATE_ID);
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        getBundle();
        mRealm = Realm.getDefaultInstance();
        mRealmResults = mRealm.where(NewsEntity.class).equalTo("cateId", mCateId).findAll();
        mRealmResults.addChangeListener(mRealmlistener);

        mMultipleStatusView.loading();
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setLayoutManager(LayoutHelper.getLinearLayout(mContext));
        mRecyclerView.addItemDecoration(LayoutHelper.getHorizontalDivider_6(mContext));

        setAdapter();
    }

    private void setAdapter() {
        mRecyclerAdapter = new NewsAdapter(mContext) {
            @Override
            protected void convert(RecyclerAdatpterHelper helper, NewsEntity item) {
                super.convert(helper, item);
                int position = helper.getAdapterPosition();
                int size = mRecyclerAdapter.getSize();
                if (position == size - 1){
                    mPage = Integer.parseInt(item.getId().trim());
                }
            }
        };

        SlideInBottomAnimationAdapter mSlideAdapter = new SlideInBottomAnimationAdapter(mRecyclerAdapter);
        mSlideAdapter.setFirstOnly(true);
        mSlideAdapter.setDuration(500);
        mSlideAdapter.setInterpolator(new AnticipateInterpolator(1f));

        mRecyclerView.setAdapter(mSlideAdapter);
    }

    //监听realm查询结果的变化，如果realm数据库更新了，该结果会自动变化为更新后的结果
    RealmChangeListener mRealmlistener = new RealmChangeListener<RealmResults<NewsEntity>>() {
        @Override
        public void onChange(RealmResults<NewsEntity> element) {
            PPLog.i("realm >>> 监听器中size:" + element.size());
            mRecyclerAdapter.addAllAt(0, element);
        }
    };

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPage = 0;
        loadNewsData(mPage);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        loadNewsData(mPage);
    }

    private void loadNewsData(int mPage) {
        NewsRepo.getInstance().getNewsList(mCateId, mPage)
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new DefaultSubscriber<BaseNewsEntity<List<NewsEntity>>>() {
                    @Override
                    public void _onNext(BaseNewsEntity<List<NewsEntity>> entity) {
//                        saveToRealm(entity.data); //FIXME
                        if (isRefresh)
                            mRecyclerAdapter.clear();
                        mRecyclerAdapter.addAll(entity.data);
                    }

                    @Override
                    public void onCompleted() {
                        mMultipleStatusView.content();
                        mRecyclerView.loadMoreComplete();
                        mRecyclerView.refreshComplete();
                    }
                });
    }

    //保存到Realm中
    private void saveToRealm(List<NewsEntity> entities) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                for (int i = 0, size = entities.size(); i < size; i++){
                    NewsEntity entity = entities.get(i);
                    //如果该条数据已经存在则不保存
                    if (realm.where(NewsEntity.class).equalTo("cateId", mCateId)
                            .contains("id", entity.getId()).count() == 0){
                        PPLog.i("realm 数据库中添加数据--->>>id" + entity.getId());
                        NewsEntity newsEntity = realm.createObject(NewsEntity.class);
                        newsEntity.setId(entity.getId());
                        newsEntity.setTitle(entity.getTitle());
                        //realm保存图片的时候有些问题
//                        newsEntity.setmImageEntityList(entity.getmImageEntityList());
                        newsEntity.setCreate_time(entity.getCreate_time());
                        newsEntity.setSource_id(entity.getSource_id());
                        newsEntity.setSource_name(entity.getSource_name());
                        newsEntity.setStatus(entity.getStatus());
                        newsEntity.setTemplate_id(entity.getTemplate_id());
                        newsEntity.setUrl(entity.getUrl());
                        newsEntity.setCateId(mCateId);
                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                PPLog.i("realm 添加数据后--->>> size:" + mRealmResults.size());
            }
        });
    }
}
