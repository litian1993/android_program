package com.phone1000.giftgenius.openingservice;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.giftgenius.R;

/**
 * Created by 李田 on 2016/8/15.
 */
public class CheckViewHolder {
    private View view;
   public ImageView imageView;
    public TextView gameNameTv,gameOwnerTv,startTimeTv;
   public TextView checkBtn;
    public CheckViewHolder(View view) {
        this.view = view;
        imageView  = (ImageView) view.findViewById(R.id.kaice_list_pic);
        gameNameTv = (TextView) view.findViewById(R.id.kaice_list_gamename);
        gameOwnerTv  = (TextView) view.findViewById(R.id.kaice_list_gameowner);
        startTimeTv = (TextView) view.findViewById(R.id.kaice_list_starttime);
        checkBtn = (TextView) view.findViewById(R.id.kaifu_kaice_check);

    }
}
