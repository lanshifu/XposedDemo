package com.lanshifu.xposeddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by lanshifu on 2018/10/1.
 */

public class PreferenceUtils {
    public static final String AUTO_ALL ="auto_all";
    public static final String REPLY_CONTENT ="reply_content";
    public static final String REPLY_ORDER ="reply_order";
    public static final String REPLY_ROBOT ="reply_robot";
    public static final String REPLY_DESCRIBE ="reply_describe";

    //自动收（偷）能量开关
    public static final String ZHIFUBAO_OPEN ="zhifubao_open";

    //自动通知好友驱赶小鸡开关
    public static final String OPEN_NOTICE_STEAL ="zhifubao_open";

    private static XSharedPreferences intance = null;

    public static XSharedPreferences getIntance(){
        if (intance == null){
            intance = new XSharedPreferences("com.lanshifu.xposeddemo","config");
            intance.makeWorldReadable();
        }else {
            intance.reload();
        }
        return intance;
    }


    public static void putBool(Context context,String key,boolean val){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("config", Activity.MODE_WORLD_READABLE);
        SharedPreferences.Editor   mEditor  = mSharedPreferences.edit();
        mEditor.putBoolean(PreferenceUtils.ZHIFUBAO_OPEN, val).commit();
    }


    public static boolean isZhifubaoOpen(){
//        return getIntance().getBoolean(ZHIFUBAO_OPEN,true);
        return true;
    }

    public static boolean isNoticeStealOpen(){
//        return getIntance().getBoolean(OPEN_NOTICE_STEAL,false);
        return true;
    }


}
