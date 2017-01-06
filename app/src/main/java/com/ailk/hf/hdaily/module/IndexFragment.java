package com.ailk.hf.hdaily.module;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;
import com.ailk.hf.hdaily.model.LatestNews;
import com.ailk.hf.hdaily.model.NewsInfo;
import com.ailk.hf.hdaily.module.welcome.MyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by huangfu on 2016/12/16 15.:23
 */
public class IndexFragment extends BaseFragment {
    @Bind(R.id.recylerView)
    RecyclerView recylerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<NewsInfo> data = new ArrayList<>();
    private List<NewsInfo> topData = new ArrayList<>();
    private String date = null;
    private RecyclerViewAdapter adapter;
    private boolean isLoading;
    private int lastVisibleItemPosition;
    private Handler handler = new Handler();
    /**
     * 标记加载更多的position
     */
    private int mLoadMorePosition;
    private String latestDate;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_index, container, false);
//        ButterKnife.bind(this, view);
//        initView();
//        initData();
////        initBanner();
//        return view;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

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
                getLatestNews();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recylerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(mActivity, data);
        recylerView.setAdapter(adapter);
        recylerView.setItemAnimator(new DefaultItemAnimator());
        recylerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition + 1 == layoutManager.getItemCount()) {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getLatestNews();
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("id",data.get(position).getId());
                getActivity().startActivity(intent);
//              getActivity().startActivity(new Intent(getActivity(), NewsDetailsActivity.class));
            }
        });


    }


    @Override
    protected void initData() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
        getLatestNews();
//            }
//        }, 1500);
    }

    private void getLatestNews() {
        MyWrapper wrapper = new MyWrapper();
        Subscription subscription = wrapper.getLatestNews().subscribe(newSubscriber(new Action1<LatestNews>() {
            @Override
            public void call(LatestNews info) {
                latestDate = info.getDate();

                if (!latestDate.equals(date)) {
                    data.addAll(info.getStories());
                } else if (data.size() == 0) {
                    data.addAll(info.getStories());
                }

                for (int i = 0; i < info.getStories().size(); i++) {
                    info.getStories().get(i).setDate(latestDate);
                }

                date = latestDate;
                adapter.setTopData(info.getTop_stories());
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }));
        mCompositeSubscription.add(subscription);
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

}
