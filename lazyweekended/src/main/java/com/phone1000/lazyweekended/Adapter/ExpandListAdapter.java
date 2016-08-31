package com.phone1000.lazyweekended.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.phone1000.lazyweekended.R;
import com.phone1000.lazyweekended.bean.CityListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {
    private List<CityListInfo.ResultBean> resultBeanList ;
    private Context mContext;

    public ExpandListAdapter(Context mContext, List<CityListInfo.ResultBean> resultBeanList) {
        this.mContext = mContext;
        this.resultBeanList = resultBeanList;
    }

    @Override
    public int getGroupCount() {
        return resultBeanList == null? 0: resultBeanList.size()-1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return resultBeanList.get(groupPosition+1).getCity_list().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return resultBeanList.get(groupPosition+1).getBegin_key();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  resultBeanList.get(groupPosition+1).getCity_list().get(childPosition);
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
        TextView  begainTv  = (TextView) convertView;
        if(convertView == null){
            begainTv = new TextView(mContext);
        }
        begainTv.setText(resultBeanList.get(groupPosition+1).getBegin_key());
        return begainTv;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
           View view = convertView;
          ChildViewHolder viewHolder = null;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_item,parent,false);
           viewHolder = new ChildViewHolder(view);
        }
        else{
            viewHolder = (ChildViewHolder) view.getTag();
        }
        String cityName = resultBeanList.get(groupPosition+1).getCity_list().get(childPosition).getCity_name();
       viewHolder.cityTv.setText(cityName);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class ChildViewHolder{
        @BindView(R.id.grid_item_tv)
        TextView cityTv;
        public  ChildViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }

}
