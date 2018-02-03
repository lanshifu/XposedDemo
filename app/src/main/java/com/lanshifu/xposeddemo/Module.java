package com.lanshifu.xposeddemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lanshifu.xposeddemo.utils.ToastUtil;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 */

public class Module {

    private static final int ID_SETTING = 10;

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

        xLog("handleMyHandleLoadPackage");

        hook_method("com.lanshifu.myapp_3.MainActivity", param.classLoader, "getCount", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(11);
            }
        });

        hook_method("com.lanshifu.myapp_3.MainActivity", param.classLoader, "onPrepareOptionsMenu", Menu.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Activity activity = (Activity) param.thisObject;
                Menu menu = (Menu) param.args[0];
                menu.add(Menu.NONE, 0, ID_SETTING, "设置hook");
                xLog("hook 更多菜单");
            }
        });

        hook_method("com.lanshifu.myapp_3.MainActivity", param.classLoader, "onOptionsItemSelected", MenuItem.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                MenuItem menuItem = (MenuItem) param.args[0];
                if (menuItem.getItemId() == ID_SETTING){
                    Context context = (Context) param.thisObject;
                    ToastUtil.showShortToast(context,"点击了设置");
                    xLog("点击了 hook 更多菜单");
                }
            }
        });

    }

    private void xLog(String content) {
        XposedBridge.log("*******************************************************************************************************************************");
        XposedBridge.log(content);
        XposedBridge.log("----------------------------------------------------------------------------------------------------------------------");
        Log.d("lxb",content);
    }
}
