package com.phone1000.lazyweekended.httpwork;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李田 on 2016/8/26.
 */
public class HttpUtils {
    private static HttpService httpService;
   public  static  final  String BASE_URL = "http://api.lanrenzhoumo.com";
    public static HttpService create(){
        if(httpService == null){
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                    build();
            httpService = retrofit.create(HttpService.class);
        }
        return  httpService;
    }
}
