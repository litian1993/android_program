package com.phone1000.lazyweekended.presenter;

import com.phone1000.lazyweekended.bean.CityListInfo;

/**
 * Created by 李田 on 2016/8/31.
 */
public interface IcityInfoPre {
    /**
     * 获取城市列表信息的接口
     */
    void queryCityInfo();
    interface Callback{
        void success(CityListInfo cityListInfo);
    }
}
