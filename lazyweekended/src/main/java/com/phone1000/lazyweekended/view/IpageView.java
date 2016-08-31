package com.phone1000.lazyweekended.view;

import com.phone1000.lazyweekended.bean.FirstPageInfo;

/**
 * Created by 李田 on 2016/8/27.
 */
public interface IpageView {
    /**
     * 获取第一个页面的数据的接口
     * @param firstPageInfo
     */
    void refreshListView(FirstPageInfo firstPageInfo);
}
