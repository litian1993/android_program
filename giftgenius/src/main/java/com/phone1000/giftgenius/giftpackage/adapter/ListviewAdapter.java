package com.phone1000.giftgenius.giftpackage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.giftpackage.ViewHolder;
import com.phone1000.giftgenius.giftpackage.packageInfo.PackageInfo;

import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class ListviewAdapter extends BaseAdapter {
    private List<PackageInfo.ListBean> listBeenList;
    private Context mContext;
    private ImageLoader imageLoader;
    public ListviewAdapter(List<PackageInfo.ListBean> listBeenList, Context mContext) {
        this.listBeenList = listBeenList;
        this.mContext = mContext;
        imageLoader = ImageLoader.initLoader(mContext);
    }

    @Override
    public int getCount() {
        return listBeenList == null?0:listBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if(view == null){
            view  = LayoutInflater.from(mContext).inflate(R.layout.frament_lsitview_view,null,false);
            viewHolder = new ViewHolder(view,listBeenList);
            view.setTag(viewHolder);
        }
        else{
           viewHolder = (ViewHolder) view.getTag();
        }
        final PackageInfo.ListBean  listBean = listBeenList.get(position);
        viewHolder.giftKindTv.setText(listBean.getGiftname());
        viewHolder.giftNameTv.setText(listBean.getGname());
        viewHolder.giftDateTv.setText(listBean.getAddtime());
        //listview 中最好不要设有点击的控件会使item无法点击
        viewHolder.giftNumberTv.setText(listBean.getNumber()+"");
        viewHolder.getBtn.setTag(PackageUrl.MAIN_PATH+listBean.getIconurl());
        if( listBean.getNumber() == 0 && viewHolder.getBtn.getTag().equals(listBean.getIconurl())){
            viewHolder.getBtn.setText("淘  号");
            viewHolder.getBtn.setBackgroundColor(Color.GRAY);
        }

        viewHolder.imageView.setTag(PackageUrl.MAIN_PATH+listBean.getIconurl());
        String url = PackageUrl.PACKAGE_URL.substring(0,PackageUrl.PACKAGE_URL.lastIndexOf("/"));
       imageLoader.setImage(url,listBean.getIconurl(),viewHolder.imageView);
        return view;
    }
}
