package com.lanshifu.xposeddemo.utils;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by lanshifu on 2018/9/29.
 */

public class LogUtil {

    public static void d(String content){
        XposedBridge.log("lxb******************************");
        XposedBridge.log(content);
        XposedBridge.log("lxb------------------------------");
        Log.d("lxb", content);
    }

    public static void e(String content){
        Log.e("lxb", content);
    }
}
