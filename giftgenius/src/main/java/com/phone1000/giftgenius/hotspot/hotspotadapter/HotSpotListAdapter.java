package com.phone1000.giftgenius.hotspot.hotspotadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.hotspot.hotspotbean.HotSpotInfo;

import java.util.List;

/**
 * Created by 李田 on 2016/8/18.
 */
public class HotSpotListAdapter extends BaseAdapter {
    private List<HotSpotInfo.InfoBean.Push1Bean> push1Been;
    private Context mContext;
    private ImageLoader imageLoader;

    public HotSpotListAdapter(List<HotSpotInfo.InfoBean.Push1Bean> push1Been, Context mContext) {
        this.push1Been = push1Been;
        this.mContext = mContext;
        imageLoader = ImageLoader.initLoader(mContext);
    }

    @Override
    public int getCount() {
        return push1Been == null?0:push1Been.size();
    }

    @Override
    public Object getItem(int position) {
        return push1Been.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ListViewHolder viewHolder = null;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.remen_fragement_listview,parent,false);
          viewHolder = new ListViewHolder(view);
        }
        else{
            viewHolder = (ListViewHolder) view.getTag();
        }
        HotSpotInfo.InfoBean.Push1Bean bean = push1Been.get(position);
        viewHolder.gameTv.setText(bean.getName());
        viewHolder.numberTv.setText(bean.getClicks()+"");
        viewHolder.kindTv.setText(bean.getTypename());
        if(bean.getSize() != null){
            viewHolder.sizeTv.setText(bean.getSize());
        }
        else{
            viewHolder.sizeTv.setText("不大");
        }
    viewHolder.listPic.setTag(PackageUrl.MAIN_PATH+bean.getLogo());
        imageLoader.setImage(PackageUrl.MAIN_PATH,bean.getLogo(),viewHolder.listPic);
        return view;
    }
    class ListViewHolder{
        ImageView listPic;
       TextView gameTv,kindTv,sizeTv,numberTv;
        public ListViewHolder(View view){
            view.setTag(this);
            listPic = (ImageView) view.findViewById(R.id.hotspot_game_pic);
             gameTv = (TextView) view.findViewById(R.id.hotspot_game_name);
             kindTv = (TextView) view.findViewById(R.id.hotspot_game_type);
              sizeTv = (TextView) view.findViewById(R.id.hotspot_game_gamesize);
              numberTv = (TextView) view.findViewById(R.id.hotspot_show_number);
        }

    }
}
