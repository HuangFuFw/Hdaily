package com.ailk.hf.hdaily.module;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by huangfu on 2016/12/16 15.:23
 */
public class IndexFragment extends BaseFragment {
    //
//    @Bind(R.id.banner)
//    Banner banner;
    @Bind(R.id.recylerView)
    RecyclerView recylerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<String> data = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Context mContext;
    private boolean isLoading;
    private int lastVisibleItemPosition;
    private Handler handler = new Handler();
    /**
     * 标记加载更多的position
     */
    private int mLoadMorePosition;

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
                mLoadMorePosition = lastVisibleItemPosition;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        &&lastVisibleItemPosition + 1 == layoutManager.getItemCount()) {
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
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
            }
        });


    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        this.mContext = activity;
//        super.onAttach(activity);
//    }




    private void initView() {

    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLatestNews();
            }
        }, 1500);
    }

    private void getLatestNews() {
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
        swipeRefreshLayout.setRefreshing(false);

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

}
