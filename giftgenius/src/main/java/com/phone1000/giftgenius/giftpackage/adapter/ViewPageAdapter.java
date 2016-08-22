package com.phone1000.giftgenius.giftpackage.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.giftpackage.packageInfo.PackageInfo;

import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class ViewPageAdapter extends PagerAdapter {
    private List<PackageInfo.AdBean> adBeanList;
    private Context mContext;
    private ImageLoader imageLoader;

    public ViewPageAdapter(List<PackageInfo.AdBean> adBeanList, Context mContext) {
        this.adBeanList = adBeanList;
        this.mContext = mContext;
        imageLoader =  ImageLoader.initLoader(mContext);
    }

    @Override
    public int getCount() {
        return adBeanList == null? 0:adBeanList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        PackageInfo.AdBean  adBean = adBeanList.get(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String url = PackageUrl.PACKAGE_URL.substring(0,PackageUrl.PACKAGE_URL.lastIndexOf("/"));
       imageView.setTag(PackageUrl.MAIN_PATH+adBean.getIconurl());
        imageLoader.setImage(url,adBean.getIconurl(),imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
