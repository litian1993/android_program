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
import com.phone1000.giftgenius.feature.featureinfo.WeekPlayInfo;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李田 on 2016/8/16.
 */
public class WeekPlayAdapter extends BaseAdapter {
    private Context mContext;
    private List<WeekPlayInfo.ListBean> beanList ;
    private ImageLoader imageLoader;

    public WeekPlayAdapter(Context mContext, List<WeekPlayInfo.ListBean> beanList) {
        this.mContext = mContext;
        this.beanList = beanList;
        imageLoader = ImageLoader.initLoader(mContext);
    }

    @Override
    public int getCount() {
        return beanList == null?0:beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        WeekViewHolder viewHolder = null;
        if( convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.weekplay_list_view,parent,false);
            viewHolder = new WeekViewHolder(view);
        }
        else{
          viewHolder = (WeekViewHolder) view.getTag();
        }
        WeekPlayInfo.ListBean bean = beanList.get(position);
         viewHolder.textView.setText(bean.getName());
        viewHolder.imageView.setTag(FeatureUrl.IMAGE_URL_HEAD+bean.getIconurl());
         viewHolder.imageView.setImageResource(R.drawable.def_loading);
        viewHolder.circleImageView.setImageResource(R.drawable.def_loading);
        viewHolder.circleImageView.setTag(FeatureUrl.IMAGE_URL_HEAD+FeatureUrl.IMAGE_PHOTO_URL_);
        imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD,bean.getIconurl(),viewHolder.imageView);
        imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD,FeatureUrl.IMAGE_PHOTO_URL_,viewHolder.circleImageView);
        return view;
    }
    class  WeekViewHolder{
      public   ImageView imageView ;
       public CircleImageView circleImageView;
      public   TextView textView ;
        public WeekViewHolder(View view){
            view.setTag(this);
            imageView = (ImageView) view.findViewById(R.id.weekplay_list_image);
            circleImageView = (CircleImageView) view.findViewById(R.id.weekplay_list_icon);
            textView = (TextView) view.findViewById(R.id.weekplay_list_name);
        }
    }
}
