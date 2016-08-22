package com.phone1000.giftgenius.feature;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.giftgenius.MainActivity;
import com.phone1000.giftgenius.R;
import com.phone1000.giftgenius.feature.fragmets.WeekPlayFragment;
import com.phone1000.giftgenius.feature.fragmets.WendnesPlayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李田 on 2016/8/13.
 */
public class FeatureFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;
    private List<String> title;
    private Context mContext;

    private MyFragmentAdapter adapter;
    private FragmentManager manager;
    private List<Fragment> fragemnts = new ArrayList<>();
    private MainActivity mActivity;

    public static FeatureFragment newInstance(){
     return  new FeatureFragment();
 }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext =getContext();
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.from(mContext).inflate(R.layout.tese_fragment,container,false);
       manager = getFragmentManager();
       initView();
        initData();
        return view;
    }

    private void initData() {
        fragemnts.add(WendnesPlayFragment.newInstance());
        fragemnts.add(WeekPlayFragment.newInstance());
        title = new ArrayList<>();
        title.add("暴打星期三");
        title.add("新游周刊");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initView() {

        viewPager = (ViewPager) view.findViewById(R.id.tese_show_viewpage);
        tabLayout = (TabLayout) view.findViewById(R.id.tese_show_table);
        adapter = new MyFragmentAdapter(manager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
    class MyFragmentAdapter extends FragmentPagerAdapter{

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           return  fragemnts.get(position);
        }

        @Override
        public int getCount() {
            return fragemnts == null?0:fragemnts.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
