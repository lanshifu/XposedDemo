package com.lanshifu.xposeddemo.module;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.lanshifu.xposeddemo.utils.LogUtil;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TomatoModule extends BaseModule{

    private static final String TAG = "TomatoModule";

    public static ClassLoader classLoader;

    public static void hookClassLoader(final XC_LoadPackage.LoadPackageParam loadPackageParam){
        if (classLoader == null){

            try {
                //腾讯加固，需要获取对应classloader
                XposedHelpers.findAndHookMethod("com.tencent.StubShell.TxAppEntry", loadPackageParam.classLoader,
                        "attachBaseContext", Context.class, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                //获取到Context对象，通过这个对象来获取classloader
                                Context context = (Context) param.args[0];
                                //获取classloader，之后hook加固后的就使用这个classloader
                                TomatoModule.classLoader = context.getClassLoader();
                                LogUtil.d("成功hook classloader");

                                hookAD(loadPackageParam, classLoader);

                                openLog(loadPackageParam, classLoader);

                                hookVideoCount(loadPackageParam, classLoader);


                            }

                        });
            }catch (Exception e){
                LogUtil.e(e.getMessage());
            }
        }

    }



    //自动跳过广告
    private static void hookAD(XC_LoadPackage.LoadPackageParam param, ClassLoader loader) {
        LogUtil.d(TAG,"hook StartUpActivity start");
        hook_method("com.one.tomato.ui.StartUpActivity", loader, "onCreate", Bundle.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.d(TAG,"beforeHookedMethod onCreate");
                Object object = param.thisObject;
                LogUtil.d(object.toString());
                Class<?> aClass = object.getClass();
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {

//                    LogUtil.d(method.getName());
                    if (method.getName().equals("z")){
                        LogUtil.d("自动跳过广告页");
                        method.setAccessible(true);
                        method.invoke(object);

                    }
                }


                Toast.makeText((Context) param.thisObject, "xposed并跳过广告，功能正常", Toast.LENGTH_SHORT).show();

            }
        });

    }


    //打开日志开关
    private static void openLog(XC_LoadPackage.LoadPackageParam param, ClassLoader loader){
        LogUtil.d(TAG,"hook LogUtil start");
        hook_method("com.one.tomato.utils.LogUtil", loader, "a", int.class,String.class,Object.class, new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                Object object = param.thisObject;
                LogUtil.d(object.toString());
                Class<?> aClass = object.getClass();
                Method[] methods = aClass.getDeclaredMethods();
                LogUtil.d("beforeHookedMethod LogUtil.a");
                for (Method method : methods) {
                    LogUtil.d(method.getName());
                    if (method.getName().equals("a")){
                        LogUtil.d("openLog");
                        method.setAccessible(true);
                        method.invoke(object,true);
                        return;

                    }
                }

            }

        });
    }

    //播放数总返回0，无限制观看
    private static void hookVideoCount(XC_LoadPackage.LoadPackageParam param, ClassLoader loader){
        LogUtil.d(TAG,"hookVideoCount start");

        hook_method("com.one.tomato.utils.PreferencesUtil", loader, "e", new XC_MethodHook() {

            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                LogUtil.d(TAG,"beforeHookedMethod hookVideoCount");

            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.d(TAG,"afterHookedMethod 视频次数返回 -  " + param.getResult());
                param.setResult(0);


            }
        });
    }


}
