package com.lanshifu.xposeddemo.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.lanshifu.xposeddemo.Config;
import com.lanshifu.xposeddemo.Main;
import com.lanshifu.xposeddemo.R;
import com.lanshifu.xposeddemo.utils.PreferenceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_tip = (TextView) findViewById(R.id.tv_tip);
        if (isOpen()){
            tv_tip.setText("模块已经启动");
            tv_tip.setTextColor(getColor(R.color.green));
        }else {
            tv_tip.setText("模块未启动，请勾选模块并重启");
            tv_tip.setTextColor(getColor(R.color.red));

        }

        String tag = Main.TAG;

        Switch switch_zhifubao= (Switch) findViewById(R.id.switch_zhifubao);

        switch_zhifubao.setChecked(Config.isMayiSenlinOpen);
        switch_zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Config.isMayiSenlinOpen = b;

                SharedPreferences  mSharedPreferences = getSharedPreferences("config", Activity.MODE_WORLD_READABLE);
                SharedPreferences.Editor   mEditor  = mSharedPreferences.edit();
                mEditor.putBoolean(PreferenceUtils.ZHIFUBAO_OPEN, b).commit();




            }
        });
    }

    public String getResult(){
        return "未启动,请勾选框架并请重启";
    }

    public boolean isOpen(){
        return false;
    }
}
