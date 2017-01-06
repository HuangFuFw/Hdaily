package com.ailk.hf.hdaily.module;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.model.NewsInfo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangfu on 2016/12/19 14.:32
 */
public class RecyclerViewAdapter extends Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_GROUPITEM = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_FOOTER = 3;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<NewsInfo> data;
    private List<NewsInfo> topData;
//    private List<String> dates = new ArrayList<>();
//    private String currentDate;
//    private String prevDate;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecyclerViewAdapter(Context context, List<NewsInfo> data) {
        this.context = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

//    public void putDate(String date, boolean isFirst) {
//        this.currentDate = date;
//        dates.add(date);
//        if (isFirst) {
//            this.prevDate = currentDate;
//        }
//    }

    public void setTopData(List<NewsInfo> topData) {
        this.topData = topData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_news, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_GROUPITEM) {
            View view = mLayoutInflater.inflate(R.layout.fragment_groupitem_news, parent,
                    false);
            return new GroupItemHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_footer, parent,
                    false);
            return new FooterViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_header, parent,
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
        newsIcon.setImageURI(getItem(position).getImages().get(0));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof GroupItemHolder) {
            bindNormalItem(position, ((GroupItemHolder) holder).newsTitle, ((ItemViewHolder) holder).newsIcon, ((GroupItemHolder) holder).newsContainer);
            ((GroupItemHolder) holder).newsTime.setText(getItem(position).getDate());
        } else if (holder instanceof ItemViewHolder) {
            bindNormalItem(position, ((ItemViewHolder) holder).newsTitle, ((ItemViewHolder) holder).newsIcon, ((ItemViewHolder) holder).newsContainer);
        } else if (holder instanceof HeadererViewHolder) {
            final List<String> urls = new ArrayList<>();
            final List<String> titles = new ArrayList<>();
            for (int i = 0; i < topData.size(); i++) {
                urls.add(topData.get(i).getImage());
                titles.add(topData.get(i).getTitle());
            }
            ((HeadererViewHolder) holder).banner.setImageLoader(new FrescoImageLoader());
            ((HeadererViewHolder) holder).banner.setImages(urls);
            ((HeadererViewHolder) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            ((HeadererViewHolder) holder).banner.setBannerTitles(titles);
            ((HeadererViewHolder) holder).banner.start();
            ((HeadererViewHolder) holder).banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {


                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 2;
    }

    private NewsInfo getItem(int position) {
        return data.get(position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (position == 0) {
            return TYPE_HEADER;
        } else {
            if (position == 1)
                return TYPE_GROUPITEM;

            String currentDate = getItem(position).getDate();
            int prevIndex = position - 1;
            boolean isAddGroup = !getItem(prevIndex).getDate().equals(currentDate);
            return isAddGroup ? TYPE_GROUPITEM : TYPE_ITEM;
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

    class GroupItemHolder extends ItemViewHolder {
        TextView newsTime;

        public GroupItemHolder(View itemView) {
            super(itemView);
            newsTime = (TextView) itemView.findViewById(R.id.frame_main_group_item_time);
        }
    }

    class HeadererViewHolder extends ViewHolder {
        Banner banner;

        public HeadererViewHolder(View headerView) {
            super(headerView);
            banner = (Banner) headerView.findViewById(R.id.banner);
        }
    }

    class FooterViewHolder extends ViewHolder {

        public FooterViewHolder(View footerView) {
            super(footerView);
        }
    }

    class FrescoImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //用fresco加载图片
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }
}
