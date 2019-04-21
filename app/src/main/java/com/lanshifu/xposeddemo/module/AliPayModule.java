package com.lanshifu.xposeddemo.module;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lanshifu.xposeddemo.Config;
import com.lanshifu.xposeddemo.utils.AliUtil;
import com.lanshifu.xposeddemo.utils.LogUtil;
import com.lanshifu.xposeddemo.utils.PreferenceUtils;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class AliPayModule extends BaseModule{

    private static final String TAG = "AliPayModule";

    public static void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam param) throws ClassNotFoundException {

        final ClassLoader loader = param.classLoader;
        if (Config.isMayiSenlinOpen) {
            Log.d("lxb", "支付宝开关打开222");

            // Farm

            try {
                Class ci = loader.loadClass("com.alipay.mobile.base.security.CI");
                if (ci != null) {
                    XposedHelpers.findAndHookMethod(ci, "a", ci, Activity.class, new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                            xLog("replaceHookedMethod,show dialog");
                            return null;
                        }
                    });
                } else {
                    xLog("ci == null");
                }
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "loadClass CI error: " + e.getMessage());
            }


            //我们利用之前分析的那几个类关系，保存当前的 H5Fragment
            //这块没有分析代码
            try {
                Class clazz = loader.loadClass("com.alipay.mobile.nebulacore.ui.H5FragmentManager");
                if (clazz != null) {
                    LogUtil.d("H5FragmentManager != null");
                    Class<?> h5FragmentClazz = loader.loadClass("com.alipay.mobile.nebulacore.ui.H5Fragment");
                    if (h5FragmentClazz != null) {
                        XposedHelpers.findAndHookMethod(clazz, "pushFragment", h5FragmentClazz,
                                boolean.class, Bundle.class, boolean.class, boolean.class, new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                                        super.afterHookedMethod(param);
                                        LogUtil.d("fragment,cur fragment = " + param.args[0]);
                                        AliUtil.curH5Fragment = param.args[0];

                                    }
                                });
                    }
                }

            } catch (Exception e) {
                LogUtil.e("H5Fragment error" + e.getMessage());
            }


            Log.d("lxb", "goto:H5Log ");
            try {
                Class h5Log = loader.loadClass("com.alipay.mobile.nebula.util.H5Log");
                xLog("H5Log == null ? :" + (h5Log == null));
                if (h5Log != null) {
                    hook_method("com.alipay.mobile.nebula.util.H5Log", loader, "d", String.class,
                            String.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);

                                    if (PreferenceUtils.isZhifubaoOpen()) {
                                        xLog("H5Log  " + param.args[0] + ":" + param.args[1]);
                                    }


                                }

                            });
                }
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "loadClass:H5Log error " + e.getMessage());
            }


            Class<?> clazz = null;
            try {
                // com.alipay.mobile.nebulabiz.rpc
                Log.d(TAG, "goto H5RpcUtil");


                clazz = loader.loadClass("com.alipay.mobile.nebulabiz.rpc.H5RpcUtil");
                if (clazz != null) {
                    xLog("H5RpcUtil != null");
                    Class<?> h5PageClazz = loader.loadClass("com.alipay.mobile.h5container.api.H5Page");
                    Class<?> jsonClazz = loader.loadClass("com.alibaba.fastjson.JSONObject");
                    Log.d("lxb", "h5PageClazz == null:" + (h5PageClazz == null));
                    Log.d("lxb", "jsonClazz == null:" + (jsonClazz == null));
                    if (h5PageClazz != null && jsonClazz != null) {
                        Log.d(TAG, "h5PageClazz != null && jsonClazz != null: ");
                        XposedHelpers.findAndHookMethod(clazz, "rpcCall", String.class, String.class,
                                String.class, boolean.class, jsonClazz, String.class, boolean.class, h5PageClazz,
                                int.class, String.class, boolean.class, int.class, new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        super.beforeHookedMethod(param);
                                        if (!PreferenceUtils.isZhifubaoOpen()) {
                                            return;
                                        }
                                        Log.d(TAG, "rpcCall: params:" + param.args[0] + "," +
                                                param.args[1] + "," + param.args[2] + "," +
                                                param.args[3] + "," + param.args[4] + "," +
                                                param.args[5] + "," + param.args[6] + "," +
                                                param.args[7] + "," + param.args[8] + "," +
                                                param.args[9] + "," + param.args[10] + "," +
                                                param.args[11]
                                        );
                                        Log.d(TAG, "rpcCall result: " + param.getResult());
                                        Log.d(TAG, "rpcCall obj2: " + param.thisObject);

                                    }

                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        Object resp = param.getResult();
                                        xLog("afterHookedMethod rpcCall");

                                        if (!PreferenceUtils.isZhifubaoOpen()) {
                                            return;
                                        }
                                        if (resp != null) {
                                            Method method = resp.getClass().getMethod("getResponse", new Class<?>[]{});
                                            String response = (String) method.invoke(resp, new Object[]{});
                                            xLog("response =" + response);


                                            //解析好友信息
                                            if (AliUtil.isBankList(response)) {
                                                xLog("解析好友信息数据");
                                                AliUtil.autoGetCanCollectUserIdList(loader, response);
                                            }

                                            //第一次是自己的能量，比上面获取用户信息消息还要早，所以这里需要记录当前自己的
                                            //userId值
                                            if (AliUtil.isUserDetail(response)) {
                                                LogUtil.d("解析好友能力详情信息");
                                                AliUtil.autoGetCanCollectBubbleIdList(loader, response);

                                            }

                                            if (AliUtil.isCollectResult(response)) {
                                                //计算收集了多少能量
                                                AliUtil.calcuCollectionResult(response);
                                            }


                                        } else {
                                            xLog("resp == null");
                                        }
                                    }
                                });


                    } else {
                        Log.d(TAG, "else");
                    }
                } else {
                    xLog("H5RpcUtil == null");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                xLog("H5RpcUtil error " + e.getMessage());
            }

            Log.d("lxb", "goto:H5Activity ");
            try {
                Class h5FragmentClazz = loader.loadClass("com.alipay.mobile.nebulacore.ui.H5Activity");
                if (h5FragmentClazz != null) {
                    XposedHelpers.findAndHookMethod(h5FragmentClazz, "onActivityCreated", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            Log.d(TAG, "hook H5Activity onAttach: 保存 activity");
                            AliUtil.activity = param.args[0];
                        }
                    });
                }

            } catch (Exception e) {
                LogUtil.e("hook H5Activity onAttach 失败 " + e.getMessage());
            }


        }

    }

}
