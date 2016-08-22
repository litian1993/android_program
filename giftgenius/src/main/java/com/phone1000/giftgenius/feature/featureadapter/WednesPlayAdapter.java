package com.phone1000.giftgenius.feature.featureadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.feature.featureinfo.FeatureUrl;
import com.phone1000.giftgenius.feature.featureinfo.Wendnesplayinfo;

import java.util.List;

/**
 * Created by 李田 on 2016/8/16.
 */
public class WednesPlayAdapter extends BaseAdapter {
    private Context mContext;
    private List<Wendnesplayinfo.ListBean>  listBeen;
    private ImageLoader imageLoader;
    public WednesPlayAdapter(Context mContext, List<Wendnesplayinfo.ListBean> listBeen) {
        this.mContext = mContext;
        this.listBeen = listBeen;
        imageLoader = ImageLoader.initLoader(mContext);
    }

    @Override
    public int getCount() {
        return listBeen == null?0:listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View view = convertView;
        WenViewHolder viewHolder = null;
        if( view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.wednesplay_list_view,parent,false);
            viewHolder = new WenViewHolder(view);
        }{
            viewHolder = (WenViewHolder) view.getTag();
        }
        Wendnesplayinfo.ListBean bean = listBeen.get(position);
        viewHolder.nameTv.setText(bean.getName());
        viewHolder.imageView.setTag(FeatureUrl.IMAGE_URL_HEAD+bean.getIconurl());
        viewHolder.timeTv.setText(bean.getAddtime());
        viewHolder.imageView.setImageResource(R.drawable.def_loading);
        imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD,bean.getIconurl(),viewHolder.imageView);
        return view;
    }
    class  WenViewHolder{
        public ImageView imageView;
        public TextView nameTv,timeTv;
        public WenViewHolder(View view){
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.wendesday_list_image);
             nameTv = (TextView) view.findViewById(R.id.wendesday_list_name);
            timeTv = (TextView) view.findViewById(R.id.wendesday_list_addtime);
        }
    }
}
