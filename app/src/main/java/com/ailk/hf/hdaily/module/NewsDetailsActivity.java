package com.ailk.hf.hdaily.module;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseActivity;
import com.ailk.hf.hdaily.model.NewsDetailInfo;
import com.ailk.hf.hdaily.module.welcome.MyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by huangfu on 2016/12/27 11.:39
 */
public class NewsDetailsActivity extends BaseActivity {

    @Bind(R.id.news_details_toolbar)
    Toolbar newsDetailsToolbar;
    @Bind(R.id.news_tail)
    TextView newsTail;
    @Bind(R.id.mwebview)
    WebView mWebView;
//    @Bind(R.id.news_datails_viewPager)
//    ViewPager newsDatailsViewPager;
//    @Bind(R.id.news_datails_viewPager)
//    ViewPager newsDatailsViewPager;

    private List<String> data = new ArrayList<>();
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        initToolBar();
//        initViewPager();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mWebView.getSettings().setDatabaseEnabled(true);
        // 开启Application Cache功能
        mWebView.getSettings().setAppCacheEnabled(true);
        initData();
    }


    @Override
    public void widgetClick(View v) {

    }

    private void initToolBar() {
        newsDetailsToolbar.setTitle("新闻详情页");
        newsDetailsToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(newsDetailsToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initData() {
        MyWrapper wrapper = new MyWrapper();
        Subscription subscription = wrapper.getNewsDetailInfo(id).subscribe(newSubscriber(new Action1<NewsDetailInfo>() {
            @Override
            public void call(NewsDetailInfo info) {
                newsTail.setText(info.getTitle());
                parseJson(info);
            }
        }));
        mCompositeSubscription.add(subscription);

    }

    private void parseJson(NewsDetailInfo info) {
        String css = "<link rel=\"stylesheet\" href=\""+info.getCss()+"\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + info.getBody() + "</body></html>";
//        html = html.replace("<div class=\"img-place-holder\">", "");
        mWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);

    }

//    private void initViewPager() {
//        newsDatailsViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//
//                String str = data.get(position);
//                return NewsDetailsPagerFragment.newInstance(str);
//            }
//
//            @Override
//            public int getCount() {
//                return data.size();
//            }
//        });
//
//
//        newsDatailsViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
}
