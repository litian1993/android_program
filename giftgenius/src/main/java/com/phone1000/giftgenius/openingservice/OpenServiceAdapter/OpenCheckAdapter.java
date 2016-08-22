package com.phone1000.giftgenius.openingservice.OpenServiceAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.openingservice.CheckViewHolder;
import com.phone1000.giftgenius.openingservice.info.OpenCheckInfo;
import com.phone1000.giftgenius.openingservice.info.OpenServiceUrl;

import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class OpenCheckAdapter extends BaseAdapter {
    private Context mContext;
    private List<OpenCheckInfo.InfoBean> infoBeenList;
    private ImageLoader imageLoader;

    public OpenCheckAdapter(Context mContext, List<OpenCheckInfo.InfoBean> infoBeenList) {
        this.mContext = mContext;
        this.infoBeenList = infoBeenList;
       imageLoader =  ImageLoader.initLoader(mContext);
    }

    @Override
    public int getCount() {
        return infoBeenList == null? 0:infoBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view = convertView;
        CheckViewHolder viewHolder = null;
        if(view  == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.kaifu_kaice_listview,parent,false);
            viewHolder = new CheckViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (CheckViewHolder) view.getTag();
        }
        OpenCheckInfo.InfoBean infoBean = infoBeenList.get(position);
        viewHolder.gameNameTv.setText(infoBean.getGname());
        viewHolder.gameOwnerTv.setText(infoBean.getOperators());
        viewHolder.startTimeTv.setText(infoBean.getTeststarttime());
        viewHolder.imageView.setTag(PackageUrl.MAIN_PATH+infoBean.getIconurl());
        imageLoader.setImage(OpenServiceUrl.getImageUrl(OpenServiceUrl.OPENCHECK_URL),infoBean.getIconurl(),viewHolder.imageView);
        return view;
    }
}
