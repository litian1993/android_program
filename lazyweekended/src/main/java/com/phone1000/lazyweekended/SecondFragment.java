package com.phone1000.lazyweekended;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.phone1000.lazyweekended.Adapter.SecondGridAdapter;
import com.phone1000.lazyweekended.bean.SecondPageInfo;
import com.phone1000.lazyweekended.presenter.impl.SeondPresenterImpl;
import com.phone1000.lazyweekended.ui.DetailActivity;
import com.phone1000.lazyweekended.view.SecondPageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李田 on 2016/8/25.
 */
public class SecondFragment extends Fragment implements SecondPageView {
    private View view;
    private Context mContext;
    private List<SecondPageInfo.ResultBean> resultBeanList = new ArrayList<>();
    private SecondGridAdapter adapter;
    @BindView(R.id.second_fragemnt_location_btn)
    Button locationBtn;
    @BindView(R.id.second_fragment_search)
    EditText searchEt;
    @BindView(R.id.second_fragment_gridview)
    GridView gridView;
    private SeondPresenterImpl mSeondPresenterImpl;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = getContext();
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(mContext).inflate(R.layout.second_fragment, container, false);
        ButterKnife.bind(this, view);
        mSeondPresenterImpl = new SeondPresenterImpl(this);
        initView();
        mSeondPresenterImpl.queryList();
        return view;
    }

    private void initView() {
        adapter = new SecondGridAdapter(mContext, resultBeanList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //设置跳转事件
                String categeory = resultBeanList.get(position).getName();
                String titleName = resultBeanList.get(position).getCn_name();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("categeory",categeory);
                intent.putExtra("titleName",titleName );
                startActivity(intent);
            }
        });
    }

    @Override
    public void getSecondPageInfo(SecondPageInfo secondPageInfo) {

        resultBeanList.addAll(secondPageInfo.getResult());
        adapter.notifyDataSetChanged();
    }
}
