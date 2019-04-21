package com.lanshifu.xposeddemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

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
            tv_tip.setText("模块未启动，请到XposedInstaller中勾选模块并重启");
            tv_tip.setTextColor(getColor(R.color.red));

        }


        Switch switch_zhifubao= (Switch) findViewById(R.id.switch_zhifubao);
        switch_zhifubao.setChecked(PreferenceUtils.isZhifubaoOpen());
        switch_zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                PreferenceUtils.putBool(MainActivity.this,PreferenceUtils.ZHIFUBAO_OPEN,b);


            }
        });


        Switch switch_notice_steal= (Switch) findViewById(R.id.switch_notice_steal);
        switch_notice_steal.setChecked(PreferenceUtils.isNoticeStealOpen());
        switch_notice_steal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PreferenceUtils.putBool(MainActivity.this,PreferenceUtils.OPEN_NOTICE_STEAL,b);

            }
        });

        ((View)findViewById(R.id.btn_join_group)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                joinQQGroup("QYiQlOnt_tVIDW8GCH4Lj2gl2zn4mxRH");
            }
        });
    }


    /****************
     *
     * https://qun.qq.com/join.html
     *
     * 发起添加群流程。群号：番茄社区xposed模块交流(1012574771) 的 key 为： QYiQlOnt_tVIDW8GCH4Lj2gl2zn4mxRH
     * 调用 joinQQGroup(QYiQlOnt_tVIDW8GCH4Lj2gl2zn4mxRH) 即可发起手Q客户端申请加群 番茄社区xposed模块交流(1012574771)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }



    public String getResult(){
        return "未启动,请勾选框架并请重启";
    }

    public boolean isOpen(){
        return false;
    }
}
