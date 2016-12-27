package com.ailk.hf.hdaily.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by huangfu on 2016/12/27 16.:52
 */
public class NewsDetailsPagerFragment extends BaseFragment {
    @Bind(R.id.txt_user)
    TextView txtUser;
    private String string;
    private static final String ARG_PARAM1 = "param1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            string = getArguments().getString(ARG_PARAM1);
        }
    }

    public static NewsDetailsPagerFragment newInstance(String str) {
        NewsDetailsPagerFragment fragment = new NewsDetailsPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, str);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_details_pager;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        txtUser.setText(string);
    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
