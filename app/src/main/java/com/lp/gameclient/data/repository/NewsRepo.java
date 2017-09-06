package com.lp.gameclient.data.repository;

import com.lp.gameclient.base.BaseRepo;
import com.lp.gameclient.data.models.BaseNewsEntity;
import com.lp.gameclient.data.models.NewsEntity;
import com.lp.gameclient.data.retrofit.RetrofitClient;
import com.lp.gameclient.utils.PPLog;

import java.util.List;

import rx.Observable;

/**
 * Created by LP on 2017/8/4/17:43.
 * 新闻数据仓库
 */

public class NewsRepo extends BaseRepo{
    private final static String TAG = NewsRepo.class.getName();

    private static volatile NewsRepo mInstance = null;

    private NewsRepo(){}

    public static NewsRepo getInstance(){
        if (mInstance == null){
            synchronized (NewsRepo.class){
                if (mInstance == null)
                    mInstance = new NewsRepo();
            }
        }
        return mInstance;
    }

    /**
     * 获取新闻列表
     * @param cateId  栏目分类ID
     * @param pageNum 分页
     * @return
     */
    public Observable<BaseNewsEntity<List<NewsEntity>>> getNewsList(int cateId, int pageNum) {
        PPLog.i("开始获取数据");
        return transform(RetrofitClient.getNewsInstance().NEWS().newsList(cateId, pageNum));
    }
}
