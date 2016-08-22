package com.phone1000.giftgenius.openingservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.SecondDetailActivity;
import com.phone1000.giftgenius.openingservice.OpenServiceAdapter.OpenServiceAdapter;
import com.phone1000.giftgenius.openingservice.info.OpenServiceInfo;
import com.phone1000.giftgenius.openingservice.info.OpenServiceUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class StartServiceFragment extends Fragment implements IjsonCallback {
    private ExpandableListView expandableListView;
    private List<List<OpenServiceInfo.InfoBean>> serviceInfoBeen = new ArrayList<>();
    private Context mContext;
    private  View view;
    private OpenServiceAdapter adapter;
    private JsonHttpUtils jsonHttpUtils ;
    public static StartServiceFragment newInstance(){

        return new StartServiceFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(mContext).inflate(R.layout.kaifu_kaifu,container,false);
        initView();
        initData();
        return view;
    }

    private void initData() {

        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(OpenServiceUrl.OPENSERVICE_URL,null,this);

    }

    private void initView() {
        expandableListView = (ExpandableListView) view.findViewById(R.id.kaifu_kaifu_eList);
      expandableListView.setGroupIndicator(null);
        adapter = new OpenServiceAdapter(serviceInfoBeen,mContext);
        expandableListView.setAdapter(adapter);
        //设置expandableListView点击Item以后无法收起
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
//设置expanView的点击事件
   expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
       @Override
       public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
             showDetails(groupPosition,childPosition);
           return false;
       }
   });
    }

    private void showDetails(int grounpPosition,int childPostion) {
        //设置点击Item后的跳转事件
        //根据item的位置获取数据
        OpenServiceInfo.InfoBean infobean = serviceInfoBeen.get(grounpPosition).get(childPostion);
        String id = infobean.getGid();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",id);
        Intent intent = new Intent(mContext, SecondDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void getString(String jsonString) {
        Gson gson  = new Gson();
        OpenServiceInfo info = gson.fromJson(jsonString,OpenServiceInfo.class);
        List<OpenServiceInfo.InfoBean>  infoBeanList = new ArrayList<>();
        infoBeanList.addAll(info.getServiceinfo());
        //将数据根据时间分装成一个List中的子List
        String addTime = infoBeanList.get(0).getAddtime();
        List<OpenServiceInfo.InfoBean> infoList = new ArrayList<>();
        for(int i = 0 ; i < infoBeanList.size();i++){
            //获取第i个item
            OpenServiceInfo.InfoBean infobean = infoBeanList.get(i);
             String itemAddTime = infobean.getAddtime();
            //判断当前的item的addTime是否与上一个相同，如果相同添加到一个子List，如果不同则重新创建一个子List,并将当前的Item添加到子List中
            if(!itemAddTime.equals(addTime) ){
                serviceInfoBeen.add(infoList);
                infoList = new ArrayList<>();
                infoList.add(infobean);
                //当最后一个和之前的不同
                if( i == infoBeanList.size() - 1 ){
                    serviceInfoBeen.add(infoList);
                }

            }
            else{
                infoList.add(infobean);
                //当为最后一个Item时
                if( i == infoBeanList.size() - 1 ){
                    serviceInfoBeen.add(infoList);
                }
            }
            addTime = itemAddTime;
        }
    adapter.notifyDataSetChanged();
        //设置Expanview强制展开
        int count  = expandableListView.getCount();
        for(int i = 0; i<count;i++){
            expandableListView.expandGroup(i);
        }


    }
}
