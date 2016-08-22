package com.phone1000.giftgenius;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phone1000.giftgenius.feature.FeatureFragment;
import com.phone1000.giftgenius.giftpackage.PackageFragment;
import com.phone1000.giftgenius.hotspot.HotSpotFragment;
import com.phone1000.giftgenius.openingservice.OpeningServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager manager ;
    private boolean isScrolled;
    private ActionBar mActionBar;
    private static  TextView textView;
    private Fragment mCurrentFramgment;
    private DrawerLayout drewerLayout;
    private float rOffencets;
    private Context mContext;
    private   RelativeLayout mainRl,drawll;
    private ObjectAnimator objectAnimator;
    private int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        manager = getSupportFragmentManager();
        initAcionBar();
        initFragments();
        initView();
    }
    private void initAcionBar() {
        //设置Acionbar
        mActionBar = getSupportActionBar();
        View ationbarView = LayoutInflater.from(this).inflate(R.layout.fragemtn_actionbar_layout,null,false);
        ActionBar.LayoutParams layoutparms = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT);
        mActionBar.setCustomView(ationbarView, layoutparms);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        textView = (TextView) ationbarView.findViewById(R.id.fragment_aciton_text_show);
        textView.setText("礼包精灵");

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float  rigthOffents = event.getRawX();
        return super.onTouchEvent(event);
    }

    private  void  activityMove(float x){
        objectAnimator = ObjectAnimator.ofFloat(mainRl,"x",0,x);
        objectAnimator.start();


    }
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_ll);
        mainRl = (RelativeLayout) findViewById(R.id.main_rl);
        drawll = (RelativeLayout) findViewById(R.id.draw_left);
        drewerLayout  = (DrawerLayout) findViewById(R.id.main_draw);
        Log.d("drewerlayoutx",drewerLayout.getX()+"");
         drewerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
             @Override
             public void onDrawerSlide(View drawerView, float slideOffset) {
                 Log.d("slideOffset",slideOffset+"");
                 //当前停止拉动时
              if(state == 2){
                  activityMove(dip2px(mContext,slideOffset*300));
              }

             }

             @Override
             public void onDrawerOpened(View drawerView) {

             }

             @Override
             public void onDrawerClosed(View drawerView) {

             }

             @Override
             public void onDrawerStateChanged(int newState) {
               state = newState;
             }
         });
        //设置radioGroup的监听事件,实现点击按钮切换Fragment
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {

               switch (checkedId){
                   case R.id.radio_btn_libao:
                       showFragment(fragments.get(0));
                       //动态改变标题栏
                       textView.setText("礼包精灵");
                       break;
                   case R.id.radio_btn_kaifu:
                       showFragment(fragments.get(1));
                       textView.setText("开服");
                       break;
                   case R.id.radio_btn_remen:
                       showFragment(fragments.get(2));
                       textView.setText("热门游戏");
                       break;
                   case R.id.radio_btn_tese:
                       textView.setText("独家策划");
                       showFragment(fragments.get(3));
                       break;

               }
           }
       });

    }
    public static  void changeTitle(String title){
        textView.setText(title);
    }

    private void initFragments() {
        fragments.add(PackageFragment.newInstance());
        fragments.add(OpeningServiceFragment.newInstance());
        fragments.add(HotSpotFragment.newInstance());
        fragments.add(FeatureFragment.newInstance());
        showFragment(fragments.get(0));
    }

    /**
     * 点击底部的radioGroup实现Fragment之间的切换
     * @param fragment
     */
    private void showFragment(Fragment fragment){
     FragmentTransaction transaction =  manager.beginTransaction();
        if(mCurrentFramgment != null){
            //隐藏上一个Fragment
             transaction.hide(mCurrentFramgment);
        }
        if( fragment.isAdded()){
            //如果已经添加的话就可以直接显示Fragment
            transaction.show(fragment);
        }
        else{
            //没有添加的话执行添加的方法
            transaction.add(R.id.main_show_fragent,fragment);

        }
        mCurrentFramgment = fragment;
        transaction.commit();


    }

}
