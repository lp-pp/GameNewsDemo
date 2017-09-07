package com.lp.gameclient.data.repository;

import android.content.Context;
import android.text.TextUtils;

import com.lp.gameclient.base.BaseRepo;
import com.lp.gameclient.data.models.BaseVideoEntity;
import com.lp.gameclient.data.models.TokenEntity;
import com.lp.gameclient.data.models.VideoListEntity;
import com.lp.gameclient.data.retrofit.DefaultSubscriber;
import com.lp.gameclient.data.retrofit.RetrofitClient;
import com.lp.gameclient.utils.C;
import com.lp.gameclient.utils.SPUtil;

import rx.Observable;

/**
 * Created by LP on 2017/9/6/11:41.
 * 视频数据仓库
 */

public class VideoRepo extends BaseRepo{
    private final static String TAG = VideoRepo.class.getName();

    private static VideoRepo mInstance = null;

    private VideoRepo(){}

    public static VideoRepo getIntance(){
        if (mInstance == null){
            synchronized(VideoRepo.class){
                if (mInstance == null)
                    mInstance = new VideoRepo();
            }
        }
        return mInstance;
    }

    public void getUserToken(Context context){
        String token = (String) SPUtil.get(C.F_Token, context, C.TOKEN, "");
        if (!TextUtils.isEmpty(token)){
            C.token = token;
            return;
        }
        transform(RetrofitClient.getVideoInstance().VIDEO().userToken("android"))
                .subscribe(new DefaultSubscriber<BaseVideoEntity<TokenEntity>>() {
                    @Override
                    public void _onNext(BaseVideoEntity<TokenEntity> entity) {
                        TokenEntity tokenEntity = entity.returnData;
                        C.token = tokenEntity.userToken;
                        SPUtil.put(C.F_Token, context, C.TOKEN, tokenEntity.userToken);
                    }
                });
    }

    /**
     * 获取视频列表
     * @param pageNum 分页
     * @param context
     * @return
     */
    public Observable<BaseVideoEntity<VideoListEntity>> getVideoList(int pageNum, Context context){
        String token = C.token;
        if (TextUtils.isEmpty(token))
            getUserToken(context);
        return transform(RetrofitClient.getVideoInstance().VIDEO().videoList(pageNum, 5, "all", token));
    }
}
