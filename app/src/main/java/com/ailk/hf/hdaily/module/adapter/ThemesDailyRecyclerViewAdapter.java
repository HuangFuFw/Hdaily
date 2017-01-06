package com.ailk.hf.hdaily.module.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.model.DailyEditorsInfo;
import com.ailk.hf.hdaily.model.NewsInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangfu on 2016/12/19 14.:32
 */
public class ThemesDailyRecyclerViewAdapter extends Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_GROUPITEM = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_FOOTER = 3;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<NewsInfo> newsInfoList;
    private List<DailyEditorsInfo> editorsInfoList = new ArrayList<>();
    private String themeDailyBgUrl;
    private EditorsRecyclerAdapter editorsAdapter;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ThemesDailyRecyclerViewAdapter(Context context, List<NewsInfo> newsInfoList) {
        this.context = context;
        this.newsInfoList = newsInfoList;
        mLayoutInflater = LayoutInflater.from(context);
    }

//    public void putDate(String date, boolean isFirst) {
//        this.currentDate = date;
//        dates.add(date);
//        if (isFirst) {
//            this.prevDate = currentDate;
//        }
//    }

    public void setEditorsInfoList(List<DailyEditorsInfo> editorsInfoList) {
        if (this.editorsInfoList.size() > 0)
            this.editorsInfoList.clear();
        this.editorsInfoList.addAll(editorsInfoList);
    }

    public void setthemeDailyBgUrl(String themeDailyBgUrl) {
        this.themeDailyBgUrl = themeDailyBgUrl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_news, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_footer, parent,
                    false);
            return new FooterViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            View view = mLayoutInflater.inflate(R.layout.fragment_dailyitem_header, parent,
                    false);
            return new HeadererViewHolder(view);
        }
        return null;
    }

    void bindNormalItem(final int position, TextView newsTitle, SimpleDraweeView newsIcon, LinearLayout newsContainer) {
        if (onItemClickListener != null) {
            newsContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
        newsTitle.setText(getItem(position).getTitle());
        if (getItem(position).getImages() != null) {
            newsIcon.setImageURI(getItem(position).getImages().get(0));
        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            bindNormalItem(position, ((ItemViewHolder) holder).newsTitle, ((ItemViewHolder) holder).newsIcon, ((ItemViewHolder) holder).newsContainer);
        } else if (holder instanceof HeadererViewHolder) {
            Uri uri = Uri.parse(themeDailyBgUrl);
            ((HeadererViewHolder) holder).themeDailyImg.setImageURI(uri);

            final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            ((HeadererViewHolder) holder).editorsInfoRv.setLayoutManager(layoutManager);
//            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            editorsAdapter = new EditorsRecyclerAdapter(context, editorsInfoList);
            ((HeadererViewHolder) holder).editorsInfoRv.setAdapter(editorsAdapter);
            editorsAdapter.notifyDataSetChanged();

        }

    }


    @Override
    public int getItemCount() {
        return newsInfoList.size() == 0 ? 0 : newsInfoList.size() + 2;
    }

    private NewsInfo getItem(int position) {
        return newsInfoList.get(position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    class ItemViewHolder extends ViewHolder {

        TextView newsTitle;
        SimpleDraweeView newsIcon;
        LinearLayout newsContainer;

        public ItemViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.frame_main_item_title);
            newsIcon = (SimpleDraweeView) itemView.findViewById(R.id.frame_main_item_icon);
            newsContainer = (LinearLayout) itemView.findViewById(R.id.frame_main_item_container);
        }
    }



    class HeadererViewHolder extends ViewHolder {
        SimpleDraweeView themeDailyImg;
        RecyclerView editorsInfoRv;

//        public void refreshData(List<DailyEditorsInfo> data, int position) {
//            editorsInfoList = data;
//            ViewGroup.LayoutParams layoutParams = editorsInfoRv.getLayoutParams();
//            //高度等于＝条目的高度＋ 10dp的间距 ＋ 10dp（为了让条目居中）
//            layoutParams.height = screenWidth / 3 + dip2px(20);
//            hor_recyclerview.setLayoutParams(layoutParams);
//            hor_recyclerview.setLayoutManager(new LinearLayoutManager(RyRyActivity.this, LinearLayoutManager.HORIZONTAL, false));
//            hor_recyclerview.setBackgroundResource(R.color.colorAccent);
//            hor_recyclerview.setAdapter(new HorizontalAdapter());
//
//        }

        public HeadererViewHolder(View headerView) {
            super(headerView);
            themeDailyImg = (SimpleDraweeView) headerView.findViewById(R.id.themedaily_img);
            editorsInfoRv = (RecyclerView) headerView.findViewById(R.id.editorsInfo_rv);
        }
    }

    class FooterViewHolder extends ViewHolder {

        public FooterViewHolder(View footerView) {
            super(footerView);
        }
    }

    class EditorsRecyclerAdapter extends RecyclerView.Adapter<EditorsRecyclerAdapter.MyViewHolder> {

        private List<DailyEditorsInfo> mDatas;
        private Context mContext;
        private LayoutInflater inflater;

        public EditorsRecyclerAdapter(Context context, List<DailyEditorsInfo> data) {
            this.mContext = context;
            this.mDatas = data;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getItemCount() {

            return mDatas.size();
        }



        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
//            Uri uri = Uri.parse(mDatas.get(position).getAvatar());
            Uri uri = Uri.parse("http://pic4.zhimg.com/068311926_m.jpg");
            holder.editorsAvatarCimg.setImageURI(uri);
            holder.textTv.setText(mDatas.get(position).getId());
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.fragment_dailyeditors_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        class MyViewHolder extends ViewHolder {

            SimpleDraweeView editorsAvatarCimg;
            TextView textTv;

            public MyViewHolder(View view) {
                super(view);
                editorsAvatarCimg = (SimpleDraweeView) view.findViewById(R.id.editors_avatar_img);
                textTv = (TextView) view.findViewById(R.id.texttv);
            }

        }
    }


}
