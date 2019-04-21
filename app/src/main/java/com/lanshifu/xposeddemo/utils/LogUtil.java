package com.lanshifu.xposeddemo.utils;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by lanshifu on 2018/9/29.
 */

public class LogUtil {

    public static void d(String tag,String content){
        XposedBridge.log("lxb******************************");
        XposedBridge.log(content);
        XposedBridge.log("lxb------------------------------");
        Log.d("lxb-xposed->" + tag, content);
    }

    public static void d(String content){
        XposedBridge.log("lxb******************************");
        XposedBridge.log(content);
        XposedBridge.log("lxb------------------------------");
        Log.d("lxb-xposed->", content);
    }

    public static void e(String content){
        Log.e("lxb-xposed->", content);
    }
}
