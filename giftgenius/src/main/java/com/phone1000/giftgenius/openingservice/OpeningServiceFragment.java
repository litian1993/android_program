package com.phone1000.giftgenius.openingservice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.giftgenius.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 李田 on 2016/8/13.
 */
public class OpeningServiceFragment extends Fragment {
    private View view;
    private Context mContext;
    private TabLayout tabLayout;
    private List<String> title = new ArrayList<>();
    private ViewPager viewPager ;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager manager;
    private  MyAdapter adapter;
    public static  OpeningServiceFragment newInstance(){
        return  new OpeningServiceFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(mContext).inflate(R.layout.kaifu_fragment,container,false);
        manager = getFragmentManager();
        initView();
        initData();
        return view;
    }

    private void initData() {
        title.add("开服");
        title.add("开测");
        fragments.add(StartServiceFragment.newInstance());
        fragments.add(StartCheckFragment.newInstance());
        adapter.notifyDataSetChanged();
    }

    private void initView() {
       tabLayout = (TabLayout) view.findViewById(R.id.kaifu_table_item);
        viewPager = (ViewPager) view.findViewById(R.id.kaifu_viewpage);
       adapter = new MyAdapter(manager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
    class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null?0:fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }

}
