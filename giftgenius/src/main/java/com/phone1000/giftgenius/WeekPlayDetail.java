package com.phone1000.giftgenius;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.feature.featureinfo.FeatureUrl;
import com.phone1000.giftgenius.feature.featureinfo.WeekDetailInfo;
import com.phone1000.giftgenius.giftpackage.PackageUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李田 on 2016/8/18.
 */
public class WeekPlayDetail extends AppCompatActivity implements IjsonCallback {
    private ListView listView;
    private CircleImageView circleImageView;
    private ImageView backImage;
    private TextView infoTv,titleTv;
    private Button backBtn;
    private View headerView;
    private String decs = "";
    private String iconurl1 = "";
    private String name ="";
    private String id = "";
    private String logo = "";
    private Context mContext;
    private WeekDetailInfo.ListBean bean;
    private ImageLoader imageLoader;
    private JsonHttpUtils jsonHttpUtils ;
    private WeekDetailAdapter adpter;
    private List<WeekDetailInfo.ListBean> beanList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = ImageLoader.initLoader(this);
        mContext = this;
        setContentView(R.layout.weekplaydetail);
        initId();
        initView();
        initData();
    }

    private void initData() {
        jsonHttpUtils = JsonHttpUtils.newInstance();
        Map<String,String>  map = new HashMap<>();
        //获取到了id开始请求数据
        if(!"".equals(id)){
            map.put("id",id);
            jsonHttpUtils.load(FeatureUrl.WEEKPLAY_DETAIL_URL,map,this);
        }
        else{
            return;
        }
    }

    private void initId() {
        Intent  intent = getIntent();
        Bundle bundle = intent.getExtras();
       iconurl1 =   bundle.getString("iconurl");
        id =bundle.getString("id");
       name =  bundle.getString("name");
       decs =  bundle.getString("decs");
       logo =  bundle.getString("logo");

    }

    private void initView() {
        //初始化控件
        listView = (ListView) findViewById(R.id.weekdetail_list_view);
        headerView = LayoutInflater.from(mContext).inflate(R.layout.weekdetail_header,null,false);
       //添加头部视图
        listView.addHeaderView(headerView);
        circleImageView = (CircleImageView) headerView.findViewById(R.id.weekdetail_header_logo);
        infoTv  = (TextView) headerView.findViewById(R.id.detailweek_header_info);
        titleTv = (TextView) headerView.findViewById(R.id.weekdetail_header_title);
        backBtn = (Button) headerView.findViewById(R.id.weekdetail_header_back);
        backImage  = (ImageView) headerView.findViewById(R.id.weekdetail_header_image);
       //设置文字
        titleTv.setText(name);
        infoTv.setText(decs);
        if(logo != null){
            //设置头像图片
            circleImageView.setTag(FeatureUrl.IMAGE_URL_HEAD+"/"+logo);
            imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD+"/",logo,circleImageView);
        }

       //设置背景大图片
        if(iconurl1 != null){
            backImage.setTag(FeatureUrl.IMAGE_URL_HEAD+iconurl1);
            imageLoader.setImage(FeatureUrl.IMAGE_URL_HEAD,iconurl1,backImage);
        }
       adpter = new WeekDetailAdapter();
        listView.setAdapter(adpter);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.weekdetail_header_back){
                    finish();
                }
            }
        });
    }

    @Override
    public void getString(String jsonString) {
        Gson gson  = new Gson();
        WeekDetailInfo listbean = gson.fromJson(jsonString,WeekDetailInfo.class);
       beanList.addAll(listbean.getList());
        //通知适配器刷新
        adpter.notifyDataSetChanged();
    }

    class WeekDetailAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return beanList == null?0:beanList.size();
        }

        @Override
        public Object getItem(int position) {
            return beanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
           View view = convertView;
            WeekDetailViewHolder viewHolder = null;
            if( convertView == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.weekplay_detail_listview,null,false);
                viewHolder = new WeekDetailViewHolder(view);
            }
            else{
                viewHolder = (WeekDetailViewHolder) view.getTag();
            }
               bean = beanList.get(position);
            viewHolder.nameTv.setText(bean.getAppname());
            viewHolder.sizeTv.setText(bean.getAppsize());
            viewHolder.infoTv.setText(bean.getDescs());
            viewHolder.imageView.setTag(PackageUrl.MAIN_PATH+bean.getIconurl());
            imageLoader.setImage(PackageUrl.MAIN_PATH,bean.getIconurl(),viewHolder.imageView);
           //设置点击跳转事件
            viewHolder.downLoadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                    WeekDetailInfo.ListBean   bean2 = beanList.get(position);
                    String id = bean2.getAppid();
                    Bundle bundle = new Bundle();
                    bundle.putString("giftid",id);
                    Intent intent = new Intent(mContext,SecondDetailActivity.class);
                    intent.putExtras(bundle);
               startActivity(intent);
                }
            });
            return view;
        }
      class  WeekDetailViewHolder {
          ImageView imageView;
          TextView nameTv,sizeTv,infoTv;
          Button downLoadBtn;
          public WeekDetailViewHolder(View view){
              view.setTag(this);
              imageView  = (ImageView) view.findViewById(R.id.weekdetail_list_pic);
              nameTv = (TextView) view.findViewById(R.id.weekdetail_list_gamename);
              sizeTv = (TextView) view.findViewById(R.id.weekdetail_list_size);
              infoTv = (TextView) view.findViewById(R.id.weekdetail_list_introduce);
              downLoadBtn = (Button) view.findViewById(R.id.weekdetail_download);

          }
      }
    }
}
