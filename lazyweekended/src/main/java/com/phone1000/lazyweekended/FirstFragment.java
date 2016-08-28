package com.phone1000.lazyweekended;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.phone1000.lazyweekended.Adapter.FirstListAdapter;
import com.phone1000.lazyweekended.bean.FirstPageInfo;
import com.phone1000.lazyweekended.presenter.PagePresenterImpl;
import com.phone1000.lazyweekended.view.IpageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李田 on 2016/8/25.
 */
public class FirstFragment extends Fragment implements IpageView {
    private View view;
   private Context mContext;
    private List<FirstPageInfo.ResultBean> resultBeanList = new ArrayList<>();
    private PagePresenterImpl mPagePresenterImpl;
    private FirstListAdapter adapter ;
    @BindView(R.id.first_fragment_listview)
     ListView listView;
    public static FirstFragment newInstance(){
        return  new FirstFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = getContext();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(mContext).inflate(R.layout.first_fragement,container,false);
        ButterKnife.bind(this,view);
        initView();
        mPagePresenterImpl = new PagePresenterImpl(this);
        mPagePresenterImpl.queryList();
        return view;
    }

    private void initView() {
       adapter = new FirstListAdapter(mContext,resultBeanList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public void refreshListView(FirstPageInfo firstPageInfo) {
            resultBeanList.addAll(firstPageInfo.getResult());
        //适配器刷新
        adapter.notifyDataSetChanged();
    }
}
