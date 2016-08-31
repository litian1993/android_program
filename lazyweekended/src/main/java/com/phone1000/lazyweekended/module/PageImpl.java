package com.phone1000.lazyweekended.module;

import com.phone1000.lazyweekended.bean.CityListInfo;
import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.bean.SecondPageInfo;
import com.phone1000.lazyweekended.httpwork.HttpUtils;
import com.phone1000.lazyweekended.presenter.IcityInfoPre;
import com.phone1000.lazyweekended.presenter.IitemDetailPre;
import com.phone1000.lazyweekended.presenter.IpagePresenter;
import com.phone1000.lazyweekended.presenter.IsecondPresenter;

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

    /**
     * 获取第二个页面的json数据
     * @param callBack
     */
    @Override
    public void getSecondPageIngo(final IsecondPresenter.SecondCallBack callBack) {
         HttpUtils.create().querySecondPageInfo().
                 observeOn(AndroidSchedulers.mainThread())
                 .subscribeOn(Schedulers.io())
                 .subscribe(new Action1<SecondPageInfo>() {
                     @Override
                     public void call(SecondPageInfo secondPageInfo) {
                          callBack.success(secondPageInfo);
                     }
                 });
    }

    /**
     * 获取第二个页面的item行情的数据
     * @param category
     * @param lat
     * @param cityId
     * @param callback
     */
    @Override
    public void getItemdDetailInfo(String category, String lat, int cityId, final IitemDetailPre.Callback callback) {
          HttpUtils.create().queryItemDetailInfo(category,lat,cityId).
                  observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io()).subscribe(new Action1<FirstPageInfo>() {
              @Override
              public void call(FirstPageInfo firstPageInfo) {
                  callback.success(firstPageInfo);
              }
          });
    }

    /**
     * 获取城市列表信息
     * @param callback
     */
    @Override
    public void queryCityListInfo(final IcityInfoPre.Callback callback) {
        HttpUtils.create().queryCityInfo().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Action1<CityListInfo>() {
            @Override
            public void call(CityListInfo cityListInfo) {
                 callback.success(cityListInfo);
            }
        });
    }


}
