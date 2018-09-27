package com.lanshifu.xposeddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_tip = (TextView) findViewById(R.id.tv_tip);
        tv_tip.setText(getResult());
    }

    public String getResult(){
        return "未启动,请勾选框架并请重启";
    }
}
