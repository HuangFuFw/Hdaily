package com.ailk.hf.hdaily.module;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsdetail, menu);
        MenuItem item = menu.findItem(R.id.user_defined);
        NewsActionProvider cartActionProvider = (NewsActionProvider) MenuItemCompat.getActionProvider(item);
        cartActionProvider.setData("测试");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_follower:
                // TODO: 2017/1/12   收藏
                showToast("ssss");
                break;
            case R.id.action_share:
                // TODO: 2017/1/12 分享
                showToast("分享");
                break;
//            case R.id.action_cart:
//                // TODO: 2017/1/12
//                NewsActionProvider cartActionProvider=new NewsActionProvider(NewsDetailsActivity.this);
//                cartActionProvider.setData("测试");
//                MenuItemCompat.setActionProvider(item,cartActionProvider);
//                break;
        }
        return true;
    }

    private void initToolBar() {
        setSupportActionBar(newsDetailsToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置toolbar自带返回键为白色
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        newsDetailsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        String css = "<link rel=\"stylesheet\" href=\"" + info.getCss() + "\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + info.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
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
