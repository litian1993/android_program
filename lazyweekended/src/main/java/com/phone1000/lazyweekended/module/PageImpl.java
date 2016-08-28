package com.phone1000.lazyweekended.module;

import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.httpwork.HttpUtils;
import com.phone1000.lazyweekended.presenter.IpagePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 李田 on 2016/8/27.
 */
public class PageImpl implements IpagelistModel {


    @Override
    public void getListInfo(final IpagePresenter.Callback callback) {
        HttpUtils.create().queryPageInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Action1<FirstPageInfo>() {
            @Override
            public void call(FirstPageInfo firstPageInfo) {
                callback.success(firstPageInfo);
            }
        });
    }
}
