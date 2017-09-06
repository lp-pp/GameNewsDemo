package com.lp.gameclient.data.retrofit;

import com.lp.gameclient.data.models.BaseNewsEntity;
import com.lp.gameclient.data.models.NewsEntity;
import com.lp.gameclient.utils.C;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Lp on 2017/9/6/12:07.
 */

public interface NewsService {

    @FormUrlEncoded
    @POST("toutiao/get_list")
    Observable<BaseNewsEntity<List<NewsEntity>>> newsList(
            @Field(C.CATE_ID) int cateId,
            @Field(C.MAX) int page);
}
