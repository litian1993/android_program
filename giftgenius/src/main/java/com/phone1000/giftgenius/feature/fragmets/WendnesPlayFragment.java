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
import com.phone1000.giftgenius.WendnesDetailActivity;
import com.phone1000.giftgenius.feature.featureadapter.WednesPlayAdapter;
import com.phone1000.giftgenius.feature.featureinfo.FeatureUrl;
import com.phone1000.giftgenius.feature.featureinfo.Wendnesplayinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李田 on 2016/8/16.
 */
public class WendnesPlayFragment extends Fragment implements IjsonCallback{
    private Context mContext;
    private List<Wendnesplayinfo.ListBean> listBeen =  new ArrayList<>();
    private View view;
    private PullToRefreshListView refreshListView;
    private WednesPlayAdapter adapter ;
    private JsonHttpUtils jsonHttpUtils;
    private boolean isRefreshed;
    public static  WendnesPlayFragment newInstance(){
        return new WendnesPlayFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.from(mContext).inflate(R.layout.wednesdayplay_fragment,container,false);
          initView();
          initData();
        return view;
    }

    private void initData() {
        jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(FeatureUrl.WENDESPLAY_URL,null,this);


    }

    private void initView() {
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.wendesdayplay_list_view);
       adapter = new WednesPlayAdapter(mContext,listBeen);
        refreshListView.setAdapter(adapter);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                   isRefreshed  = true;
                   onRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetails(position);
            }
        });

    }

    private void showDetails(int position) {
        //获取要跳转时需要传输的数据
        Wendnesplayinfo.ListBean bean  = listBeen.get(position-1);
        String desc = bean.getDescs();
        String addtime = bean.getAddtime();
        String imageiconUrl = bean.getIconurl();
        String name = bean.getName();
        String sid = bean.getSid()+"";
        Bundle bundle = new Bundle();
        bundle.putString("desc",desc);
        bundle.putString("addtime",addtime);
        bundle.putString("iconurl",imageiconUrl);
        bundle.putString("name",name);
        bundle.putString("sid",sid);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(mContext, WendnesDetailActivity.class);
        startActivity(intent);


    }

    private void onRefresh() {
        listBeen.clear();
        jsonHttpUtils.load(FeatureUrl.WENDESPLAY_URL,null,this);

    }

    @Override
    public void getString(String jsonString) {
        Gson gson  = new Gson();
        Wendnesplayinfo info = gson.fromJson(jsonString,Wendnesplayinfo.class);
        listBeen.addAll(info.getList());
        adapter.notifyDataSetChanged();
        if(isRefreshed){
            refreshListView.onRefreshComplete();
        }

    }
}
