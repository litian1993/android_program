package com.phone1000.giftgenius.openingservice;

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
import com.phone1000.giftgenius.SecondDetailActivity;
import com.phone1000.giftgenius.openingservice.OpenServiceAdapter.OpenCheckAdapter;
import com.phone1000.giftgenius.openingservice.info.OpenCheckInfo;
import com.phone1000.giftgenius.openingservice.info.OpenServiceUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李田 on 2016/8/15.
 */
public class StartCheckFragment extends Fragment implements IjsonCallback{
     private  View view;
    private Context mContext;
    private OpenCheckAdapter adapter;
    private List<OpenCheckInfo.InfoBean> infoBeanList = new ArrayList<>();
    private PullToRefreshListView listView ;
    private JsonHttpUtils jsonHttpUtils;
  public static StartCheckFragment newInstance(){
      return new StartCheckFragment();
  }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.from(mContext).inflate(R.layout.kaifu_kaice,container,false);
        initView();
        initData();
        return view;
    }

    private void initData() {
         jsonHttpUtils = JsonHttpUtils.newInstance();
        jsonHttpUtils.load(OpenServiceUrl.OPENCHECK_URL,null,this);

    }
    private void showDetails(int position) {
        //跳转到详情界面
        OpenCheckInfo.InfoBean bean = infoBeanList.get(position-1);
        String giftid = bean.getGid();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",giftid);
        intent.putExtras(bundle);
        intent.setClass(mContext, SecondDetailActivity.class);
        startActivity(intent);
    }
    private void initView() {
        listView = (PullToRefreshListView) view.findViewById(R.id.kaifu_kaice_list);
         adapter = new OpenCheckAdapter(mContext, infoBeanList);
        listView.setAdapter(adapter);
        /**
         * 监听下拉刷新
         */
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                infoBeanList.clear();
                jsonHttpUtils = JsonHttpUtils.newInstance();
                jsonHttpUtils.load(OpenServiceUrl.OPENCHECK_URL,null,StartCheckFragment.this);
                  listView.onRefreshComplete();
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

    @Override
    public void getString(String jsonString) {
        Gson gson  = new Gson();
        OpenCheckInfo checkInfo = gson.fromJson(jsonString,OpenCheckInfo.class);
        infoBeanList.addAll(checkInfo.getInfo());
        adapter.notifyDataSetChanged();
    }
}
