package com.phone1000.giftgenius.giftpackage;

/**
 * Created by 李田 on 2016/8/15.
 */
public class PackageUrl {
    public static final  String PACKAGE_URL = "http://www.1688wan.com/majax.action?method=getGiftList";
     public static final  String PACKAGE_DETAIL = "http://www.1688wan.com/majax.action?method=getGiftInfo";
     public static final String MAIN_PATH = "http://www.1688wan.com";
    public static String  getImageUrl(String urlstring){
        String str = urlstring.substring(0,urlstring.lastIndexOf("/"));
        return  str;
    }

}
