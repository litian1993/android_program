package com.phone1000.lazyweekended.module;

import com.phone1000.lazyweekended.presenter.IpagePresenter;

/**
 * Created by 李田 on 2016/8/27.
 */
public interface IpagelistModel {
    /**
     * 使用get获取数据的方法
     *
     */
    void getListInfo(IpagePresenter.Callback callback);


}
