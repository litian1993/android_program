package com.phone1000.lazyweekended.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.lazyweekended.Adapter.FirstListAdapter;
import com.phone1000.lazyweekended.R;
import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.bean.PareamsBean;
import com.phone1000.lazyweekended.presenter.impl.IitemDeailImpl;
import com.phone1000.lazyweekended.view.IpageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements IpageView,View.OnClickListener {
   @BindView(R.id.detailt_activity_lv)
   ListView detailLv;
    @BindView(R.id.detail_item_back)
    Button backButton;
    @BindView(R.id.detail_item_name)
    TextView titleTv;
    private String category;
    private IitemDeailImpl mIitemDetailImpl;
    private FirstListAdapter adapter;
    private List<FirstPageInfo.ResultBean> resultBeanList = new ArrayList<>();
    private String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initgetCategory();
        mIitemDetailImpl = new IitemDeailImpl(this);
        initView();
        mIitemDetailImpl.queryItemDetailInfo(category, PareamsBean.LAT,PareamsBean.CITY_ID);
    }

    private void initView() {
     adapter = new FirstListAdapter(this,resultBeanList);
        detailLv.setAdapter(adapter);
        titleTv.setText(titleName);

    }

    private void initgetCategory() {
        //获取跳转item的category
        Intent intent = getIntent();
         category = intent.getStringExtra("categeory");
        titleName = intent.getStringExtra("titleName");
        backButton.setOnClickListener(this);

    }


    @Override
    public void refreshListView(FirstPageInfo firstPageInfo) {
         resultBeanList.addAll(firstPageInfo.getResult());
         adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.detail_item_back:
                  finish();
                  break;
          }
    }
}
