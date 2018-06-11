package com.lanshifu.xposeddemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 */

public class Module {

    private static final int ID_SETTING = 10;
    private static final String TAG = "lxb";

    private void hook_method(String className, ClassLoader classLoader, String methodName,
                             Object... parameterTypesAndCallback) {
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            XposedBridge.log(e);
        }
    }

    /**
     * 入口，通过反射调用
     *
     * @param param
     */
    public void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam param) {

        xLog("handleMyHandleLoadPackage packageName = " + param.packageName);

//        hook_method("com.lanshifu.myapp_3.MainActivity", param.classLoader, "getCount", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                param.setResult(11);
//            }
//        });
//
//        hook_method("com.lanshifu.baselibrary_master.ui.SecondActivity", param.classLoader, "onPrepareOptionsMenu", Menu.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                Activity activity = (Activity) param.thisObject;
//                Menu menu = (Menu) param.args[0];
//                menu.add(Menu.NONE, 0, ID_SETTING, "设置hook");
//                xLog("hook 更多菜单");
//            }
//        });
//
//
//        hook_method("com.lanshifu.baselibrary_master.ui.SecondActivity", param.classLoader, "onOptionsItemSelected", MenuItem.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                MenuItem menuItem = (MenuItem) param.args[0];
//                if (menuItem.getItemId() == ID_SETTING){
//                    Context context = (Context) param.thisObject;
//                    ToastUtil.showShortToast(context,"点击了设置");
//                    xLog("点击了 hook 更多菜单");
//                }
//            }
//        });

        if (param.packageName.equals("com.lanshifu.xposeddemo")) {

            hook_method("com.lanshifu.xposeddemo.MainActivity", param.classLoader, "getResult", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("lxb", "hookMainActivity -- >initView");
                    param.setResult("已启动，不需要重启手机");
                    Toast.makeText((Context) param.thisObject, "你在看什么xposeddemo", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (param.packageName.equals("com.lanshifu.baselibrary_master")) {
            hook_method("com.lanshifu.baselibrary_master.ui.MainActivity", param.classLoader,
                    "initView", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("lxb", "hookMainActivity -- >initView");
                            Toast.makeText((Context) param.thisObject, "你在看什么MainActivity", Toast.LENGTH_SHORT).show();
                        }
                    });

            hook_method("com.lanshifu.baselibrary_master.ui.SecondActivity", param.classLoader,
                    "initView", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("lxb", "hookMainActivity -- >initView");
                            Toast.makeText((Context) param.thisObject, "你在看什么SecondActivity", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


        if (param.packageName.equals("me.jessyan.armscomponent.app")) {
            hook_method("me.jessyan.armscomponent.zhihu.mvp.ui.activity.ZhihuHomeActivity", param.classLoader,
                    "initView", Bundle.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("lxb", "hookMvpArm -- >ZhihuHomeActivity");
                            Toast.makeText((Context) param.thisObject, "你在看什么ZhihuHomeActivity", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        if (param.packageName.equals("com.mixiu")) {
            hook_method("com.mixiu.主窗口", param.classLoader,
                    "主窗口$创建完毕", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("lxb", "hookMixiu -- >主窗口$创建完毕");
                        }
                    });
        }

//        if (param.packageName.equals("com.mixiu") || param.packageName.equals("com.le.android.webview")){
//            hook_method("com.e4a.runtime.android.mainActivity", param.classLoader, "onCreate",
//                    Bundle.class,new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.d("lxb","hook主窗口onCreate");
//                    Toast.makeText((Context) param.thisObject, "创建完毕", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//           hook_method("com.e4a.runtime.android.mainActivity", param.classLoader, "onPause",
//                    new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.d("lxb","hook主窗口onPause");
//                    Toast.makeText((Context) param.thisObject, "onPause", Toast.LENGTH_SHORT).show();
//
//                }
//            });

        /**
         * 网易云跳过首页广告
         */
        if (param.packageName.equals("com.netease.cloudmusic")) {
            Log.d(TAG, "hook cloudmusic: ");
            final Class<?> aClass = XposedHelpers.findClassIfExists("com.netease.cloudmusic.fragment.ay", param.classLoader);
            if (aClass == null) {
                return;
            }
            final Field field = XposedHelpers.findField(aClass, "b");
            final Field field_handler = XposedHelpers.findField(aClass, "p");
            if (field == null && field_handler !=null) {
                return;
            }
            field.setAccessible(true);
            hook_method("com.netease.cloudmusic.fragment.ay", param.classLoader,
                    "onResume", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("lxb", "hook-wangyiyun");
                            final TextView tv_pass = (TextView) field.get(param.thisObject);
                            final Handler handler = (Handler) field_handler.get(param.thisObject);
                            if (tv_pass != null) {
                                if (TextUtils.isEmpty(tv_pass.getText())){
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_pass.setClickable(true);
                                            boolean performClick = tv_pass.performClick();
                                            Log.d(TAG, "run: tv_pass.clickable delay" + tv_pass.isClickable());
                                            Log.d(TAG, "handler-performClick delay: " + performClick + ",text=" + tv_pass.getText());
                                        }
                                    },1000);

                                    return;
                                }
                                boolean performClick = tv_pass.performClick();
                                Log.d(TAG, "run: tv_pass.clickable " + tv_pass.isClickable());
                                Log.d(TAG, "handler-performClick: " + performClick + ",text=" + tv_pass.getText());
                            }

                        }
                    });

        }

    }

    private void xLog(String content) {
        XposedBridge.log("lxb*******************************************************************************************************************************");
        XposedBridge.log(content);
        XposedBridge.log("lxb----------------------------------------------------------------------------------------------------------------------");
        Log.d("lxb", content);
    }
}
