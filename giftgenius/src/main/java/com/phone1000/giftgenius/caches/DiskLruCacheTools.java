package com.phone1000.giftgenius.caches;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;


import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 使用磁盘缓存图片的一个工具类
 * Created by 李田 on 2016/8/13.
 */
public class DiskLruCacheTools {
    private static DiskLruCache diskLruCache ;
    private final static long MAX_SIZE = 10 * 1024 * 1024;

    public  static  void initCache(Context context) {

        try {

                diskLruCache = DiskLruCache.open(getFileDir(context), getVersionCode(context), 1, MAX_SIZE);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取versionCode
     *
     * @return
     */
    private static int getVersionCode(Context mContext) {
        String packageName = mContext.getPackageName();
        try {
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取存储路径的方法
     *
     * @param mContext
     * @return
     */
    private static File getFileDir(Context mContext) {
        //判断当前的内存卡是否挂载,如果是返回当前外置存储的路径
        if (Environment.isExternalStorageRemovable() || Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return mContext.getExternalCacheDir();
        } else {
            //如果没有就返回当前的内置存储
            return mContext.getCacheDir();
        }
    }

    /**
     * 保存图片设置缓存的方法
     *
     * @param url
     * @param bitmap
     */
    public static void saveDiskCache(String url, Bitmap bitmap) {
        String key = formatKeys(url);
        OutputStream os = null;
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            if (editor != null) {
                os = editor.newOutputStream(0);
                /**
                 * 三个参数分别是
                 * 1.bitmap的保存格式
                 * 2.图像的质量，0到100,数字越大，表示保存的质量越好
                 * 3.写入的流
                 */
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                if (compress) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将url加密的方法
     *
     * @param url
     * @return
     */
    private static String formatKeys(String url) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                //转换为十六进制
                stringBuilder.append(Integer.toHexString(Math.abs(bytes[i])));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(url.hashCode());
    }

    /**
     * 设置读取缓存中的图片的方法
     */
    public static Bitmap readDiskCaches(String url) {
        String key = formatKeys(url);
        InputStream is = null;
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if (snapshot != null) {
                is = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(is);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void flush(){
        if(diskLruCache != null){
            try {
                diskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
