package com.phone1000.giftgenius.giftpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.giftgenius.DetailActivity;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.SearchActivity;
import com.phone1000.giftgenius.giftpackage.adapter.ListviewAdapter;
import com.phone1000.giftgenius.giftpackage.adapter.ViewPageAdapter;
import com.phone1000.giftgenius.giftpackage.packageInfo.PackageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 李田 on 2016/8/13.
 */
public class PackageFragment extends Fragment implements IjsonCallback {
    private View view;
    private Context mContext;
    private PullToRefreshListView refreshlistView;
    private ListviewAdapter listAdapter ;
    private ViewPageAdapter pageAdapter;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private int pageno = 1;
    private boolean isPull ;
    private JsonHttpUtils jsonUtils;
    private List<PackageInfo.AdBean> ad = new ArrayList<>();
    private int count;
    private List<PackageInfo.ListBean> listbean = new ArrayList<>();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count++;
            viewPager.setCurrentItem(count%5);
            sendEmptyMessageDelayed(0,3000);
        }
    };
    private View headerView;
    private FragmentActivity mActivity;


    public static PackageFragment newInstance(){
     return  new PackageFragment();
 }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(mContext).inflate(R.layout.libao_fragment,container,false);
        setHasOptionsMenu(true);
        initHeaderView();
        initListView();
        initData();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
              Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

         jsonUtils = JsonHttpUtils.newInstance();
        Map<String,String> content = new HashMap<>();
        content.put("pageno",pageno+"");
        jsonUtils.load(PackageUrl.PACKAGE_URL,content,this);
    }

    private void initListView() {
        refreshlistView = (PullToRefreshListView) view.findViewById(R.id.libao_list_view);
      ListView listView = refreshlistView.getRefreshableView();
        refreshlistView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.addHeaderView(headerView);
        listAdapter = new ListviewAdapter(listbean,mContext);
        refreshlistView.setAdapter(listAdapter);
        refreshlistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                              //下拉刷新
                listbean.clear();
                listAdapter.notifyDataSetChanged();
                freshData(1);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                 pageno +=2;
                 freshData(pageno);
            }
        });

   refreshlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           Log.d("tag",position+"");
           showDetails(position);
       }
   });
    }

    private void showDetails(int position) {
        //跳转到详情界面
        PackageInfo.ListBean bean = listbean.get(position-2);
        String giftid = bean.getId();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",giftid);
        intent.putExtras(bundle);
        intent.setClass(mContext, DetailActivity.class);
        startActivity(intent);
    }

    /**
     * 初始化listView头部视图
     */
    private void initHeaderView() {
        headerView = LayoutInflater.from(mContext).inflate(R.layout.scollviewpager,null,false);
        linearLayout = (LinearLayout) headerView.findViewById(R.id.circle_selector_ll);
        viewPager = (ViewPager) headerView.findViewById(R.id.first_page_viewpager);
        pageAdapter= new ViewPageAdapter(ad,mContext);
        viewPager.setAdapter(pageAdapter);
        mHandler.sendEmptyMessage(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              for(int i = 0; i<linearLayout.getChildCount();i++){
                  ImageView imageView = (ImageView) linearLayout.getChildAt(i);
                  imageView.setEnabled(false);
              }
                ImageView imageView1 = (ImageView) linearLayout.getChildAt(position);
                imageView1.setEnabled(true);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    //设置点击头部Viewpager跳转的页面
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PackageInfo.AdBean adBean = ad.get(0);
                String linkurl  = adBean.getLinkurl();
                if(linkurl != null){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(linkurl);
                    intent.setData(uri);
                    startActivity(intent);
                }
                return true;
            }
        });

    }

    @Override
    public void getString(String jsonString) {
        Gson gson = new Gson();
        PackageInfo packageInfo = gson.fromJson(jsonString,PackageInfo.class);
        if(!isPull){
            ad.addAll(packageInfo.getAd());
        }
        listbean.addAll(packageInfo.getList());
        refreshlistView.onRefreshComplete();
       listAdapter.notifyDataSetChanged();
        pageAdapter.notifyDataSetChanged();

    }

    private void freshData(int pagenos){
        Map<String,String> content = new HashMap<>();
        content.put("pageno",pagenos+"");
        jsonUtils.load(PackageUrl.PACKAGE_URL,content,PackageFragment.this);

    }
}
