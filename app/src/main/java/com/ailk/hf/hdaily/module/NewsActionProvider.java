package com.ailk.hf.hdaily.module;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ailk.hf.hdaily.R;

/**
 * Created by huangfu on 2017/1/12 11.:03
 */
public class NewsActionProvider extends ActionProvider {

    TextView tv2;
    private Context mContext;
    private String data;

    public NewsActionProvider(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View onCreateActionView() {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toolbar_menu_newsaction, null);
        tv2= (TextView) view.findViewById(R.id.tv_supporters);

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                Toast.makeText(mContext, "收藏" + getData(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
