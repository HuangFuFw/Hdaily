//package com.ailk.hf.hdaily.module;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.ailk.hf.hdaily.R;
//import com.ailk.hf.hdaily.app.BaseActivity;
//import com.ailk.hf.hdaily.model.SplashInfo;
//import com.ailk.hf.hdaily.module.welcome.MyWrapper;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.yyydjk.library.BannerLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import rx.Subscription;
//import rx.functions.Action1;
//
///**
// * Created by huangfu on 2016/11/11 17.:10
// */
//public class SplashActivity extends BaseActivity {
//    @Bind(R.id.btn1)
//    Button btn1;
//    @Bind(R.id.btn2)
//    Button btn2;
//    @Bind(R.id.banner)
//    BannerLayout banner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.splash);
//        ButterKnife.bind(this);
//        //网络地址
//
//        final List<String> urls = new ArrayList<>();
//        urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/1.png");
//        urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/2.png");
//        urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png");
//        banner.setViewUrls(urls);
//
////本地资源
////        banner.setViewRes(urls);
//
////添加点击监听
//        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//            }
//        });
//
////        RetrofitUtil.getService().getS("")
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MyWrapper wrapper = new MyWrapper();
//                Subscription subscription = wrapper.getSplashInfo().subscribe(newSubscriber(new Action1<SplashInfo>() {
//                    @Override
//                    public void call(SplashInfo info) {
//                        Uri uri = Uri.parse(info.getImg());
//                        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
//                        draweeView.setImageURI(uri);
//                    }
//                }));
//                mCompositeSubscription.add(subscription);
//
//
////                wrapper.getSplashInfo().subscribe(new Subscriber<SplashInfo>() {
////                    @Override
////                    public void onCompleted() {
////
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////
////                    }
////
////                    @Override
////                    public void onNext(SplashInfo info) {
////                        Uri uri = Uri.parse(info.getImg());
////                        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
////                        draweeView.setImageURI(uri);
////                    }
////                });
//
//            }
//        });
//
//    }
//
////    @Override
////    public void onClick(View v) {
////        super.onClick(v);
////        switch (v.getId()) {
////            case R.id.btn1:
////                break;
////            default:
////                break;
////        }
////    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        mCompositeSubscription.unsubscribe();
//
//    }
//}
