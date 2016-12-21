package com.ailk.hf.hdaily.module;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangfu on 2016/12/19 14.:32
 */
public class RecyclerViewAdapter extends Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;
    private Context context;
    private List data;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecyclerViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_news, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_footer, parent,
                    false);
            return new FooterViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_header, parent,
                    false);
            return new HeadererViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
            ((ItemViewHolder) holder).tvTitle.setText(data.get(position - 1).toString());
        } else if (holder instanceof HeadererViewHolder) {

            final List<String> urls = new ArrayList<>();
            urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/1.png");
            urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/2.png");
            urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png");

            ((HeadererViewHolder) holder).banner.setImageLoader(new FrescoImageLoader());
            ((HeadererViewHolder) holder).banner.setImages(urls);
            ((HeadererViewHolder) holder).banner.start();
        }

    }


    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 2;
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

    static class ItemViewHolder extends ViewHolder {

        TextView tvTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    static class HeadererViewHolder extends ViewHolder {

        Banner banner;

        public HeadererViewHolder(View headerView) {
            super(headerView);
            banner = (Banner) headerView.findViewById(R.id.banner);
        }
    }

    static class FooterViewHolder extends ViewHolder {

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
