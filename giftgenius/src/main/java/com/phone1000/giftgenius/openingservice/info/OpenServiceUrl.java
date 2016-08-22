package com.phone1000.giftgenius.openingservice.info;

/**
 * Created by 李田 on 2016/8/15.
 */
public class OpenServiceUrl {
    public  static  final  String OPENSERVICE_URL = "http://www.1688wan.com/majax.action?method=getJtkaifu";
    public static final  String OPENCHECK_URL = "http://www.1688wan.com/majax.action?method=getWebfutureTest";
     public  static  final String OPENSERVICE_DETAIL_URL="http://www.1688wan.com/majax.action?method=getAppInfo";
    public  static  final  String IMAGE_HEAD_URL ="http://www.1688wan.com";
    public  static  String getImageUrl(String url){
       return  url.substring(0,url.lastIndexOf("/"));
   }
}
