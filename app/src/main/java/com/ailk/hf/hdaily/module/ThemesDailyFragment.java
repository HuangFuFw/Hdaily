package com.ailk.hf.hdaily.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;
import com.ailk.hf.hdaily.model.NewsInfo;
import com.ailk.hf.hdaily.model.ThemesDailyInfo;
import com.ailk.hf.hdaily.module.adapter.ThemesDailyRecyclerViewAdapter;
import com.ailk.hf.hdaily.module.welcome.MyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by huangfu on 2017/1/4 15.:53
 */
public class ThemesDailyFragment extends BaseFragment {
    private static String EXTRA_ID = "extra_id";
    private static String EXTRA_TITLE = "extra_title";
    @Bind(R.id.themedaily_rv)
    RecyclerView themedailyRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private String id;
    private String title;
    private List<NewsInfo> newsInfoList = new ArrayList<>();
    //    private List<DailyEditorsInfo> editorsInfoList = new ArrayList<>();
    private ThemesDailyRecyclerViewAdapter adapter;

    public static final ThemesDailyFragment newInstance(String id, String title) {
        ThemesDailyFragment f = new ThemesDailyFragment();
        Bundle bdl = new Bundle();
        bdl.putString(EXTRA_ID, id);
        bdl.putString(EXTRA_TITLE, title);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString(EXTRA_ID);
        title = getArguments().getString(EXTRA_TITLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_themesdaily;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ((MainActivity) mActivity).setToolbarTitle(title);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        themedailyRv.setLayoutManager(layoutManager);
        adapter = new ThemesDailyRecyclerViewAdapter(mActivity, newsInfoList);
        themedailyRv.setAdapter(adapter);
        themedailyRv.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new ThemesDailyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mActivity, NewsDetailsActivity.class);
                intent.putExtra("id", newsInfoList.get(position).getId());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        MyWrapper wrapper = new MyWrapper();
        Subscription subscription = wrapper.getDailyDetailInfo(id).subscribe(newSubscriber(new Action1<ThemesDailyInfo>() {
            @Override
            public void call(ThemesDailyInfo info) {
                if (newsInfoList.size() > 0)
                    newsInfoList.clear();
                newsInfoList.addAll(info.getStories());
                adapter.setEditorsInfoList(info.getEditors());
                adapter.setthemeDailyBgUrl(info.getImage());
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }));
        mCompositeSubscription.add(subscription);

    }
}
