package com.lp.gameclient.data.retrofit;

import com.lp.gameclient.data.models.ImageListEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by LP on 2017/9/6/12:07.
 */

public interface ImageService {

    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<ImageListEntity> imageList(
            @Path("page") int page);
}
