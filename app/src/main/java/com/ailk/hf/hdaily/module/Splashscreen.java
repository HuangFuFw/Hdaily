package com.ailk.hf.hdaily.module;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseActivity;
import com.ailk.hf.hdaily.model.SplashInfo;
import com.ailk.hf.hdaily.module.welcome.MyWrapper;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by huangfu on 2016/12/9 14.:35
 */
public class Splashscreen extends BaseActivity {

    Thread splashTread;
    @Bind(R.id.splash)
    SimpleDraweeView draweeView;
//    @Bind(R.id.lin_lay)
//    LinearLayout linLay;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);
        loadImg();


    }

    private void loadImg() {
        MyWrapper wrapper = new MyWrapper();
        Subscription subscription = wrapper.getSplashInfo().subscribe(newSubscriber(new Action1<SplashInfo>() {
            @Override
            public void call(SplashInfo info) {
                StartAnimations(info);
//                Uri uri = Uri.parse(info.getImg());
//                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.splash);
//                draweeView.setImageURI(uri);
            }
        }));
        mCompositeSubscription.add(subscription);

    }

    private void StartAnimations(SplashInfo info) {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        anim.setFillAfter(true);
        anim.reset();
        Uri uri = Uri.parse(info.getImg());
//        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.splash);
        draweeView.setImageURI(uri);
        draweeView.clearAnimation();
        draweeView.startAnimation(anim);

//        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        anim.setInterpolator(new AccelerateInterpolator());
//        anim.setFillAfter(true);
//        anim.reset();
////        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
//        linLay.clearAnimation();
//        linLay.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
//                    overridePendingTransition(R.anim.scale,android.R.anim.fade_out);
                    Splashscreen.this.finish();
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mCompositeSubscription.unsubscribe();
    }

}
