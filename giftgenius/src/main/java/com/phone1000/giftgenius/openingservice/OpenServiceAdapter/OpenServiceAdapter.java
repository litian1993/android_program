package com.phone1000.giftgenius.openingservice.OpenServiceAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.openingservice.ServiceViewHolder;
import com.phone1000.giftgenius.openingservice.info.OpenServiceInfo;
import com.phone1000.giftgenius.openingservice.info.OpenServiceUrl;

import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class OpenServiceAdapter extends BaseExpandableListAdapter {
 private static List<List<OpenServiceInfo.InfoBean>>  parentBean;
    private Context mContext;
   private ImageLoader imageLoader;
    public OpenServiceAdapter( List<List<OpenServiceInfo.InfoBean>> parentBean, Context mContext) {
        this.parentBean = parentBean;
        this.mContext = mContext;
        imageLoader = ImageLoader.initLoader(mContext);
    }

    @Override
    public int getGroupCount() {
        return parentBean == null?0:parentBean.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentBean.get(groupPosition) == null?0:parentBean.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentBean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentBean.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       View view = convertView;
        GroupViewHolder groupViewHolder = null;
        if(convertView  == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.explistview_groupview,parent,false);
            groupViewHolder = new GroupViewHolder(view);
            view.setTag(groupViewHolder);
        }
        else{
            groupViewHolder = (GroupViewHolder) view.getTag();

        }
        String addTime = parentBean.get(groupPosition).get(0).getAddtime();
        groupViewHolder.textView.setText(addTime);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ServiceViewHolder viewHolder = null;
        if( view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.kaifu_kaifu_listview,parent,false);
            viewHolder = new ServiceViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ServiceViewHolder) view.getTag();
        }
        OpenServiceInfo.InfoBean infoBean = parentBean.get(groupPosition).get(childPosition);
        viewHolder.gNameTv.setText(infoBean.getGname());
        viewHolder.startTimeTv.setText(infoBean.getStarttime());
        viewHolder.ownerTv.setText(infoBean.getOperators());
        viewHolder.areaTv.setText(infoBean.getArea());
        viewHolder.imageView.setTag(PackageUrl.MAIN_PATH+infoBean.getIconurl());
        imageLoader.setImage(OpenServiceUrl.getImageUrl(OpenServiceUrl.OPENSERVICE_URL),infoBean.getIconurl(),viewHolder.imageView);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
class GroupViewHolder{
    public TextView textView;
    public GroupViewHolder(View view) {
        textView = (TextView) view.findViewById(R.id.exp_group_timetv);
    }
}
}
