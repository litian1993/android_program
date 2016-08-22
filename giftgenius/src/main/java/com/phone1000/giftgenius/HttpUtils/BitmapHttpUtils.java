package com.phone1000.giftgenius.HttpUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.phone1000.giftgenius.caches.DiskLruCacheTools;
import com.phone1000.giftgenius.caches.LrucacheTools;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可以开启多线程下载图片的类
 * Created by 李田 on 2016/8/13.
 */
public class BitmapHttpUtils  {
    private static ExecutorService executor;
    public  static void load(String url, String path, ImageView view) {
        //判断当前的excutor是否为空
        if (executor == null) {
            //创建一个数量为五的线程池
            executor = Executors.newFixedThreadPool(5);
        }
        //每调用一次方法就开启一个线程下载Json数据
        executor.execute(new BitmapHttpThread(url,path,view));
    }

      static   class BitmapHttpThread implements Runnable {
            private String urlString;
            private String path;
            private ImageView imageView;

            public BitmapHttpThread(String urlString, String path, ImageView imageView) {
                this.urlString = urlString;
                this.path = path;
                this.imageView = imageView;
            }

            private Handler mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (imageView.getTag().equals(urlString+path)) {
                        Bitmap bitmap = (Bitmap) msg.obj;
                        imageView.setImageBitmap(bitmap);
                    }
                }
            };

            @Override
            public void run() {
                Bitmap diskBitmap = DiskLruCacheTools.readDiskCaches(urlString+path);
                if (diskBitmap != null) {
                    //如果内存中读取到了图片
                   LrucacheTools.saveMenmoryCache(urlString+path,diskBitmap);
                    Message message = mHandler.obtainMessage();
                    message.obj = diskBitmap;
                    mHandler.sendMessage(message);
                    return;
                }
                HttpURLConnection conn = null;
                InputStream is = null;
                ByteArrayOutputStream baos = null;
                try {
                    URL  url = new URL(urlString+path);
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.connect();

                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        is = conn.getInputStream();
                        baos = new ByteArrayOutputStream();

                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while((len = is.read(bytes)) != -1 ){
                            baos.write(bytes,0,len);
                            baos.flush();
                        }
                        byte[] bytes1 = baos.toByteArray();
                        //二次采样
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeByteArray(bytes1,0,bytes1.length);
                        //按比例压缩图片
                        int height = options.outHeight;
                        int width = options.outWidth;
                        int radio = height/100;
                        if(radio >= 1){
                            options.inSampleSize = radio;
                        }
                        options.inJustDecodeBounds = false;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes1,0,bytes1.length);
                        DiskLruCacheTools.saveDiskCache(url+path,bitmap);
                        Message message = Message.obtain();
                        message.obj = bitmap;
                        mHandler.sendMessage(message);

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    closeStream(is);
                    closeStream(baos);
                }
            }
          /**
           * 关闭流的方法
           */
          public void closeStream(Closeable stream){
              if(stream != null){
                  try {
                      stream.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
        }

    }

