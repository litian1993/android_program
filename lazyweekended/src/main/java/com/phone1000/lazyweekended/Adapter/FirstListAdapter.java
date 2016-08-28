package com.phone1000.lazyweekended.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.lazyweekended.R;
import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李田 on 2016/8/26.
 */
public class FirstListAdapter extends BaseAdapter {
    private List<FirstPageInfo.ResultBean>  resultBeanList ;
    private Context mContext;

    public FirstListAdapter(Context mContext, List<FirstPageInfo.ResultBean> resultBeanList) {
        this.mContext = mContext;
        this.resultBeanList = resultBeanList;
    }

    @Override

    public int getCount() {

        return resultBeanList == null?0:resultBeanList.size();
    }

    @Override
    public Object getItem(int position) {

        return resultBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           View view =convertView;
        ViewHolder holder = null;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.firstfragment_list_itemview,parent,false);
            holder = new ViewHolder(view);
        }else{
            holder  = (ViewHolder) view.getTag();
        }
        FirstPageInfo.ResultBean resultBean = resultBeanList.get(position);
        String imagUrl = resultBean.getFront_cover_image_list().get(0);
        holder.titleTv.setText(resultBean.getTitle());
        holder.poiTv.setText(resultBean.getPoi()+"~"+resultBean.getDistance()+"~"+resultBean.getCategory());
        holder.timeDscTv.setText(resultBean.getTime_info());
        holder.collectNumberTv.setText(resultBean.getCollected_num()+"人收藏");
        holder.sellTv.setText("￥"+resultBean.getPrice());
        Picasso.with(mContext).load(imagUrl).into(holder.listPic);
        return view;
    }
    class ViewHolder{
        @BindView(R.id.first_list_image)
        ImageView listPic;
        @BindView(R.id.first_list_title)
        TextView titleTv;
        @BindView(R.id.first_list_poi)
        TextView poiTv;
        @BindView(R.id.first_list_timeDsc)
        TextView timeDscTv;
        @BindView(R.id.first_list_collectnumber)
        TextView collectNumberTv;
        @BindView(R.id.first_list_sell)
        TextView sellTv;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);

        }
    }
}
