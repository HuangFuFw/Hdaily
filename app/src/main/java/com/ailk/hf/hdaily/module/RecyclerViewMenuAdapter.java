package com.ailk.hf.hdaily.module;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ailk.hf.hdaily.R;
import com.ailk.hf.hdaily.model.ThemesDaily;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


/**
 * Created by huangfu on 2016/12/26 14.:32
 */
public class RecyclerViewMenuAdapter extends Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 2;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<ThemesDaily> data;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onHeaderItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecyclerViewMenuAdapter(Context context, List<ThemesDaily> data) {
        this.context = context;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_menu_header, parent, false);
            return new HeadererViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.fragment_item_menu, parent, false);
            return new ItemViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof HeadererViewHolder){
            ((HeadererViewHolder)holder).rlMenuHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onHeaderItemClick(v,position-1);
                }
            });
        }else {
            ((ItemViewHolder)holder).txtDrawerItemTitle.setText(getItem(position).getName());
            ((ItemViewHolder)holder).txtDrawerItemTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position-1);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    private ThemesDaily getItem(int position) {
        return data.get(position - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    class ItemViewHolder extends ViewHolder {

        TextView txtDrawerItemTitle;
        ImageButton ibtnDrawerItemFocus;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtDrawerItemTitle = (TextView) itemView.findViewById(R.id.txt_drawer_item_title);
            ibtnDrawerItemFocus = (ImageButton) itemView.findViewById(R.id.ibtn_drawer_item_focus);
            itemView.findViewById(R.id.ibtn_drawer_item_focus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    class HeadererViewHolder extends ViewHolder {

        RelativeLayout rlMenuHeader;

        public HeadererViewHolder(View headerView) {
            super(headerView);
            rlMenuHeader = (RelativeLayout) headerView.findViewById(R.id.rl_menu_header);
            itemView.findViewById(R.id.rl_menu_header).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
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
