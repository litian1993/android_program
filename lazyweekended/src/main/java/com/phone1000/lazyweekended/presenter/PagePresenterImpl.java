package com.phone1000.lazyweekended.presenter;

import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.module.PageImpl;
import com.phone1000.lazyweekended.view.IpageView;

/**
 * Created by 李田 on 2016/8/27.
 */
public class PagePresenterImpl implements IpagePresenter,IpagePresenter.Callback  {
    PageImpl mImodel;
    IpageView mIpageView;

    public PagePresenterImpl(IpageView mIpageView) {
        this.mIpageView = mIpageView;
        mImodel = new PageImpl();
    }

    /**
     * 获取首页list的数据
     */
    @Override
    public void queryList() {

        mImodel.getListInfo(this);
    }

    /**
     * 接口回调获取数据
     * @param firstPageInfo
     */
    @Override
    public void success(FirstPageInfo firstPageInfo) {

        mIpageView.refreshListView(firstPageInfo);
    }
}
