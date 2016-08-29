package com.phone1000.lazyweekended.presenter;

import com.phone1000.lazyweekended.bean.SecondPageInfo;

/**
 * Created by 李田 on 2016/8/28.
 */
public interface IsecondPresenter {
    void queryList();

    interface SecondCallBack{
        void  success(SecondPageInfo secondPageInfo);
    }
}
