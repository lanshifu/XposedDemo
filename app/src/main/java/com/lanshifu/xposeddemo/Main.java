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
	private String mApkFilePath = null;
	private String mPackageName = "com.lanshifu.xposeddemo";


	@Override
	public void handleLoadPackage(final LoadPackageParam param) throws Throwable{
		//debug模式下才使用热更新，因为涉及到反射，影响性能
		if (BuildConfig.DEBUG){
			debugHook(param);
		}else {
			releaseHook(param);
        }
	}

	private void debugHook(final LoadPackageParam param){

		//查找apk路径
		if (mApkFilePath == null){
			Log.d(TAG, "debugHook: mApkFilePath = null");
			//1、从 /data/app/com.lanshifu.xposeddemo-1.apk 找
			mApkFilePath = String.format("/data/app/%s-%s.apk", mPackageName, 1);
			if (!new File(mApkFilePath).exists()) {
				//2、从 /data/app/com.lanshifu.xposeddemo-2.apk 找
				mApkFilePath = String.format("/data/app/%s-%s.apk", mPackageName, 2);
				if (!new File(mApkFilePath).exists()) {
					//3、从 /data/app/com.lanshifu.xposeddemo-1/base.apk 找
					mApkFilePath = String.format("/data/app/%s-%s/base.apk", mPackageName, 1);
					if (!new File(mApkFilePath).exists()) {
						//4、从 /data/app/com.lanshifu.xposeddemo-2/base.apk 找
						mApkFilePath = String.format("/data/app/%s-%s/base.apk", mPackageName, 2);
						if (!new File(mApkFilePath).exists()) {
							LogUtil.e("找不到apk路径，热更新失败:filePath= "+ mApkFilePath + " ,packageName="+ mPackageName);
							mApkFilePath = null;
							return;
						}
					}
				}
			}
		}

		//通过PathClassLoader 加载apk
		final PathClassLoader pathClassLoader = new PathClassLoader(mApkFilePath, ClassLoader.getSystemClassLoader());
		String className = MainModule.class.getName();
		//通过反射调用 MainModule的 handleLoadPackage 方法【重点】
		final Class<?> aClass;
		try {
			//反射调用MainModule的handleLoadPackage方法
			aClass = Class.forName(className, true, pathClassLoader);
			final Method aClassMethod = aClass.getMethod("handleLoadPackage", XC_LoadPackage.LoadPackageParam.class);
			aClassMethod.invoke(aClass.newInstance(), param);
		} catch (Exception e) {
			LogUtil.e("反射MainModule 失败："+e.getMessage());
			e.printStackTrace();
		}

	}

	private void releaseHook(final LoadPackageParam param){
		Log.d(TAG, "not hot load - >handleLoadPackage: " + param.packageName);
		MainModule module = new MainModule();
		module.handleLoadPackage(param);

	}

}




















