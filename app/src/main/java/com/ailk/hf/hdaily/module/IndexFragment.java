package com.ailk.hf.hdaily.module;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ailk.hf.hdaily.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by huangfu on 2016/12/16 15.:23
 */
public class IndexFragment extends Fragment {

    @Bind(R.id.banner)
    Banner banner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ButterKnife.bind(this, view);
        initBanner();
        return view;
    }

    private void initBanner() {
        final List<String> urls = new ArrayList<>();
        urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/1.png");
        urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/2.png");
        urls.add("https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png");

        banner.setImageLoader(new FrescoImageLoader());
        banner.setImages(urls);
        banner.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
