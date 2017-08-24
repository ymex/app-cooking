package cn.ymex.cooking.app;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import cn.ymex.cooking.R;


@GlideModule(glideName = "ImageLoader")
public class ImageLoaderModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.blue_button_background)
                .placeholder(R.drawable.blue_button_background)
                .centerCrop()
                .dontAnimate();
        builder.setDefaultRequestOptions(requestOptions);
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    /**
     * 动画
     *
     * @return
     */
    public static DrawableTransitionOptions drawableCrossFade() {
        return new DrawableTransitionOptions().crossFade();
    }
}
