package com.ailk.hf.hdaily.utils;

import android.util.Log;

import com.ailk.hf.hdaily.config.Constant;
import com.ailk.hf.hdaily.module.welcome.MyService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by huangfu on 2016/11/30 17.:29
 */
public class RetrofitUtil {

    private static MyService service;
    private static Retrofit retrofit;


    public static MyService getService() {
        if (service == null) {
            service = getRetrofit().create(MyService.class);
        }
        return service;
    }


    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("RxJava", message);
                }
            });

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(Constant.Host)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public  <T> Observable<T> flatResonse(final T t){
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                if(!subscriber.isUnsubscribed()){
                    subscriber.onNext(t);
                }
                if(!subscriber.isUnsubscribed()){
                    subscriber.onCompleted();
                }
            }
        });
    }


    protected <T> Observable.Transformer<T,T> applySchedule(){
//        return new Observable.Transformer<T,T>() {
//            @Override
//            public Observable<T> call(Observable<T> responseObservable) {
//                return responseObservable.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .flatMap(new Func1<T, Observable<T>>() {
//                            @Override
//                            public Observable<T> call(final T response) {
//                                return flatResonse(response);
//                            }
//                        });
//            }
//        };(Observable.Transformer<T,T>)
        return transformer;
    }

    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object responseObservable) {
            return ((Observable) responseObservable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1() {
                        @Override
                        public Object call(Object response) {
                            return flatResonse(response);
                        }
                    });
        }
    };

}
