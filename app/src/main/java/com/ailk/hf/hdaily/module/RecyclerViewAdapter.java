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
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
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
    private List<String> data;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecyclerViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(context);
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

    void bindNormalItem(final int position, TextView newsTitle, LinearLayout newsContainer) {
        if (onItemClickListener != null) {
            newsContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
        newsTitle.setText(getItem(position));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (holder instanceof GroupItemHolder) {
            bindNormalItem(position,((GroupItemHolder) holder).newsTitle,((GroupItemHolder) holder).newsContainer);
            ((GroupItemHolder) holder).newsTime.setText("这是分组标题");
        } else if (holder instanceof ItemViewHolder) {
            bindNormalItem(position, ((ItemViewHolder) holder).newsTitle,((ItemViewHolder) holder).newsContainer);
        } else if (holder instanceof HeadererViewHolder) {
            final List<String> urls = new ArrayList<>();
            urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/1.png");
            urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/2.png");
            urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png");
            ((HeadererViewHolder) holder).banner.setImageLoader(new FrescoImageLoader());
            ((HeadererViewHolder) holder).banner.setImages(urls);
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

    private String getItem(int position) {
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

//            String currentDate = get(position).getPublishDate();
//            int prevIndex = position - 1;
//            boolean isDifferent = !mDataList.get(prevIndex).getPublishDate().equals(currentDate);
            boolean isDifferent = getItem(position).contains("title") ? true : false;
            return isDifferent ? TYPE_GROUPITEM : TYPE_ITEM;
        }
    }

    class ItemViewHolder extends ViewHolder {

        TextView newsTitle;
        ImageView newsIcon;
        LinearLayout newsContainer;

        public ItemViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.frame_main_item_title);
            newsIcon = (ImageView) itemView.findViewById(R.id.frame_main_item_icon);
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
