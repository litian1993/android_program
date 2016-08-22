package com.phone1000.giftgenius.feature.fragmets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.WeekPlayDetail;
import com.phone1000.giftgenius.feature.featureadapter.WeekPlayAdapter;
import com.phone1000.giftgenius.feature.featureinfo.FeatureUrl;
import com.phone1000.giftgenius.feature.featureinfo.WeekPlayInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李田 on 2016/8/16.
 */
public class WeekPlayFragment extends Fragment implements IjsonCallback {
    private Context mContext;
    private List<WeekPlayInfo.ListBean> list = new ArrayList<>();
    private View view;
    private JsonHttpUtils jsonHttpUtils;
    private WeekPlayAdapter adapter;
    private PullToRefreshListView listView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }
public static WeekPlayFragment newInstance(){
    return  new WeekPlayFragment();
}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.from(mContext).inflate(R.layout.weekplay_fragment,container,false);
       initView();
       initData();
        return view;
    }

    private void initData() {
        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(FeatureUrl.WEEKPLAY_URL,null,this);
    }

    private void initView() {
      listView = (PullToRefreshListView) view.findViewById(R.id.weekplay_list_view);
        adapter = new WeekPlayAdapter(mContext,list);
        listView.setAdapter(adapter);
     listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
         @Override
         public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
             onRefesh();
         }

         @Override
         public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

         }
     });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetails(position);
            }
        });
    }

    private void showDetails(int position) {
        WeekPlayInfo.ListBean listbean = list.get(position-1);
        String iconurl = listbean.getIconurl();
        String name = listbean.getName();
        String logo = listbean.getAuthorimg();
        String decs = listbean.getDescs();
        String id = listbean.getId()+"";
        Bundle bundle = new Bundle();
        bundle.putString("iconurl",iconurl);
        bundle.putString("name",name);
        bundle.putString("decs",decs);
        bundle.putString("logo",logo);
        bundle.putString("id",id);
        Intent intent = new Intent(mContext, WeekPlayDetail.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void onRefesh() {
        list.clear();
        jsonHttpUtils.load(FeatureUrl.WEEKPLAY_URL,null,this);
        listView.onRefreshComplete();
    }

    @Override
    public void getString(String jsonString) {
        //解析数据
       Gson gson  = new Gson();
        WeekPlayInfo info = gson.fromJson(jsonString,WeekPlayInfo.class);
        list.addAll(info.getList());
        adapter.notifyDataSetChanged();


    }
}
