package com.lanshifu.xposeddemo;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 */

public class Module {

    private void hook_method(String className, ClassLoader classLoader, String methodName,
                             Object... parameterTypesAndCallback){
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            XposedBridge.log(e);
        }
    }

    public void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam param){
        xLog("handleMyHandleLoadPackage");

        hook_method("com.lanshifu.myapp_3.MainActivity", param.classLoader, "getCount", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(11);
            }
        });

    }

    private void xLog(String content) {
        XposedBridge.log("*******************************************************************************************************************************");
        XposedBridge.log(content);
        XposedBridge.log("----------------------------------------------------------------------------------------------------------------------");
    }
}
