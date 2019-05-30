package com.lanshifu.xposeddemo;

import android.util.Log;

import com.lanshifu.xposeddemo.module.MainModule;
import com.lanshifu.xposeddemo.utils.LogUtil;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


public class Main implements IXposedHookLoadPackage {


	public static final String TAG = "lxb";


	@Override
	public void handleLoadPackage(final LoadPackageParam param) throws Throwable{
		if (BuildConfig.DEBUG){
			//通过反射实现热更新
			final String packageName = MainModule.class.getPackage().getName();
			String filePath = String.format("/data/app/%s-%s.apk", packageName, 1);
			if (!new File(filePath).exists()) {
				filePath = String.format("/data/app/%s-%s.apk", packageName, 2);
				if (!new File(filePath).exists()) {
					filePath = String.format("/data/app/%s-%s/base.apk", packageName, 1);
					if (!new File(filePath).exists()) {
						filePath = String.format("/data/app/%s-%s/base.apk", packageName, 2);
						if (!new File(filePath).exists()) {
							XposedBridge.log("lxb-Error:在/data/app找不到APK文件" + packageName);
							return;
						}
					}
				}
			}
			final PathClassLoader pathClassLoader = new PathClassLoader(filePath, ClassLoader.getSystemClassLoader());
			final Class<?> aClass = Class.forName(packageName + "." + MainModule.class.getSimpleName(), true, pathClassLoader);
			final Method aClassMethod = aClass.getMethod("handle", XC_LoadPackage.LoadPackageParam.class);
			aClassMethod.invoke(aClass.newInstance(), param);

			LogUtil.d("pkg:"+param.packageName);
		}else {
			Log.d(TAG, "not hot fix - >handleLoadPackage: " + param.packageName);
			MainModule module = new MainModule();
            try {
                module.handleMyHandleLoadPackage(param);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleLoadPackage: "+ e.getMessage());
            }
        }



	}

}




















