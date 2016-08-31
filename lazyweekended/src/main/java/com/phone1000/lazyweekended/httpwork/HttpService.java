package com.phone1000.lazyweekended.httpwork;

import com.phone1000.lazyweekended.bean.CityListInfo;
import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.bean.SecondPageInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 李田 on 2016/8/26.
 */
public interface HttpService{


    @GET("/main/recommend/index?v=3&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91&lon=114.30963859310197&page=1&lat=30.575388756810078")
    Observable<FirstPageInfo> queryPageInfo() ;

    @GET("/wh/common/cats?v=2&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91")
    Observable<SecondPageInfo>  querySecondPageInfo();
    @GET("/wh/common/leos?v=2&session_id=000040a3fb7d64ce1737c6c7bb3co7e4e157c91&lon=114.30963859310197&page=1")
    Observable<FirstPageInfo>  queryItemDetailInfo(@Query("category") String category,@Query("lat") String lat,@Query("city_id")
    int  city_id);
    //获取城市列表
   @GET("/district/list/allcity?session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91")
    Observable<CityListInfo>  queryCityInfo();
}
