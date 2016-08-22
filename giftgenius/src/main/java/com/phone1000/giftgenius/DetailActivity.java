package com.phone1000.giftgenius;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.caches.ImageLoader;
import com.phone1000.giftgenius.giftpackage.PackageUrl;
import com.phone1000.giftgenius.giftpackage.packageInfo.PackageDetailInfo;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李田 on 2016/8/16.
 */
public class DetailActivity extends AppCompatActivity  implements IjsonCallback,View.OnClickListener{
    private CircleImageView circleImageView;
    private TextView addTimeTv,numberTv,explainTv,exchangeTv,titleTv;
    private Button getBtn,loginBtn,backBtn;
    private JsonHttpUtils jsonHttpUtils;
    private RelativeLayout relativeLayout;
    private ImageLoader imageLoader;
    private ActionBar mActionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        imageLoader  = ImageLoader.initLoader(this);
        initAcionBar();
        initView();
        initData();
    }

    private void initAcionBar() {
        //设置Acionbar
        mActionBar = getSupportActionBar();
        View ationbarView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout,null,false);
        titleTv = (TextView) ationbarView.findViewById(R.id.aciton_text_show);
       backBtn = (Button) ationbarView.findViewById(R.id.aciotn_back);
        ActionBar.LayoutParams layoutparms = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT);
       mActionBar.setCustomView(ationbarView, layoutparms);
       mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
       mActionBar.setDisplayShowCustomEnabled(true);
       mActionBar.setDisplayShowHomeEnabled(false);
       mActionBar.setDisplayShowTitleEnabled(false);
    }

    private void initData() {
        //加载数据
        jsonHttpUtils = jsonHttpUtils.newInstance();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("giftid");
        Map<String,String> map = new HashMap<>();
        if( id != null){
            map.put("id",id);
        }else{
            return;
        }

        jsonHttpUtils.load(PackageUrl.PACKAGE_DETAIL,map,this);
    }

    private void initView() {
        //
        circleImageView = (CircleImageView) findViewById(R.id.detail_cicle_view);
        addTimeTv = (TextView) findViewById(R.id.detail_add_time);
        numberTv = (TextView) findViewById(R.id.detail_left_number);
        explainTv = (TextView) findViewById(R.id.detail_show_explain);
        exchangeTv = (TextView) findViewById(R.id.detail_show_exchangemethod);
        getBtn = (Button) findViewById(R.id.detail_get);
        loginBtn = (Button) findViewById(R.id.detail_login);
        getBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void getString(String jsonString) {
        Gson gson = new Gson();
        PackageDetailInfo info = gson.fromJson(jsonString,PackageDetailInfo.class);
        PackageDetailInfo.InfoBean bean = info.getInfo();
        addTimeTv.setText(bean.getAddtime());
        numberTv.setText(bean.getNumber()+"");
        exchangeTv.setText(bean.getDescs());
        explainTv.setText(bean.getExplains());
        //设置标题
        titleTv.setText(bean.getGname()+"-"+bean.getGiftname());
        //设置图片
        circleImageView.setImageResource(R.drawable.def_loading);
        circleImageView.setTag(PackageUrl.MAIN_PATH+bean.getIconurl());
        imageLoader.setImage(PackageUrl.MAIN_PATH,bean.getIconurl(),circleImageView);

    }
//设置点击跳转时间
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.detail_get:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case  R.id.detail_login:
                Intent intent1 = new Intent(this,LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.aciotn_back:
                finish();
                break;

        }

    }
}
