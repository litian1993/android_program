package com.phone1000.lazyweekended.presenter;

import com.phone1000.lazyweekended.bean.FirstPageInfo;

/**
 * Created by 李田 on 2016/8/29.
 */
public interface IitemDetailPre {
    void queryItemDetailInfo(String catagroy,String lat,int cityId);
    interface Callback{
        void success(FirstPageInfo firstPageInfo);
    }
}
