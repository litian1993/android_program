package com.phone1000.giftgenius;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phone1000.giftgenius.HttpUtils.IjsonCallback;
import com.phone1000.giftgenius.HttpUtils.JsonHttpUtils;
import com.phone1000.giftgenius.giftpackage.adapter.ListviewAdapter;
import com.phone1000.giftgenius.giftpackage.packageInfo.PackageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 李田 on 2016/8/18.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener,IjsonCallback {
    private ListView searchView;
    private EditText inputEditText;
    private Button searchBtn,backBtn;
    private JsonHttpUtils jsonHttpUtils;
    private ListviewAdapter adapter ;
    private final  String SEARCH_URL="http://www.1688wan.com/majax.action?method=searchGift";
    private List<PackageInfo.ListBean> listbean = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.searvh_activity);
        initView();
        initdata();
    }

    private void initdata() {
        //初始值
        searchGame("礼包");
    }

    private void initView() {
        searchView = (ListView) findViewById(R.id.search_list_view);
        inputEditText = (EditText) findViewById(R.id.search_list_input);
        searchBtn  = (Button) findViewById(R.id.search_list_search);
        backBtn = (Button) findViewById(R.id.search_list_back);
        adapter = new ListviewAdapter(listbean,this);
        searchView.setAdapter(adapter);
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetails(position);
            }
        });
        backBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

    }

    private void showDetails(int position) {
        //跳转到详情界面
        PackageInfo.ListBean bean = listbean.get(position);
        String giftid = bean.getId();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("giftid",giftid);
        intent.putExtras(bundle);
        intent.setClass(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_list_back:
                finish();
                break;
            case R.id.search_list_search:
              String  searchValue = inputEditText.getText().toString();
                if(searchValue == null){
                    Toast.makeText(this,"请输入关键字",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    listbean.clear();
                    adapter.notifyDataSetChanged();
                    searchGame(searchValue);

                }
                break;

        }

    }

    private void searchGame(String searhValue) {
        if(jsonHttpUtils == null){
            jsonHttpUtils = JsonHttpUtils.newInstance();
        }
        HashMap<String ,String> map = new HashMap<>();
        map.put("key",searhValue);
        jsonHttpUtils.load(SEARCH_URL,map,this);
    }

    @Override
    public void getString(String jsonString) {
        if("{\"list\":}".equals(jsonString)){
            Toast.makeText(this,"对不起没找到应用",Toast.LENGTH_SHORT).show();
        }
        else{
            Gson gson = new Gson();
            PackageInfo  beanlist= gson.fromJson(jsonString,PackageInfo.class);
            listbean.addAll(beanlist.getList());
            adapter.notifyDataSetChanged();
        }


    }
}
