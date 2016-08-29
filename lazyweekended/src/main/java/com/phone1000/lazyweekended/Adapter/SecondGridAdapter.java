package com.phone1000.lazyweekended.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.lazyweekended.R;
import com.phone1000.lazyweekended.bean.SecondPageInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liTian on 2016/8/28.
 */
public class SecondGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<SecondPageInfo.ResultBean> resultBeen;

    public SecondGridAdapter(Context mContext, List<SecondPageInfo.ResultBean> resultBeen) {
        this.mContext = mContext;
        this.resultBeen = resultBeen;
    }

    @Override
    public int getCount() {

        return resultBeen == null? 0:resultBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return resultBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           View view = convertView;
           SecondViewHolder viewHolder;
        if( convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.second_grid_item,parent,false);
            viewHolder = new SecondViewHolder(view);
        }else{
            viewHolder = (SecondViewHolder) view.getTag();
        }
        SecondPageInfo.ResultBean resultBean = resultBeen.get(position);
        String imageUrl = resultBean.getIcon_view();
        viewHolder.itemTv.setText(resultBean.getCn_name());
        Picasso.with(mContext).load(imageUrl).into(viewHolder.itemIv);
        return view;
    }
    class SecondViewHolder{
        @BindView(R.id.second_grid_item_tv)
        TextView itemTv;
        @BindView(R.id.second_grid_item_iv)
        ImageView itemIv;
         public  SecondViewHolder(View view){
             view.setTag(this);
             ButterKnife.bind(this,view);

         }
    }
}
