package com.ailk.hf.hdaily.module;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if(savedInstanceState==null){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            if(iFragment==null){
                iFragment=new IndexFragment();
            }
            ft.replace(R.id.frame_main, iFragment).commit();
        }
    }

    private void initToolBar() {
        toolbar.setTitle("Title");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dragLayout, toolbar, R.string.abc_action_bar_home_description, R.string.abc_action_bar_home_description_format);
        toggle.syncState();
        dragLayout.setDrawerListener(toggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent intent = new Intent();
//        switch (item.getItemId())
//        {
//            case R.id.main_toolbar_search:
//                showToast("main_toolbar_search");
//                break;
//            case R.id.main_toolbar_notify:
//                intent.setClass(this,NotifyActivity.class);
//                break;
//        }
//        startActivity(intent);
//        //切换动画一定要放在startActivity后面，并且一定要先进后出
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
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


}
