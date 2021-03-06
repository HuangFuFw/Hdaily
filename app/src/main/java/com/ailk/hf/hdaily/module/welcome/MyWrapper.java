package com.ailk.hf.hdaily.module.welcome;

import com.ailk.hf.hdaily.model.LatestNews;
import com.ailk.hf.hdaily.model.NewsDetailInfo;
import com.ailk.hf.hdaily.model.SplashInfo;
import com.ailk.hf.hdaily.model.ThemesDailyInfo;
import com.ailk.hf.hdaily.model.ThemesDailyList;
import com.ailk.hf.hdaily.utils.RetrofitUtil;

import rx.Observable;


/**
 * Created by huangfu on 2016/11/30 18.:15
 */
public class MyWrapper extends RetrofitUtil {


    public Observable<SplashInfo> getSplashInfo() {
        return getService().getSplashInfo()
             .compose(this.<SplashInfo>applySchedule());
    }

    public Observable<LatestNews> getLatestNews() {
        return getService().getLatestNews()
                .compose(this.<LatestNews>applySchedule());
    }

    public Observable<NewsDetailInfo> getNewsDetailInfo(String id) {
        return getService().getNewsDetailInfo(id)
                .compose(this.<NewsDetailInfo>applySchedule());
    }

    public Observable<ThemesDailyList> getThemesDailyList() {
        return getService().getThemesDailyList()
                .compose(this.<ThemesDailyList>applySchedule());
    }

    public Observable<ThemesDailyInfo> getDailyDetailInfo(String id) {
        return getService().getDailyDetailInfo(id)
                .compose(this.<ThemesDailyInfo>applySchedule());
    }

//    public Observable<SplashInfo> getSplashInfo() {
//        return getService().getSplashInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Func1<SplashInfo, Observable<SplashInfo>>() {
//                    @Override
//                    public Observable<SplashInfo> call(final SplashInfo response) {
//                        return flatResonse(response);
//                    }
//                });
//    }

//    return Observable.create(new Observable.OnSubscribe<SplashInfo>(){
//        @Override
//        public void call(Subscriber<? super SplashInfo> subscriber) {
//            if(!subscriber.isUnsubscribed()){
//                subscriber.onNext(response);
//            }
//            if(!subscriber.isUnsubscribed()){
//                subscriber.onCompleted();
//            }
//        }
//    });

//    public static class APIException extends Exception{
//        public String code;
//        public String message;
//
//        public APIException(String code, String message) {
//            this.code = code;
//            this.message = message;
//        }
//
//        @Override
//        public String getMessage() {
//            return message;
//        }
//
//        public String getCode() {
//            return code;
//        }
//    }



}
