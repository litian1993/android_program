package com.phone1000.lazyweekended.presenter;

import com.phone1000.lazyweekended.bean.SecondPageInfo;
import com.phone1000.lazyweekended.module.PageImpl;
import com.phone1000.lazyweekended.view.SecondPageView;

/**
 * Created by 李田 on 2016/8/28.
 */
public class SeondPresenterImpl implements IsecondPresenter,IsecondPresenter.SecondCallBack {
   private SecondPageView  mSecondPageView;
    private PageImpl  mPageImpl;

    public SeondPresenterImpl(SecondPageView mSecondPageView) {
        this.mSecondPageView = mSecondPageView;
        this.mPageImpl = new PageImpl();
    }

    @Override
    public void queryList() {
        mPageImpl.getSecondPageIngo(this);

    }

    @Override
    public void success(SecondPageInfo secondPageInfo) {
          mSecondPageView.getSecondPageInfo(secondPageInfo);
    }
}
