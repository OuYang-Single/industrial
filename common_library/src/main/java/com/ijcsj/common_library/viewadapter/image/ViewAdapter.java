package com.ijcsj.common_library.viewadapter.image;

import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ijcsj.ui_library.utils.ScreenUtils;


public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(ScreenUtils.dp2PxInt(imageView.getContext(), placeholderRes)))
                    .error(com.ijcsj.ui_library.R.mipmap.ic_default_image)
                    .placeholder(com.ijcsj.ui_library.R.mipmap.ic_default_image)
                    .into(imageView);
        }
    }
    @BindingAdapter(value = {"url_void", "placeholderRess"}, requireAll = false)
    public static void setImageUrid(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
          /*  MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(url);
*/
            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(
                            new RequestOptions()
                                    .frame(0)
                                    .centerCrop()
                    )
                    .load(url)
                    .apply(new RequestOptions().placeholder(ScreenUtils.dp2PxInt(imageView.getContext(), placeholderRes)))
                    .error(com.ijcsj.ui_library.R.mipmap.ic_default_image)
                    .placeholder(com.ijcsj.ui_library.R.mipmap.ic_default_image)
                    .into(imageView);
        }
    }
    @BindingAdapter(value = {"urls", "placeholderResd"}, requireAll = false)
    public static void setImageUris(ImageView imageView, String url, int placeholderResd) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(ScreenUtils.dp2PxInt(imageView.getContext(), placeholderResd)))
                    .error(com.ijcsj.ui_library.R.mipmap.ic_default_avatar)
                    .placeholder(com.ijcsj.ui_library.R.mipmap.ic_default_avatar)
                    .into(imageView);
        }
    }
    @BindingAdapter(value = "src")
    public static void setImageUri(ImageView imageView,  int imageSrc) {
        if (imageView!=null) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(imageSrc)
                    .into(imageView);
        }
    }

}