package com.ailk.hf.hdaily.module.welcome;


import com.ailk.hf.hdaily.model.SplashInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by huangfu on 2016/11/30 17.:14
 */
public interface MyService {

    @GET("start-image/1080*1776")
    Observable<SplashInfo> getSplashInfo();

//    Observable<Response<SplashInfo>> getS(@Field("name") String type);
}
