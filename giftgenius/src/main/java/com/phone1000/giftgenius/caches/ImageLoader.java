package com.phone1000.giftgenius.caches;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.phone1000.giftgenius.HttpUtils.BitmapHttpUtils;

/**
 * 读取缓存的一个方法
 * Created by 李田 on 2016/8/14.
 */
public class ImageLoader {
    private static ImageLoader imageLoader;
    public static ImageLoader initLoader(Context context){
       DiskLruCacheTools.initCache(context);
        if(imageLoader == null){
             imageLoader = new ImageLoader();
        }
        return  imageLoader;
    }

    /**
     * 加载图片读取缓存的方法，当内存中有图片时，就将当前的图片设置到ImageView中，如果没有就去存储卡中找，如果有就
     * 将当前的图片设置到ImageView,并将图片写入到内存中，如果没有就开启网络下载，并将图片保存到存储卡中
     * @param url
     * @param imageView
     */
    public   void setImage(String url,String path,ImageView imageView){
        Bitmap bitmap  = LrucacheTools.readMenmoryCache(url+path);
        if(bitmap != null){
            //当前的内存缓存中有图片
            imageView.setImageBitmap(bitmap);
        }
        else{

                BitmapHttpUtils.load(url,path,imageView);
            }
            DiskLruCacheTools.flush();

    }
}
