package com.phone1000.lazyweekended.presenter.impl;

import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.module.PageImpl;
import com.phone1000.lazyweekended.presenter.IitemDetailPre;
import com.phone1000.lazyweekended.view.IpageView;

/**
 * Created by LiT on 2016/8/29.
 */
public class IitemDeailImpl implements IitemDetailPre,IitemDetailPre.Callback {
    /**
     * 获取第二个页面item的行情
     */
  private PageImpl mIpageImpl;
    private IpageView ipageView;

    public IitemDeailImpl(IpageView ipageView) {
        this.ipageView = ipageView;
        mIpageImpl = new PageImpl();
    }



    @Override
    public void success(FirstPageInfo firstPageInfo) {

        ipageView.refreshListView(firstPageInfo);
    }


    @Override
    public void queryItemDetailInfo(String catagroy, String lat, int cityId) {
           mIpageImpl.getItemdDetailInfo(catagroy,lat,cityId,this);
    }
}
