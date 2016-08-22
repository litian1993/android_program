package com.phone1000.giftgenius;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 李田 on 2016/8/19.
 */
public class WelcomActivity extends AppCompatActivity {
    private Context mContext;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent();
            intent.setClass(mContext,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        mContext = this;
        handler.sendEmptyMessageDelayed(0,3000);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
