package com.phone1000.giftgenius;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.feature.featureinfo.FeatureUrl;
import com.phone1000.giftgenius.feature.featureinfo.WednesPlayDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李田 on 2016/8/17.
 */
public class WendnesDetailActivity extends AppCompatActivity implements IjsonCallback,View.OnClickListener{
    private ImageView gNameView;
    private GridView gridView;
    private Button backBtn;
    private TextView addTimeTv,infoTv,titleTv;
    private GridAdapter adapter;
    private  String sid = "";
    private  String iconurl;
    private JsonHttpUtils jsonHttpUtils;
    private ActionBar mActionBar;
    private ImageLoader imageLoader;
    private  String addtime;
    private  String   desc;
    private  String name;
    private Context mContext;
    private List<WednesPlayDetail.ListBean> listBean = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wendnesdayplay_detail);
        mContext = this;
        imageLoader = ImageLoader.initLoader(this);
        initId();
        initView();
        initData();
    }


    private void initData() {
        jsonHttpUtils = JsonHttpUtils.newInstance();
        Map<String,String> map = new HashMap<>();
        if(sid != null){
            map.put("id",sid);
        }else{
            return;
        }
        jsonHttpUtils.load(FeatureUrl.WENDESPLAY_DETAIL_URL,map,this);

    }

    private void initId() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         iconurl = bundle.getString("iconurl");
       addtime = bundle.getString("addtime");
         desc = bundle.getString("desc");
        sid = bundle.getString("sid");
        name = bundle.getString("name");


    }

    private void initView() {
        gNameView = (ImageView) findViewById(R.id.wendesdayplay_detail_image);
        gridView = (GridView) findViewById(R.id.wendesdayplay_detail_gridview);
        addTimeTv = (TextView) findViewById(R.id.wendesdayplay_show_time);
        infoTv = (TextView) findViewById(R.id.wendesdayplay_detail_info);
        addTimeTv.setText(addtime);
        infoTv.setText(desc);
        gNameView.setTag(FeatureUrl.IMAGE_URL_HEAD+iconurl);
        imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD,iconurl,gNameView);
        titleTv.setText(name);
        backBtn.setOnClickListener(this);
        adapter = new GridAdapter();
        gridView.setAdapter(adapter);
     //设置监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeatils(position);
            }
        });
    }

    private void showDeatils(int position) {
        WednesPlayDetail.ListBean bean = listBean.get(position);
        String id = bean.getAppid();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",id);
        Intent intent = new Intent(this,SecondDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getString(String jsonString) {
        Gson gson = new Gson();
     WednesPlayDetail detail = gson.fromJson(jsonString,WednesPlayDetail.class);
        listBean.addAll(detail.getList());
        adapter.notifyDataSetChanged();
        

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aciotn_back:
                finish();
                break;
        }
    }

    class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listBean == null?0:listBean.size();
        }

        @Override
        public Object getItem(int position) {
            return listBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           View view =convertView;
            GridViewHolder viewHolder = null;
            if(convertView == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.wen_gridview,null,false);
                viewHolder = new GridViewHolder(view);
            }
            else{
                viewHolder = (GridViewHolder) view.getTag();
            }
            WednesPlayDetail.ListBean bean = listBean.get(position);
            viewHolder.gNameTv.setText(bean.getAppname());
            viewHolder.gameIv.setTag(FeatureUrl.IMAGE_URL_HEAD+bean.getAppicon());
              imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD,bean.getAppicon(),viewHolder.gameIv);
            return view;
        }

        class GridViewHolder{
            ImageView gameIv;
            TextView gNameTv;
            public GridViewHolder(View view){
                view.setTag(this);
                gNameTv = (TextView) view.findViewById(R.id.wen_grid_name);
                gameIv = (ImageView) view.findViewById(R.id.wen_grid_image);
            }
        }

    }
}
