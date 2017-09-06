package com.lp.gameclient.data.retrofit;

import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lp on 2017/9/4/18:00.
 * retrofit封装
 */

public class RetrofitClient {

    public static final String URL_BASE_NEWS = "http://shouyoutoutiao.app.17wanba.com/"; //新闻来源
    public static final String URL_BASE_VIDEO = "http://api.shenyou.tv/apiv1/"; //视频来源
    public static final String URL_BASE_IMAGE = "http://gank.io/api/"; //图片来源

    private static Retrofit mNewsRetrofit = null;
    private static Retrofit mVideoRetrofit = null;
    private static Retrofit mImageRetrofit = null;
    private static volatile RetrofitClient vNewsRetrofitClient = null;
    private static volatile RetrofitClient vVideoRetrofitClient = null;
    private static volatile RetrofitClient vImageRetrofitClient = null;
    private final static int TYPE_NEWS = 1;
    private final static int TYPE_VIDEO = 2;
    private final static int TYPE_IMAGE = 3;

    private RetrofitClient(int type) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("lp"))
                .build();

        Retrofit.Builder buider = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        if (type == TYPE_NEWS){
            Retrofit.Builder newBuider = buider;
            mNewsRetrofit = newBuider.baseUrl(URL_BASE_NEWS).build();
        } else if (type == TYPE_VIDEO){
            Retrofit.Builder newBuider = buider;
            mNewsRetrofit = newBuider.baseUrl(URL_BASE_VIDEO).build();
        } else if (type == TYPE_IMAGE){
            Retrofit.Builder newBuider = buider;
            mNewsRetrofit = newBuider.baseUrl(URL_BASE_IMAGE).build();
        }
    }


    /**
     * 新闻
     * @return
     */
    public static RetrofitClient getNewsInstance() {
        if (vNewsRetrofitClient == null){
            synchronized (RetrofitClient.class){
                if (vNewsRetrofitClient == null)
                    vNewsRetrofitClient = new RetrofitClient(TYPE_NEWS);
            }
        }
        return vNewsRetrofitClient;
    }

    /**
     * 视频
     * @return
     */
    public static RetrofitClient getVideoInstance() {
        if (vVideoRetrofitClient == null){
            synchronized (RetrofitClient.class){
                if (vVideoRetrofitClient == null)
                    vVideoRetrofitClient = new RetrofitClient(TYPE_VIDEO);
            }
        }
        return vVideoRetrofitClient;
    }

    /**
     * 图片
     * @return
     */
    public static RetrofitClient getImageInstance() {
        if (vImageRetrofitClient == null){
            synchronized (RetrofitClient.class){
                if (vImageRetrofitClient == null)
                    vImageRetrofitClient = new RetrofitClient(TYPE_IMAGE);
            }
        }
        return vImageRetrofitClient;
    }

    public NewsService NEWS(){
        return createNews(NewsService.class);
    }

    public VideoService VIDEO(){
        return createVideo(VideoService.class);
    }

    public ImageService IMAGE(){
        return createImage(ImageService.class);
    }

    private static <T> T createNews(Class<T> apiService) {
        return vNewsRetrofitClient.getNewsInstance().mNewsRetrofit.create(apiService);
    }

    private static <T> T createVideo(Class<T> apiService) {
        return vVideoRetrofitClient.getVideoInstance().mNewsRetrofit.create(apiService);
    }

    private static <T> T createImage(Class<T> apiService) {
        return vImageRetrofitClient.getImageInstance().mNewsRetrofit.create(apiService);
    }

}
