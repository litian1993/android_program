package com.phone1000.lazyweekended;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private ViewPager mainViewPage;
    private RadioGroup radioGroup;
    private FragmentManager manager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private boolean isUserControll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        initFragment();
        initView();
    }

    private void initView() {
        mainViewPage = (ViewPager) findViewById(R.id.main_fragment_viewpage);
        PagerAdapter adapter = new PageAdapter(manager);
        mainViewPage.setAdapter(adapter);
        radioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        mainViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                 switch (position){
                     case 0:
                         radioGroup.check(R.id.main_radio_first);
                         break;
                     case 1:
                         radioGroup.check(R.id.main_radio_second);
                         break;
                     case 2:
                         radioGroup.check(R.id.main_radio_third);
                         break;
                     case  3:
                         radioGroup.check(R.id.main_radio_fourth);
                         break;
                 }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_radio_first:
                        mainViewPage.setCurrentItem(0);
                        break;
                    case R.id.main_radio_second:
                        mainViewPage.setCurrentItem(1);
                        break;
                    case R.id.main_radio_third:
                        mainViewPage.setCurrentItem(2);
                        break;
                    case R.id.main_radio_fourth:
                        mainViewPage.setCurrentItem(3);
                        break;
                }
            }
        });

    }

    private void initFragment() {
        fragmentList.add(FirstFragment.newInstance());
        fragmentList.add(SecondFragment.newInstance());
        fragmentList.add(ThirdFragment.newInstance());
        fragmentList.add(FourthFragment.newInstance());
    }
    class PageAdapter extends FragmentStatePagerAdapter{

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null? 0:fragmentList.size();
        }
    }
}
