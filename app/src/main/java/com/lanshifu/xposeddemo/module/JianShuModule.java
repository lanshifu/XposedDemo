package com.lanshifu.xposeddemo.module;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.lanshifu.xposeddemo.utils.LogUtil;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 * 简书去广告
 */

public class JianShuModule extends BaseModule {

    public static Handler mHandler = new Handler();

    private static final String TAG = "lxb-JianShuModule";

    public static void handleLoadPackage(final XC_LoadPackage.LoadPackageParam param) {

        if (!param.packageName.equals("com.jianshu.haruki")) {
            return;
        }

        hook_method("com.baiji.jianshu.ui.splash.SplashAdActivity", param.classLoader, "onCreate", Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Toast.makeText((Context) param.thisObject, "广告页，自动跳过", Toast.LENGTH_SHORT).show();
                        //反射调用c方法
                        Object object = param.thisObject;
                        Class<?> aClass = object.getClass();
                        Method[] methods = aClass.getDeclaredMethods();
                        Log.d(TAG, "SplashAdActivity: methods.size = " + methods.length);

                        for (Method method : methods) {
                            Log.d(TAG, "hookDownload " + method.getName());
                            if (method.getName().equals("c")) {
                                Log.d(TAG, "SplashAdActivity 跳过吧~");
                                Toast.makeText((Context) param.thisObject, "广告页1，自动跳过", Toast.LENGTH_SHORT).show();
                                method.setAccessible(true);
                                method.invoke(object);

                            }
                        }

                    }
                });


        hook_method("com.baiji.jianshu.ui.splash.SplashScreenActivity", param.classLoader, "onCreate", Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        //反射调用c方法
                        Object object = param.thisObject;
                        Class<?> aClass = object.getClass();
                        Method[] methods = aClass.getDeclaredMethods();
                        Log.d(TAG, "SplashScreenActivity: methods.size = " + methods.length);

                        for (Method method : methods) {
                            Log.d(TAG, "hookDownload " + method.getName());
                            if (method.getName().equals("j")) {
                                Log.d(TAG, "SplashScreenActivity 跳过吧~");
                                Toast.makeText((Context) param.thisObject, "蓝师傅破解，跳过首页广告2~", Toast.LENGTH_SHORT).show();
                                method.setAccessible(true);
                                method.invoke(object);

                            }
                        }

                    }
                });


        hook_method("com.baiji.jianshu.MainActivity", param.classLoader, "onCreate", Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Toast.makeText((Context) param.thisObject, "进入简书主页，蓝师傅破解", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "hook MainActivity");
                    }
                });

    }

}
