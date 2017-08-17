package com.lp.gameclient.data.repository;

import android.content.Context;

import com.lp.gameclient.base.BaseRepo;
import com.lp.gameclient.data.models.BaseVideoEntity;
import com.lp.gameclient.data.models.VideoListEntity;

import rx.Observable;

/**
 * Created by LP on 2017/8/17/20:19.
 * 游戏数据仓库
 */

public class VideoRepo extends BaseRepo{

    private VideoRepo(){}

    public static VideoRepo getIntance(){
        return new VideoRepo();
    }

    public void getUserToken(Context context){

    }

    public Observable<BaseVideoEntity<VideoListEntity>> getVideoList(int mPage, Context context){

    }
}
