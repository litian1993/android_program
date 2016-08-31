package com.phone1000.lazyweekended.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;

import com.phone1000.lazyweekended.R;
import com.phone1000.lazyweekended.bean.CityListInfo;
import com.phone1000.lazyweekended.presenter.impl.IcityInfoImpl;
import com.phone1000.lazyweekended.view.IcityInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityDetailActivity extends AppCompatActivity implements IcityInfoView {

    GridView hotGridView;
    ExpandableListView cityList;
    GridView recentGrid;
    private View headerView;
    private List<CityListInfo.ResultBean> cityListBean = new ArrayList<>();
    private  List<CityListInfo.ResultBean.CityListBean> hotCitylist = new ArrayList<>();
    private IcityInfoImpl mIcityInfoimpl;
    private GridAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_detail);
        ButterKnife.bind(this);
        mIcityInfoimpl = new IcityInfoImpl(this);
        initHeaderView();
        initView();
        mIcityInfoimpl.queryCityInfo();

    }

    private void initView() {
        cityList.addHeaderView(headerView);

    }
    private void initHeaderView() {
        //初始化头部视图
        headerView = LayoutInflater.from(CityDetailActivity.this).inflate(R.layout.activity_city_detail_header,null,false);
        hotGridView = (GridView) headerView.findViewById(R.id.city_detail_hotcitylist);
        recentGrid = (GridView) headerView.findViewById(R.id.city_detail_recentcitylist);
         gridAdapter = new GridAdapter();
        hotGridView.setAdapter(gridAdapter);
    }

    @Override
    public void getCityListInfo(CityListInfo cityListInfo) {
        //获取城市的list列表
        cityListBean.addAll(cityListInfo.getResult());
        //获取热门城市的list
        hotCitylist.addAll(cityListBean.get(0).getCity_list());
        gridAdapter.notifyDataSetChanged();


    }

    class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return hotCitylist == null?0:hotCitylist.size();
        }

        @Override
        public Object getItem(int position) {
            return  hotCitylist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            GridViewHolder viewHolder = null;
            if(convertView == null){
                view = LayoutInflater.from(CityDetailActivity.this).inflate(R.layout.grid_item,parent,false);
                viewHolder = new GridViewHolder(view);
            }
            else{
                viewHolder = (GridViewHolder) view.getTag();
            }
            String hotCity = hotCitylist.get(position).getCity_name();
            viewHolder.hotCityTv.setText(hotCity);
            return view;
        }
        class GridViewHolder{
            @BindView(R.id.grid_item_tv)
            TextView hotCityTv;
            public GridViewHolder(View view){
                view.setTag(this);
                ButterKnife.bind(this,view);
            }

        }
    }
}
