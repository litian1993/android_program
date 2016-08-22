package com.phone1000.giftgenius.openingservice;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.giftgenius.R;

/**
 * Created by 李田 on 2016/8/15.
 */
public class ServiceViewHolder {
    public ImageView imageView;
    public TextView gNameTv,startTimeTv,areaTv,ownerTv;
    public TextView checkBtn;
    public ServiceViewHolder(View view){
        gNameTv = (TextView) view.findViewById(R.id.kaifu_list_gamename);
        startTimeTv = (TextView) view.findViewById(R.id.kaifu_list_starttime);
        areaTv = (TextView) view.findViewById(R.id.kaifu_list_area);
        ownerTv  = (TextView) view.findViewById(R.id.kaifu_list_gameowner);
        checkBtn = (TextView) view.findViewById(R.id.kaifu_kaifu_check);
        imageView = (ImageView) view.findViewById(R.id.kaifu_list_pic);
    }
}
