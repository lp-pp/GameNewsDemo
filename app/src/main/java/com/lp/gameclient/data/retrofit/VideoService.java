package com.lp.gameclient.data.retrofit;

import com.lp.gameclient.data.models.BaseVideoEntity;
import com.lp.gameclient.data.models.TokenEntity;
import com.lp.gameclient.data.models.VideoListEntity;
import com.lp.gameclient.utils.C;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LP on 2017/9/6/12:07.
 */

public interface VideoService {

    @GET("video/vlist")
    Observable<BaseVideoEntity<VideoListEntity>> videoList(
            @Query(C.PAGENO) int pageNum,
            @Query(C.PAGESIZE) int pageSize,
            @Query(C.CATEID) String cateId,
            @Query(C.TOKEN) String token);

    @GET("token")
    Observable<BaseVideoEntity<TokenEntity>> userToken(
            @Query(C.DEVICE) String divice);
}
