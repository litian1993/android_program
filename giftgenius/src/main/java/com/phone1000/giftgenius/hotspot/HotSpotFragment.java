package com.phone1000.giftgenius.hotspot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.giftgenius.CustomGridView;
import com.phone1000.giftgenius.CustonListView;
import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.SecondDetailActivity;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.hotspot.hotspotadapter.HotSpotListAdapter;
import com.phone1000.giftgenius.hotspot.hotspotbean.HotSpotInfo;
import com.phone1000.giftgenius.hotspot.hotspotbean.HotSpotJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李田 on 2016/8/13.
 */
public class HotSpotFragment extends Fragment {
 private View view;
    private CustonListView listView;
    private CustomGridView gridView;
    private Context mContext;
    private ImageLoader imagloader;
    private HotSpotListAdapter listAdapter;
    private  HotGridAdapter  gridAdapter;
    private List<HotSpotInfo.InfoBean.Push1Bean> push1Bean = new ArrayList<>();
    private List<HotSpotInfo.InfoBean.Push2Bean> push2Bean = new ArrayList<>();
    public  static HotSpotFragment newInstance(){
        return  new HotSpotFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.from(mContext).inflate(R.layout.remen_fragment,container,false);
        imagloader = ImageLoader.initLoader(mContext);
        initView();
        initData();
        return view;
    }

    private void initData() {
        Gson gson = new Gson();
        HotSpotInfo info = gson.fromJson(HotSpotJson.HOTSPOT_JSON,HotSpotInfo.class);
        push1Bean.addAll(info.getInfo().getPush1());
        push2Bean.addAll(info.getInfo().getPush2());
        //通知适配器刷星
        listAdapter.notifyDataSetChanged();
        gridAdapter.notifyDataSetChanged();
    }

    private void initView() {
        listView = (CustonListView) view.findViewById(R.id.remen_list_view);
        View footview = LayoutInflater.from(mContext).inflate(R.layout.remen_listview_foot,null,false);
       //
        listView.addFooterView(footview);
        gridView = (CustomGridView) footview.findViewById(R.id.remen_grid_view);
        //初始化适配器
        listAdapter = new HotSpotListAdapter(push1Bean,mContext);
        gridAdapter = new HotGridAdapter();
        //绑定适配器
        listView.setAdapter(listAdapter);
        gridView.setAdapter(gridAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetails(position);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showGridDetails(position);
            }
        });
    }

    private void showDetails(int position) {
        HotSpotInfo.InfoBean.Push1Bean bean1  = push1Bean.get(position);
        String id = bean1.getAppid();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",id);
        Intent intent = new Intent(mContext, SecondDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void showGridDetails(int position) {
        HotSpotInfo.InfoBean.Push2Bean bean2  = push2Bean.get(position);
        String id = bean2.getAppid();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",id);
        Intent intent = new Intent(mContext, SecondDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    class  HotGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return push2Bean == null?0:push2Bean.size();
        }

        @Override
        public Object getItem(int position) {
            return push2Bean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            HotGridViewholder viewHolder = null;
            if(convertView == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.remen_gridview,null,false);
                viewHolder = new HotGridViewholder(view);
            }
            else{
                viewHolder = (HotGridViewholder) view.getTag();
            }
            HotSpotInfo.InfoBean.Push2Bean  bean = push2Bean.get(position);
              viewHolder.gridImage.setTag(PackageUrl.MAIN_PATH+bean.getLogo());
              imagloader.setImage(PackageUrl.MAIN_PATH,bean.getLogo(),viewHolder.gridImage);
             viewHolder.gridTv.setText(bean.getName());
            return view;
        }
        class HotGridViewholder{
            ImageView gridImage;
            TextView gridTv;
            public HotGridViewholder (View view){
                view.setTag(this);
                gridImage= (ImageView) view.findViewById(R.id.hotspot_grid_image);
                gridTv = (TextView) view.findViewById(R.id.hotspot_grid_name);
            }
        }
    }
}
