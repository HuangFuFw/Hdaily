package com.ailk.hf.hdaily.module.welcome;


import com.ailk.hf.hdaily.model.LatestNews;
import com.ailk.hf.hdaily.model.NewsDetailInfo;
import com.ailk.hf.hdaily.model.SplashInfo;
import com.ailk.hf.hdaily.model.ThemesDailyInfo;
import com.ailk.hf.hdaily.model.ThemesDailyList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huangfu on 2016/11/30 17.:14
 */
public interface MyService {

    @GET("start-image/1080*1776")
    Observable<SplashInfo> getSplashInfo();

    @GET("news/latest")
    Observable<LatestNews> getLatestNews();

    //http://news-at.zhihu.com/api/4/news/3892357
    @GET("news/{id}")
    Observable<NewsDetailInfo> getNewsDetailInfo(@Path("id") String id);


    @GET("themes")
    Observable<ThemesDailyList> getThemesDailyList();

    //http://news-at.zhihu.com/api/4/theme/11
    @GET("theme/{id}")
    Observable<ThemesDailyInfo> getDailyDetailInfo(@Path("id") String id);

//    Observable<Response<SplashInfo>> getS(@Field("name") String type);
}
