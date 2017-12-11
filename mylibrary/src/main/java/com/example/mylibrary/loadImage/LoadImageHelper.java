//package com.example.mylibrary.loadImage;
//
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.target.Target;
//
//import java.io.File;
//
//
///**
// * 类描述：加载图片的帮助类
// * 创建人 Chris
// * 创建时间：2017/5/5 9:31
// */
//
//public class LoadImageHelper {
//    /**
//     * 加载图片
//     * @param view
//     * @param file      加载本地文件
//     */
//    public  static <T extends ImageView> void loadImage(T view, File file) {
//        Glide.with(App.getmApplicationContext())
//                .load(file)
//                .placeholder(R.drawable.load_fail)
//                .error(R.drawable.load_fail)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(view);
//    }
//
//    /**
//     *
//     * @param view
//     * @param url       加载网络url
//     */
//    public static <T extends ImageView> void loadImage(T view, String url) {
//        getCommomonConfig(url,view);
//    }
//
//    /**
//     * 通用配置
//     * @param obj
//     * @param view
//     * @param <V>
//     * @return
//     */
//    public static <V extends ImageView> Target<GlideDrawable> getCommomonConfig(Object obj,V view) {
//        Target<GlideDrawable> into = Glide.with(App.getmApplicationContext())
//                .load(obj)
//                .placeholder(R.drawable.load_fail)
//                .error(R.drawable.load_fail)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .fitCenter()
//                .into(view);
//        return into;
//    }
//
//}
