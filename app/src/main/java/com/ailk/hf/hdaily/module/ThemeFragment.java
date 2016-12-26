package com.ailk.hf.hdaily.module;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;
import com.ailk.hf.hdaily.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by huangfu on 2016/12/23 16.:02
 */
public class ThemeFragment extends BaseFragment {
    @Bind(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @Bind(R.id.txt_user)
    TextView txtUser;
    @Bind(R.id.txt_drawer_UserProfile)
    TextView txtDrawerUserProfile;
    @Bind(R.id.txt_drawer_OfflineDownload)
    TextView txtDrawerOfflineDownload;
    @Bind(R.id.rv_theme_menu)
    RecyclerView rvThemeMenu;

    private List<String> data = new ArrayList<>();
    private RecyclerViewMenuAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvThemeMenu.setLayoutManager(layoutManager);
        adapter = new RecyclerViewMenuAdapter(mActivity, data);
        rvThemeMenu.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 2; i++) {
            String str = "这是测试数据title1" + i;
            data.add(str);
        }
        for (int i = 0; i < 3; i++) {
            String str = "这是测试数据" + i;
            data.add(str);
        }
        for (int i = 0; i < 2; i++) {
            String str = "这是测试数据title2" + i;
            data.add(str);
        }
//        adapter.notifyItemRemoved(0);
//        adapter.notifyItemRemoved(mLoadMorePosition);
//        adapter.notifyItemRangeChanged(0,mLoadMorePosition);
//        int i=adapter.getItemViewType(adapter.getItemCount());
        adapter.notifyDataSetChanged();

    }
}
