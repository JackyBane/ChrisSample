package com.example.mylibrary.cacheFileHelper;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 类描述：内存缓存
 * 创建人 Chris
 * 创建时间：2017/5/23 18:48
 */

public class MemoryCacheUtils {
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory();//获取虚拟机分配的最大内存,默认16MB
        System.out.println("maxMemory:" + maxMemory);
        //maxSize:内存缓存上限
        mLruCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                //计算图片占用内存大小
                int byteCount = value.getRowBytes() * value.getHeight();
                return byteCount;
            }
        };
    }

    //写缓存
    public void setMemoryCache(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }

    //读缓存
    public Bitmap getMemroyCache(String url) {
        return mLruCache.get(url);
    }
}
