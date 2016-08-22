package com.phone1000.giftgenius.HttpUtils;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以通过post,get请求使用网络下载获取json数据的类
 *
 *
 * Created by 李田 on 2016/8/13.
 */
public class JsonHttpUtils {
  private  static ExecutorService executor;
    public  static  JsonHttpUtils newInstance(){
        return  new JsonHttpUtils();
    }
    public static void load(String url, Map<String,String> map,IjsonCallback callback){
        //判断当前的excutor是否为空
        if(executor == null){
            //创建一个数量为五的线程池
            executor =  Executors.newFixedThreadPool(5);
        }
        //每调用一次方法就开启一个线程下载Json数据
        executor.execute(new JsonHttpThread(url,map,callback));


    }
  static   class JsonHttpThread implements Runnable{
      private String urlString;
        private Map<String ,String> params;
        private  IjsonCallback callback;
       private Handler handler = new Handler(){
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
               callback.getString((String) msg.obj);
           }
       };
        public JsonHttpThread(String url, Map<String, String> params, IjsonCallback callback) {
            this.urlString = url;
            this.params = params;
            this.callback = callback;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            InputStream  is = null;
            BufferedReader br = null;
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();

                //判断当前的方法为get请求还是POST请求
                /**
                 *当前请为POST请求
                 */
                if( params != null){
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    StringBuffer stringBuffer = new StringBuffer();
                    Set<String>  mapSet = params.keySet();
                    //将参数还有valuel连接起来
                    for(String key:mapSet){
                        String values = params.get(key);
                        stringBuffer.append(key).append("=").append(values).append("&");
                    }
                    String str =getParams(stringBuffer.toString());
                    os.write(str.getBytes());
                    os.flush();
                    os.close();
                 }
                else{
                    conn.setRequestMethod("GET");
                }
               conn.connect();
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    is = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    String str = "";
                    StringBuffer stringBuffer1 = new StringBuffer();
                    while ((str = br.readLine() )!= null){
                        stringBuffer1.append(str);
                    }
                    Message message = Message.obtain();
                   String jsonStrings =  stringBuffer1.toString();
                    message.obj = jsonStrings;
                    handler.sendMessage(message);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                conn.disconnect();
                closeStream(is);
                closeStream(br);
            }
        }


        /**
         * 获取参数的一个方法
         * @param str
         * @return
         */
        public String getParams(String str){

            return  str.substring(0,str.lastIndexOf("&"));
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
