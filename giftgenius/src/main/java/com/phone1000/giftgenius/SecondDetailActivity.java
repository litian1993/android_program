package com.phone1000.giftgenius;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.openingservice.info.OpenServiceUrl;
import com.phone1000.giftgenius.openingservice.info.ServiceDetailInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李田 on 2016/8/17.
 */
public class SecondDetailActivity extends AppCompatActivity implements IjsonCallback,View.OnClickListener {
    private ImageView gPicIv;
    private TextView gNameTv,gKindTv,gSizeTv,titleTv,dividline1;
    private ScrollView gDetailTv;
    private Button downLoadBtn,backBtn;
    private HorizontalScrollView scrollView;
    private JsonHttpUtils jsonHttpUtils;
    private String id = "";
    private Context mContext;
    private ImageLoader imageLoader;
    private List<ServiceDetailInfo.ImgBean> imageList = new ArrayList<>();
    private ServiceDetailInfo.AppBean appBean ;
    private LinearLayout imagell;
    private ActionBar mActionBar;
    private String downloadUrl;
    private int width;
    private RelativeLayout.LayoutParams params;
//    private LayoutParams relativeParmas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaifu_detail_activity);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        params = new RelativeLayout.LayoutParams(dip2px(this,px2dip(this,width/2)-20),dip2px(this,250));
        params.setMargins(dip2px(this,10),0,0,0);
        mContext = this;
        imageLoader = ImageLoader.initLoader(this);
        initAcionBar();
        initView();
        initId();
    }


    /**
     * 将dp转化为px的方法
     * @param context
     * @param dpValue
     * @return
     */
    private  static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 将px转化为dp
     */
    private static int px2dip(Context context,float pixValue){
     final  float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pixValue/scale+0.5f);
    }
    private void initId() {
        //获取Id值并获取数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
            id =  bundle.getString("giftid");
        Map<String,String> map = new HashMap<>();
        if( id != null){
            map.put("id",id);
            jsonHttpUtils  = JsonHttpUtils.newInstance();
        }
        else{
            return;
        }
        jsonHttpUtils.load(OpenServiceUrl.OPENSERVICE_DETAIL_URL,map,this);


    }

    private void initView() {
        //初始化控件
        gPicIv = (ImageView) findViewById(R.id.kaifu_detail_image);
        gNameTv = (TextView) findViewById(R.id.kaifu_detail_gname);
        gKindTv  = (TextView) findViewById(R.id.kaifu_detail_gkind);
        gSizeTv = (TextView) findViewById(R.id.kaifu_detail_gsize);
        gDetailTv = (ScrollView) findViewById(R.id.kaifu_detail_detaile) ;
        scrollView = (HorizontalScrollView) findViewById(R.id.kaifu_detail_scroview);
        imagell = (LinearLayout) scrollView.findViewById(R.id.image_ll);
        dividline1 = (TextView) findViewById(R.id.kaifu_detail_divideline1);
        downLoadBtn   = (Button) findViewById(R.id.kaifu_detail_downloadBtn);
//        relativeParmas = imagell.getLayoutParams();
        dividline1.setVisibility(View.GONE);
        downLoadBtn.setVisibility(View.GONE);
        downLoadBtn.setOnClickListener(this);

    }
    private void initAcionBar() {
        //设置Acionbar
        mActionBar = getSupportActionBar();
        View ationbarView = LayoutInflater.from(this).inflate(R.layout.kaifu_actionbar_layout,null,false);
        titleTv = (TextView) ationbarView.findViewById(R.id.kaifu_aciton_text);
        backBtn = (Button) ationbarView.findViewById(R.id.kaifu_aciotn_back);
        ActionBar.LayoutParams layoutparms = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT);
        mActionBar.setCustomView(ationbarView, layoutparms);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        backBtn.setOnClickListener(this);

    }

    @Override
    public void getString(String jsonString) {
        //获取数据
        Gson gson = new Gson();
        ServiceDetailInfo info = gson.fromJson(jsonString, ServiceDetailInfo.class);
        imageList.addAll(info.getImg());
        appBean = info.getApp();
        dividline1.setVisibility(View.VISIBLE);
        downLoadBtn.setVisibility(View.VISIBLE);
       downloadUrl = appBean.getDownload_addr();
        Log.d("tag",downloadUrl);
        //判断获取的下载链接是否为空
        if("http://".equals(downloadUrl)||"".equals(downloadUrl)){
            downLoadBtn.setBackgroundColor(Color.GRAY);
            downLoadBtn.setClickable(false);
            downLoadBtn.setText("暂无下载");
        }
        else{
            downLoadBtn.setClickable(true);
        }
        gNameTv.setText(appBean.getName());
        gKindTv.setText(appBean.getTypename());
        TextView textView = new TextView(this);
        textView.setText(appBean.getDescription());
        if(appBean.getDescription() != null){
            gDetailTv.addView(textView);
        }
        titleTv.setText(appBean.getName());
        backBtn.setOnClickListener(this);
        gPicIv.setTag(OpenServiceUrl.IMAGE_HEAD_URL+appBean.getLogo());
        imageLoader.setImage(OpenServiceUrl.IMAGE_HEAD_URL,appBean.getLogo(),gPicIv);
        if("".equals(appBean.getAppsize())){
            gSizeTv.setText("大小：未知");
        }
        else{
            gSizeTv.setText("大小："+appBean.getAppsize());
        }

        for(int i = 0 ; i < imageList.size();i++){
            ServiceDetailInfo.ImgBean imageBean =  imageList.get(i);
            ImageView imageView = new ImageView(mContext);
            //设置ImageView的宽和高
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(OpenServiceUrl.IMAGE_HEAD_URL+imageBean.getAddress());
            imageLoader.setImage(OpenServiceUrl.IMAGE_HEAD_URL,imageBean.getAddress(),imageView);
            imagell.addView(imageView);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.kaifu_aciotn_back:
                finish();
                break;
            case R.id.kaifu_detail_downloadBtn:
                //打开外部链接
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(downloadUrl);
                intent.setData(uri);
                startActivity(intent);
                break;
        }
    }
}
