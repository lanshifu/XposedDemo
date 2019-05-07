本文为纯技术分享，适合对xposed有兴趣的读者，

如果对xposed不感兴趣，请点击左上角返回按钮~


### 前言

在一个夜黑风高的晚上，我的基友突然给我发了一个叫“🍅社区”的app，这是什么玩意？找了一张可以上墙的图


![image.png](https://upload-images.jianshu.io/upload_images/11562793-14cd981a8e688ff0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/200)



我凑，是个美女直播软件，迫不及待的我，一阵点点点，发现最亮点的功能是第二个tab页，是类似抖音的短视频，找了一张正经的图，抖音都没这么正经吧。。。

![](https://upload-images.jianshu.io/upload_images/11562793-78389e47fa67e29a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/200)




我去，做的效果跟抖音一毛一样，上下滑动切换视频，聊这个，那我可不困了，在滑动了差不多20个视频之后，出现了这个
![image.png](https://upload-images.jianshu.io/upload_images/11562793-f15d2f69cf849da0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/200)

卧槽，这能忍，作为一个Android开发攻城狮，充值是不可能充值的。太晚了，打算明天再破解它的收费功能。


于是，第二天很早就起床打开电脑，这篇文章就开始了...


### 反编译
将apk发送到电脑，然后打开jadx-gui[传送门](https://github.com/skylot/jadx)，直接选择这个apk，打开


发现源码是这个样子的
![image.png](https://upload-images.jianshu.io/upload_images/11562793-3c5c8fa409776b6a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


熟悉逆向的朋友们肯定猜到了，这个apk使用了腾讯加固，所以反编译出来只有腾讯加固的几个类，看不到目标源码。  

那怎么办？

既然加固了，第一步就是要给它脱壳

### 脱壳(需要xposed支持)
#### FDex2
>通过Hook ClassLoader的loadClass方法，反射调用getDex方法取得Dex(com.android.dex.Dex类对象)，在将里面的dex写出。

下载地址:
>链接:https://pan.baidu.com/s/1f5dmytN4gt1V4Z5gJ46Ubw  密码:asbw

下载安装，在xposedinstaller中勾选模块并重启，然后打开FDex2选择番茄社区，然后重启番茄社区，即可在对应目录找到dex文件的踪影
这个过程不是本文重点，可以参考这篇文章  
[Android APK脱壳--腾讯乐固、360加固一键脱壳](https://www.jianshu.com/p/138c9de2c987)

### 分析代码
上一步通过FDex2，成功脱壳
![image.png](https://upload-images.jianshu.io/upload_images/11562793-e709f9590c1f0623.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


拿到三个dex，依次用jadx-gui打开，根据包名，可以找到对应Activity的位置
![image.png](https://upload-images.jianshu.io/upload_images/11562793-2bf35307b7df814d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如何得到Activity名称，这个可以用无障碍，也可以直接过滤日志，比如，打开界面，然后日志过滤“start|activity”
```
D: pid=30739, uid=10274, component=ComponentInfo{com.one.tomato/com.one.tomato.ui.StartUpActivity}
I: AppChangeImpl:pid: 30739 uid: 10274 pkg: com.one.tomato class: com.one.tomato.ui.StartUpActivity
D: activityResumed:pid=30739, uid=10274, component=ComponentInfo{com.one.tomato/com.one.tomato.ui.StartUpActivity}
D: checkIsMonitorVideoScence input :com.one.tomato,com.one.tomato.ui.StartUpActivity
D: handleActivityChange,  curPackage:com.one.tomato, curClass:com.one.tomato.ui.StartUpActivity
D: checkIsMonitorAPKScence input :com.one.tomato,com.one.tomato.ui.StartUpActivity
D: handleActivityChange, it is not a care app or scence
D: handleActivityChange,  curr mAppType:-1, lastType:-1
```
com.one.tomato.ui.StartUpActivity 就是主页了

主页一共有5个tab，每个tab应该对应一个fragment，我们要先找到第二个tab对应的fragment


![image.png](https://upload-images.jianshu.io/upload_images/11562793-f0f7b070e952a8b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


看到点击事件，通过命名方式可以猜到这个是底部tab的点击事件，点一下应该会切换显示fragment

![image.png](https://upload-images.jianshu.io/upload_images/11562793-b583b1b4007a8ac2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

果然不出我所料，第二个tab是PapaTabFragment，搜索一下
![image.png](https://upload-images.jianshu.io/upload_images/11562793-25944f1dfa7f3b19.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

打开看看
![](https://upload-images.jianshu.io/upload_images/11562793-e84d9483acbb286f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

虽然代码被混淆了，但是可以猜到这里是初始化的方法，有头像和收藏图标的点击事件，当然，这个不是重点，哈哈，我们还是要先分析一下什么时候触发这个弹窗，弹窗的条件是播放次数到达到一个值，根据这个条件，快速浏览一下PapaTabFragment这个类，代码不多，只有1千行多一点

![image.png](https://upload-images.jianshu.io/upload_images/11562793-c578601b4ad72489.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
发现疑点，LookTimes是观看时间，VideoPlayCountUtils是播放次数。

VideoPlayCountUtils 是一个单例，所以看b和c方法
![image.png](https://upload-images.jianshu.io/upload_images/11562793-5a99a79e82fe6196.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

“video_play_count” 这么明显的字眼，播放次数，这个次数是从PreferencesUtil中获取的，也就是存在sp中，

![image.png](https://upload-images.jianshu.io/upload_images/11562793-768ea80a879a27dd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


PreferencesUtil 中的e方法，如果登录信息不为空，就返回信息中的id，空就返回0，所以第一次打开才会是0，可以观看，之后这个LoginInfo不为空了，开始统计观看次数了。

so，让它总是返回0？


### 上代码
Xposed模块开发基础就不说了，默认你已经会了，不会自己去查一下，Xposed基础不是本文的重点。
```
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
                LogUtil.d(TAG,"afterHookedMethod 视频次数返回 0 ");
                param.setResult(0);


            }
        });
    }

protected static void hook_method(String className, ClassLoader classLoader, String methodName,
                             Object... parameterTypesAndCallback) {
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            XposedBridge.log(e);
            xLog(e.getMessage());
        }
    }
```


然后其实会发现一个问题，class not found,找了很久，最后才反应过来，因为apk经过加固，必须要用壳的ClassLoader来加载类，因为真正的代码是腾讯加固程序启动后它去加载真正的dex文件的。



![image.png](https://upload-images.jianshu.io/upload_images/11562793-76c5ea01eb21a8dd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这里我们可以hook TxAppEntry 的 attachBaseContext 方法

```
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

```

然后安装试了一下，卧槽，真的无限制观看了，我是V8?

确实成功了，效果图就不发了，大家可以动手试试。

---

另外，跳过首页广告和打开日志的hook点我也很快找到了

```
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

                    LogUtil.d(method.getName());
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
```

不是很难找，这里就留给大家自己去实践学习了。

---
注意：不管🍅社区后期是否停止服务（你懂的），本文只是xposed技术分享，拒绝黄赌毒，源码已开源。

源码学习：[点这里](https://github.com/lanshifu/XposedDemo) 

另外，对于伸手党，已经编译好的xposed模块放群里了，感兴趣的可以到群里下载

番茄模块交流群： 

![](https://upload-images.jianshu.io/upload_images/11562793-44ac17945f6ef5c7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/100)




声明：本文内容只供参考学习，不能用于商业用途哦，如有问题，请留言。

