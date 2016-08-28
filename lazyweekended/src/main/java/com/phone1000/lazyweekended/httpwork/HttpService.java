package com.phone1000.lazyweekended.httpwork;

import com.phone1000.lazyweekended.bean.FirstPageInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 李田 on 2016/8/26.
 */
public interface HttpService{


    @GET("/main/recommend/index?v=3&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&lat=30.575388756810078")
    Observable<FirstPageInfo> queryPageInfo() ;

}
