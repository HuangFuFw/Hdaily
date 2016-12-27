package com.ailk.hf.hdaily.module;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by huangfu on 2016/12/27 11.:39
 */
public class NewsDetailsActivity extends BaseActivity {

    @Bind(R.id.news_details_toolbar)
    Toolbar newsDetailsToolbar;
    @Bind(R.id.news_datails_viewPager)
    ViewPager newsDatailsViewPager;

    private List<String> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        initToolBar();
        initViewPager();
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
            for (int i = 0; i < 9; i++) {
                String str = "这是测试页面数据" + i;
                data.add(str);
            }
            newsDatailsViewPager.getAdapter().notifyDataSetChanged();

    }

    private void initViewPager() {
        newsDatailsViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                String str = data.get(position);
                return NewsDetailsPagerFragment.newInstance(str);
            }

            @Override
            public int getCount() {
                return data.size();
            }
        });


        newsDatailsViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
