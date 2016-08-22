package com.phone1000.giftgenius;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 李田 on 2016/8/17.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userNameEt,passWordEt;
    private Button loginBtn;
    private ActionBar mActionBar;
    private TextView titleTv;
    private Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initAcionBar();
        initView();

    }
    private void initAcionBar() {
        //设置Acionbar
        mActionBar = getSupportActionBar();
        View ationbarView = LayoutInflater.from(this).inflate(R.layout.actionbar_layout,null,false);
        titleTv = (TextView) ationbarView.findViewById(R.id.aciton_text_show);
        backBtn = (Button) ationbarView.findViewById(R.id.aciotn_back);
        ActionBar.LayoutParams layoutparms = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT);
        mActionBar.setCustomView(ationbarView, layoutparms);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        titleTv.setText("会员登录");
    }
    private void initView() {
        userNameEt = (EditText) findViewById(R.id.login_user_name);
        passWordEt = (EditText) findViewById(R.id.login_user_password);
        loginBtn = (Button) findViewById(R.id.login_confire_btn);
        loginBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_confire_btn:
            Toast.makeText(this,"无服务器~~~~~~~",Toast.LENGTH_SHORT).show();
                break;
            case R.id.aciotn_back:
                finish();
                break;


        }
    }
}
