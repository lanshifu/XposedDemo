package com.lanshifu.xposeddemo;

import android.util.Log;

import com.lanshifu.xposeddemo.module.MainModule;
import com.lanshifu.xposeddemo.utils.LogUtil;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


public class Main implements IXposedHookLoadPackage {


	public static final String TAG = "lxb";


	@Override
	public void handleLoadPackage(final LoadPackageParam param) throws Throwable{
		if (BuildConfig.DEBUG){
			//通过反射实现热更新
			final String packageName = Main.class.getPackage().getName();
			String filePath = String.format("/data/app/%s-%s.apk", packageName, 1);
			if (!new File(filePath).exists()) {
				filePath = String.format("/data/app/%s-%s.apk", packageName, 2);
				if (!new File(filePath).exists()) {
					filePath = String.format("/data/app/%s-%s/base.apk", packageName, 1);
					if (!new File(filePath).exists()) {
						filePath = String.format("/data/app/%s-%s/base.apk", packageName, 2);
						if (!new File(filePath).exists()) {
							LogUtil.e("file not exists:filePath= "+filePath + " ,packageName="+packageName);
							return;
						}
					}
				}
			}
			final PathClassLoader pathClassLoader = new PathClassLoader(filePath, ClassLoader.getSystemClassLoader());
			String className = MainModule.class.getName();

			LogUtil.d("hot load handleLoadPackage,className=: " + className);
			final Class<?> aClass = Class.forName(className, true, pathClassLoader);
			final Method aClassMethod = aClass.getMethod("handle", XC_LoadPackage.LoadPackageParam.class);
			aClassMethod.invoke(aClass.newInstance(), param);


		}else {
			Log.d(TAG, "not hot load - >handleLoadPackage: " + param.packageName);
			MainModule module = new MainModule();
            try {
                module.handle(param);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleLoadPackage: "+ e.getMessage());
            }
        }



	}

}




















