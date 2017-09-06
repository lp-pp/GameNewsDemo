package com.lp.gameclient.data.repository;

import com.lp.gameclient.base.BaseRepo;
import com.lp.gameclient.data.models.ImageListEntity;
import com.lp.gameclient.data.retrofit.RetrofitClient;
import com.lp.gameclient.utils.PPLog;

import rx.Observable;

/**
 * Created by LP on 2017/9/6/11:05.
 * 图片数据仓库
 */

public class ImageRepo extends BaseRepo{
    private final static String TAG = ImageRepo.class.getName();

    private static volatile ImageRepo mInstance = null;

    private ImageRepo(){}

    public static ImageRepo getInstance(){
        if (mInstance == null){
            synchronized (ImageRepo.class){
                if (mInstance == null)
                    mInstance = new ImageRepo();
            }
        }
        return mInstance;
    }

    /**
     * 获取图片列表
     * @param pageNum 分页
     * @return
     */
    public Observable<ImageListEntity> getImageList(int pageNum) {
        PPLog.i("开始获取数据");
        return transform(RetrofitClient.getImageInstance().IMAGE().imageList(pageNum));
    }
}
