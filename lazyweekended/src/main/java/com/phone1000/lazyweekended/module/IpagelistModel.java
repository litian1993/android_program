package com.phone1000.lazyweekended.module;

import com.phone1000.lazyweekended.presenter.IcityInfoPre;
import com.phone1000.lazyweekended.presenter.IitemDetailPre;
import com.phone1000.lazyweekended.presenter.IpagePresenter;
import com.phone1000.lazyweekended.presenter.IsecondPresenter;

/**
 * Created by 李田 on 2016/8/27.
 */
public interface IpagelistModel {
    /**
     * 使用get获取数据的方法
     *
     */
    void getListInfo(IpagePresenter.Callback callback);
    void getSecondPageIngo(IsecondPresenter.SecondCallBack callBack);
    void getItemdDetailInfo(String category, String lat,int cityId, IitemDetailPre.Callback callback);
    /**
     * 获取城市列表的方法
     */
    void queryCityListInfo(IcityInfoPre.Callback callback);
}
