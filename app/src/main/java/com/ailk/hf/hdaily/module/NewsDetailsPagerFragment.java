package com.ailk.hf.hdaily.module;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.app.BaseFragment;

import butterknife.Bind;

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

}
