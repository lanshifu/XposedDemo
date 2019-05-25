package com.lanshifu.xposeddemo.module;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lanshifu.xposeddemo.utils.LogUtil;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TomatoModule extends BaseModule{

    private static final String TAG = "lxb-TomatoModule";

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

                                hookLog(loadPackageParam, classLoader);

                                hookVideoCount(loadPackageParam, classLoader);

                                hookVideoCountUtil(loadPackageParam, classLoader);

                                hookMainActivityOnCreate(loadPackageParam, classLoader);

                                hookDownload(loadPackageParam, classLoader);

                                hookDataUpload(loadPackageParam, classLoader);

                                hookUserInfo(loadPackageParam, classLoader);

                            }

                        });
            }catch (Exception e){
                LogUtil.e(e.getMessage());
            }
        }

    }

    private static void hookUserInfo(XC_LoadPackage.LoadPackageParam loadPackageParam, ClassLoader loader) {
        LogUtil.d(TAG,"hook hookUserInfo start");
        hook_method("com.one.tomato.entity.UserInfo", loader, "getCurrentLevelValue", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.d(TAG,"hookUserInfo， 经验值");
                param.setResult(4000);

            }
        });


        //级别
        hook_method("com.one.tomato.entity.UserInfo", loader, "getCurrentLevelIndex", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                LogUtil.d(TAG,"getCurrentLevelIndex result = " + result);
                param.setResult(5);

            }
        });

        //级别
        hook_method("com.one.tomato.entity.UserInfo", loader, "getCurrentLevelValue", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                LogUtil.d(TAG,"getCurrentLevelValue result = " + result);
                param.setResult(5);

            }
        });

        //等级 lv1
        hook_method("com.one.tomato.entity.BalanceBean", loader, "getBalance", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                LogUtil.d(TAG,"LevelBean BalanceBean 等级 =  " + result);
                param.setResult(6);


            }
        });
    }

    //禁止数据上报,
    private static void hookDataUpload(XC_LoadPackage.LoadPackageParam loadPackageParam, ClassLoader loader) {
        LogUtil.d(TAG,"hook 上报数据 start");
        hook_method("com.one.tomato.base.BaseApplication", loader, "f", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.d(TAG,"使用时长返回0， 不会上报数据");

                param.setResult(0);

            }
        });
    }

    private static void hookDownload(XC_LoadPackage.LoadPackageParam loadPackageParam, ClassLoader loader) {
        LogUtil.d(TAG,"hook hook PapaTabFragment start");
        hook_method("com.one.tomato.ui.papa.PapaTabFragment", loader, "o", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.d(TAG,"hookDownload PapaTabFragment o方法~");
                Object object = param.thisObject;
                Class<?> aClass = object.getClass();
                Method[] methods = aClass.getDeclaredMethods();
                Log.d(TAG, "hookDownload: methods.size = " + methods.length);

                for (Method method : methods) {
//                    Log.d(TAG, "hookDownload "+method.getName());
                    if (method.getName().equals("D")){
                        Log.d(TAG, "hookDownload 调用下载方法");
                        Toast.makeText((Context) param.thisObject, "下载功能已破解", Toast.LENGTH_SHORT).show();
                        method.setAccessible(true);
//                        method.invoke(object);

                    }
                }

            }
        });

        //public void c(int i) {}
        hook_method("com.one.tomato.ui.papa.PapaTabFragment", loader, "c",int.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object arg = param.args[0];
                LogUtil.d("videoPay c 方法 arg0 = " + arg);

            }
        });
//
        hook_method("com.one.tomato.base.BaseFragment", loader, "j", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                LogUtil.d(TAG,"hookDownload PapaTabFragment j方法 result = " + result);
                param.setResult(false);
            }
        });

       hook_method("com.one.tomato.utils.TaskUtil", loader, "a",int.class,int.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                LogUtil.d(TAG,"hookDownload TaskUtil a 方法 result = " + result);
                param.setResult(true);

            }
        });
//
//       //成员id
       hook_method("com.one.tomato.utils.PreferencesUtil", loader, "e", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Object result = param.getResult();
                LogUtil.d(TAG,"hookDownload PreferencesUtil e 方法 result = " + result);
                param.setResult(100);

            }
        });



    }

    private static void hookMainActivityOnCreate(XC_LoadPackage.LoadPackageParam loadPackageParam, ClassLoader loader) {
        LogUtil.d(TAG,"hook StartUpActivity start");
        hook_method("com.one.tomato.ui.MainTabActivity", loader, "onCreate", Bundle.class, new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.d(TAG,"afterHookedMethod onCreate");
                Toast.makeText((Context) param.thisObject, "蓝师傅破解", Toast.LENGTH_LONG).show();

            }
        });
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
    // private static void a(int i, String str, Object obj) {}
    //静态方法hook
    private static void hookLog(XC_LoadPackage.LoadPackageParam param, ClassLoader loader){
        LogUtil.d(TAG,"hook LogUtil start");
        try{
            final Class<?> logUtilClass = XposedHelpers.findClassIfExists("com.one.tomato.utils.LogUtil", param.classLoader);
            if (logUtilClass != null){
                LogUtil.d("LogUtil 找到");
                XposedHelpers.findAndHookMethod(logUtilClass, "a", int.class,String.class, Object.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d(TAG, "lxb-番茄日志 " + param.args[1] + " : "+ param.args[2]);

                    }
                });
            }
        }catch (Exception e){
            LogUtil.e(e.getMessage());
        }



    }

    //播放数总返回0，无限制观看
    private static void hookVideoCount(XC_LoadPackage.LoadPackageParam param, ClassLoader loader){
        LogUtil.d(TAG,"hookVideoCount start");

        hook_method("com.one.tomato.utils.PreferencesUtil", loader, "e", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                LogUtil.d(TAG,"afterHookedMethod 视频次数返回 -  " + param.getResult());
                param.setResult(0);

            }
        });
    }


    private static void hookVideoCountUtil(XC_LoadPackage.LoadPackageParam param, ClassLoader loader){
        //剩余播放次数
        hook_method("com.one.tomato.utils.VideoPlayCountUtils", loader, "b", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                LogUtil.d(TAG,"afterHookedMethod VideoPlayCountUtils.b --- " + param.getResult());
                param.setResult(1000);
            }
        });

        //总的可以播放次数
        hook_method("com.one.tomato.utils.VideoPlayCountUtils", loader, "c", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                LogUtil.d(TAG,"afterHookedMethod VideoPlayCountUtils.c --- " + param.getResult());
                param.setResult(1000);

            }
        });
    }


}
