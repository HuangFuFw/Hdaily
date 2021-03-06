package com.ailk.hf.hdaily.module;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by huangfu on 2016/12/15 15.:25
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.dragLayout)
    DrawerLayout dragLayout;

    private IndexFragment iFragment;                     //首页fragment

    private boolean mIsIndex = true;
    private boolean mIsSubscribed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (iFragment == null) {
                iFragment = new IndexFragment();
            }
            ft.replace(R.id.frame_main, iFragment).commit();
            mIsIndex = true;
        }
    }

    private void initToolBar() {
        toolbar.setTitle(R.string.index_tag);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dragLayout, toolbar, R.string.abc_action_bar_home_description, R.string.abc_action_bar_home_description_format);
        toggle.syncState();
        dragLayout.setDrawerListener(toggle);
    }

    public void setToolbarStyle(String title,boolean mIsIndex) {
        toolbar.setTitle(title);
        this.mIsIndex = mIsIndex;
        invalidateOptionsMenu(); //重新绘制menu
    }

    public void closeMenu() {
        dragLayout.closeDrawers();
    }

    public void showIndex() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment instanceof ThemesDailyFragment) {
            getSupportFragmentManager().beginTransaction().remove(fragment).show(iFragment).commit();
            setToolbarStyle("首页",true);
        }
        closeMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showToast("action_settings");
                break;
            case R.id.action_notify:
                showToast("action_notify");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mIsIndex) {
            menu.findItem(R.id.action_notify).setVisible(true);
            menu.findItem(R.id.action_settings).setVisible(true);
            menu.findItem(R.id.action_about).setVisible(true);
            menu.findItem(R.id.action_subscribe).setVisible(false);
        } else {
            menu.findItem(R.id.action_subscribe).setVisible(true);
            if (mIsSubscribed) {
                menu.findItem(R.id.action_subscribe).setIcon(R.mipmap.ic_drawer_home_normal);
            } else {
                menu.findItem(R.id.action_subscribe).setIcon(R.mipmap.ic_drawer_home_pressed);
            }
            menu.findItem(R.id.action_notify).setVisible(false);
            menu.findItem(R.id.action_settings).setVisible(false);
            menu.findItem(R.id.action_about).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment instanceof ThemesDailyFragment) {
            getSupportFragmentManager().beginTransaction().remove(fragment).show(iFragment).commit();
            setToolbarStyle("首页",true);
        } else if (fragment instanceof IndexFragment) {
            finish();
        }
    }
}
