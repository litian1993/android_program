package com.phone1000.lazyweekended.presenter;

import com.phone1000.lazyweekended.bean.FirstPageInfo;

/**
 * Created by 李田 on 2016/8/27.
 */
public interface IpagePresenter {


    void queryList();

    /**
     * 用于获取数据的接口
     */
    interface Callback{
        void success(FirstPageInfo firstPageInfo);
    }

}
