package com.phone1000.lazyweekended.presenter.impl;

import com.phone1000.lazyweekended.bean.CityListInfo;
import com.phone1000.lazyweekended.module.PageImpl;
import com.phone1000.lazyweekended.presenter.IcityInfoPre;
import com.phone1000.lazyweekended.view.IcityInfoView;

/**
 * Created by 李田 on 2016/8/31.
 */
public class IcityInfoImpl implements IcityInfoPre,IcityInfoPre.Callback{
    private PageImpl mPageImpl;
    private IcityInfoView mIcityInfoView;

    public IcityInfoImpl(IcityInfoView mIcityInfoView) {
        this.mIcityInfoView = mIcityInfoView;
        mPageImpl = new PageImpl();
    }

    @Override
    public void queryCityInfo() {
        mPageImpl.queryCityListInfo(this);
    }

    @Override
    public void success(CityListInfo cityListInfo) {
        mIcityInfoView.getCityListInfo(cityListInfo);
    }
}
