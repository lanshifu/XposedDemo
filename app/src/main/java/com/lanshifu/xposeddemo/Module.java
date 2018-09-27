package com.lanshifu.xposeddemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
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
            xLog(e.getMessage());
        }
    }

    /**
     * 入口，通过反射调用
     *
     * @param param
     */
    public void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam param) throws ClassNotFoundException {

        ClassLoader loader = param.classLoader;

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
                    Toast.makeText((Context) param.thisObject, "你在看什么xposeddemo1234", Toast.LENGTH_SHORT).show();
                }
            });
        }

//        if (param.packageName.equals("com.lanshifu.baselibrary_master")) {
//            hook_method("com.lanshifu.baselibrary_master.ui.MainActivity", param.classLoader,
//                    "initView", new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            Log.d("lxb", "hookMainActivity -- >initView");
//                            Toast.makeText((Context) param.thisObject, "你在看什么MainActivity", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//            hook_method("com.lanshifu.baselibrary_master.ui.SecondActivity", param.classLoader,
//                    "initView", new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            Log.d("lxb", "hookMainActivity -- >initView");
//                            Toast.makeText((Context) param.thisObject, "你在看什么SecondActivity", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//
//
//        if (param.packageName.equals("me.jessyan.armscomponent.app")) {
//            hook_method("me.jessyan.armscomponent.zhihu.mvp.ui.activity.ZhihuHomeActivity", param.classLoader,
//                    "initView", Bundle.class, new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            Log.d("lxb", "hookMvpArm -- >ZhihuHomeActivity");
//                            Toast.makeText((Context) param.thisObject, "你在看什么ZhihuHomeActivity", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }


        if (Config.isMayiSenlinOpen ) {
            Log.d("lxb", "支付宝开关打开");
            Class ci = loader.loadClass("com.alipay.mobile.base.security.CI");
            if (ci != null){
                XposedHelpers.findAndHookMethod(ci, "a", ci, Activity.class, new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        xLog("replaceHookedMethod,show dialog");
                        return null;
                    }
                });
            }else {
                xLog("ci == null");
            }

            Log.d("lxb", "支付宝:H5Log");

            Class h5Log = loader.loadClass("com.alipay.mobile.nebula.util.H5Log");
            xLog("H5Log == null ? :" +(h5Log == null));
            if (h5Log != null){
                hook_method("com.alipay.mobile.nebula.util.H5Log", loader, "d", String.class,
                        String.class, new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                Log.d("lxb-hook日志",param.args[0] + ":" + param.args[1]);


                            }

                        });
            }


            Class<?> clazz = null;
            try {
                clazz = loader.loadClass("com.alipay.mobile.nebulabiz.rpc.H5RpcUil");
                if (clazz != null) {
                    xLog("H5RpcUil != null");
                    Class<?> h5PageClazz = loader.loadClass("com.alipay.mobile.h5container.api.H5Page");
                    Class<?> jsonClazz = loader.loadClass("com.alibaba.fastjson.JSONObject");
                    Log.d("lxb", "h5PageClazz == null:" + (h5PageClazz == null));
                    Log.d("lxb", "jsonClazz == null:" + (jsonClazz == null));
                    if (h5PageClazz != null && jsonClazz != null) {
                        // TODO: 2018\9\10 0010
                    } else {
                        // TODO: 2018\9\10 0010
                    }
                } else {
                    xLog("H5RpcUil == null");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                xLog("error " + e.getMessage());
            }


        }

        /**
         * 网易云跳过首页广告
         */
//        if (param.packageName.equals("com.netease.cloudmusic")) {
//            Log.d(TAG, "hook cloudmusic: ");
//            final Class<?> aClass = XposedHelpers.findClassIfExists("com.netease.cloudmusic.fragment.ay", param.classLoader);
//            if (aClass == null) {
//                return;
//            }
//            final Field field = XposedHelpers.findField(aClass, "b");
//            final Field field_handler = XposedHelpers.findField(aClass, "p");
//            if (field == null && field_handler != null) {
//                return;
//            }
//            field.setAccessible(true);
//            hook_method("com.netease.cloudmusic.fragment.ay", param.classLoader,
//                    "onResume", new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            Log.d("lxb", "hook-wangyiyun");
//                            final TextView tv_pass = (TextView) field.get(param.thisObject);
//                            final Handler handler = (Handler) field_handler.get(param.thisObject);
//                            if (tv_pass != null) {
//                                if (TextUtils.isEmpty(tv_pass.getText())) {
//                                    handler.postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            tv_pass.setClickable(true);
//                                            boolean performClick = tv_pass.performClick();
//                                            Log.d(TAG, "run: tv_pass.clickable delay" + tv_pass.isClickable());
//                                            Log.d(TAG, "handler-performClick delay: " + performClick + ",text=" + tv_pass.getText());
//                                        }
//                                    }, 1000);
//
//                                    return;
//                                }
//                                boolean performClick = tv_pass.performClick();
//                                Log.d(TAG, "run: tv_pass.clickable " + tv_pass.isClickable());
//                                Log.d(TAG, "handler-performClick: " + performClick + ",text=" + tv_pass.getText());
//                            }
//
//                        }
//                    });
//
//        }

    }

    private void xLog(String content) {
        XposedBridge.log("lxb*******************************************************************************************************************************");
        XposedBridge.log(content);
        XposedBridge.log("lxb----------------------------------------------------------------------------------------------------------------------");
        Log.d("lxb", content);
    }
}
