package com.lanshifu.xposeddemo.module;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.lanshifu.xposeddemo.Constants;
import com.lanshifu.xposeddemo.utils.LogUtil;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 */

public class MainModule extends BaseModule {

    public static Handler mHandler = new Handler();

    /**
     * 入口，通过反射调用
     *
     * @param param
     */
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam param) {

        LogUtil.d(" handle packageName = " + param.packageName);

        if (param.packageName.equals("com.lanshifu.xposeddemo")) {
            hook_method("com.lanshifu.xposeddemo.ui.MainActivity", param.classLoader, "isOpen", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("lxb", "hookMainActivity -- >initView");
                    param.setResult(true);
                    Toast.makeText((Context) param.thisObject, "模块已经启动", Toast.LENGTH_SHORT).show();
                }
            });
        }

        JianShuModule.handleLoadPackage(param);

        //番茄开始
        TomatoModule.hookClassLoader(param);


        //支付宝偷能量开始
//        AliPayModule.handle(param);
    }

}
