package com.phone1000.giftgenius.giftpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.giftpackage.packageInfo.PackageInfo;

import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class ViewHolder {
    private View view;
    private  List<PackageInfo.ListBean> listBeanList;
   public  TextView  giftNameTv, giftKindTv,giftNumberTv,giftDateTv;
   public   TextView getBtn;
    public    ImageView imageView;
    public ViewHolder(View view, List<PackageInfo.ListBean> listBeen) {
        this.view = view;
        this.listBeanList = listBeen;
        giftNameTv = (TextView) view.findViewById(R.id.list_gift_name);
        giftKindTv = (TextView) view.findViewById(R.id.list_gift_kind);
        giftNumberTv = (TextView) view.findViewById(R.id.list_gift_number);
        giftDateTv = (TextView) view.findViewById(R.id.list_gift_addtime);
        imageView = (ImageView) view.findViewById(R.id.list_pic);
        getBtn = (TextView) view.findViewById(R.id.list_download_btn);
    }

}
