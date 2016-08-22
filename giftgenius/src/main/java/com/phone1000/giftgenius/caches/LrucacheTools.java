package com.phone1000.giftgenius.caches;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 使用内存缓存图片的一个类
 * Created by 李田 on 2016/8/14.
 */
public class LrucacheTools  {
    private static final int  MAX_SIZE = 4*1024*1024;
    private  static LruCache<String ,Bitmap> lruCache = new LruCache<String, Bitmap>(MAX_SIZE){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };

    /**
     * 存储图片的一个方法
     * @param url
     * @param bitmap
     */
    public static void saveMenmoryCache(String url,Bitmap bitmap){
        lruCache.put(url,bitmap);
    }
    /**
     * 读取图片的一个方法
     */
    public static Bitmap readMenmoryCache(String url){
        return lruCache.get(url);
    }

}
