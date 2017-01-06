package com.ailk.hf.hdaily.module;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;
import com.ailk.hf.hdaily.model.ThemesDaily;
import com.ailk.hf.hdaily.model.ThemesDailyList;
import com.ailk.hf.hdaily.module.welcome.MyWrapper;
import com.ailk.hf.hdaily.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

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

    private List<ThemesDaily> data = new ArrayList<>();
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

        adapter.setOnItemClickListener(new RecyclerViewMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                getFragmentManager()
                        .beginTransaction()
// .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                        .replace(
                                R.id.frame_main,
                                ThemesDailyFragment.newInstance(data.get(position)
                                        .getId(), data.get(position).getName()), "ThemesDaily").commit();

                ((MainActivity) mActivity).closeMenu();

            }
        });
    }

//    public void switchContent() {
//        if ()
//
//        getFragmentManager()
//                .beginTransaction()
//// .setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
//                .replace(
//                        R.id.frame_main,
//                        ThemesDailyFragment.newInstance(data.get(position)
//                                .getId(), data.get(position).getName()), "ThemesDaily").commit();
//
//        ((MainActivity) mActivity).closeMenu();
//
//                ft.hide(from).add(R.id.frame_main, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
//
//    }

    @Override
    protected void initData() {
        MyWrapper wrapper = new MyWrapper();
        Subscription subscription = wrapper.getThemesDailyList().subscribe(newSubscriber(new Action1<ThemesDailyList>() {
            @Override
            public void call(ThemesDailyList info) {
                if (data.size() > 0)
                    data.clear();
                data.addAll(info.getOthers());
                adapter.notifyDataSetChanged();

            }
        }));
        mCompositeSubscription.add(subscription);
    }
}
