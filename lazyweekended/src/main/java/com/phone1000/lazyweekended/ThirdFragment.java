package com.phone1000.lazyweekended;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 李田 on 2016/8/25.
 */
public class ThirdFragment extends Fragment {

    private View view;
    private Context mContext;
public  static  ThirdFragment newInstance(){
    return  new ThirdFragment();
}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      view = inflater.from(mContext).inflate(R.layout.third_fragemnt,container,false);
        return view;
    }
}
