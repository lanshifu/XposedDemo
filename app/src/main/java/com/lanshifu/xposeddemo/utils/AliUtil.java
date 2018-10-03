package com.lanshifu.xposeddemo.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanshifu.xposeddemo.bean.CollectionBean;
import com.lanshifu.xposeddemo.bean.CollectionResultBean;
import com.lanshifu.xposeddemo.bean.FriendDetailBean;
import com.lanshifu.xposeddemo.bean.FriendRankBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanshifu on 2018/9/29.
 */

public class AliUtil {

    static boolean mIsWebViewRefresh = false;
    static List<String> mFriendsRankUseridList = new ArrayList<>();
    // userId 跟 能量id应该绑定，用bean
    static ArrayList<CollectionBean> mCanCollectionIdList = new ArrayList<>();

    static int pageCount = 0;

    public static Object curH5Fragment;
    public static Object curH5PageImpl;
    public static Object curH5WebView;
    public static Object activity;
    static int mCollectionCount = 0;

    public static boolean isBankList(String response) {

        try {
            FriendRankBean friendRankBean = new Gson().fromJson(response, FriendRankBean.class);
            if (friendRankBean.getFriendRanking() == null){
                return false;
            }
        } catch (Exception e) {
            LogUtil.e("isBankList 格式化FriendRankBean失败 :" + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean isUserDetail(String response) {
        try {
            FriendDetailBean friendDetailBean = new Gson().fromJson(response, FriendDetailBean.class);
            if (null == friendDetailBean.getBizNo()){

                return false;
            }

        } catch (Exception e) {
            LogUtil.e("isUserDetail error" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 一页好友信息，解析得到有可收取的好友id
     * @param loader
     * @param response
     */
    public static void autoGetCanCollectUserIdList(final ClassLoader loader, String response) {

        if (mIsWebViewRefresh) {
            //如果已经刷新了，又回来response，表示主动刷新webview结束来到的逻辑
            finishWork();
            return;
        }
        boolean isSuccess = parseFriendBankDataResponse(loader,response);
        LogUtil.d("autoGetCanCollectUserIdList isSuccess = " + isSuccess + "，response = " + response);

        if (isSuccess) {
            LogUtil.d("isSuccess 开始分页获取可以收取能量的好友信息");
            //分页，这里可能是同步的，要子线程，不确定
            new Thread() {
                @Override
                public void run() {
                    rpcCall_FriendRankList(loader);
                }
            }.start();
        } else {

            //理论上到达好友最后一页
            LogUtil.d("解析不到好友信息，说明到了最后一页 收取的能量数：" + mCollectionCount );

            StringBuilder result = new StringBuilder();
            result.append("有可收能量的好友：："+mFriendsRankUseridList.size());
            result.append("\n");
            result.append("一共有能量球数："+mCanCollectionIdList.size());
            result.append("\n");
            result.append("一共收取的能量数：" + mCollectionCount);

            mIsWebViewRefresh = true;
            //重置参数
            mCollectionCount = 0;
            mIsWebViewRefresh = false;
            mCanCollectionIdList.clear();
            mFriendsRankUseridList.clear();
            pageCount = 0;
            LogUtil.d("重置参数");


            toast(loader,result.toString());



            refreshWebView();




        }
    }

    private static void refreshWebView(){
        // mWebView.reload(); //刷新
        if (curH5WebView != null){

            try {
                Class<?> clazz = curH5WebView.getClass();
                Method reload = clazz.getMethod("reload");
                reload.invoke(curH5WebView);
                LogUtil.d("调用 刷新webview");

            } catch (Exception e) {
                LogUtil.e("调用 刷新webview异常：" + e.getMessage());
            }

        }else {
            LogUtil.e("webview 为空");
        }

    }

    /**
     * 获取指定用户可以收取的能量信息
     *
     * @param loader
     * @param userId rpcCall: params:alipay.antmember.forest.h5.queryNextAction,[{"av":"5","ct":"android","userId":"2088012502446033"}],,true,{},null,false,com.alipay.mobile.nebulacore.core.H5PageImpl@2ab462b,0,,false,-1
     *               <p>
     *               rpcCall: params:alipay.antmember.forest.h5.pageQueryDynamics,[{"av":"5","ct":"android","pageSize":3,"startIndex":0,"userId":"2088012502446033"}],,true,{},null,false,com.alipay.mobile.nebulacore.core.H5PageImpl@2ab462b,0,,false,-1
     */
    public static void rpcCall_CanCollectEnergy(ClassLoader loader, String userId) {

        try {
            Method rpcCallMethod = getRpcCallMethod(loader);
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject();
            json.put("av", "5");
            json.put("ct", "android");
            json.put("pageSize", 3);
            json.put("startIndex", 0);
            json.put("userId", userId);
            jsonArray.put(json);
            LogUtil.d("获取指定用户可以收取的能量信息 pcCall_CanCollectEnergy 参数： " + jsonArray);

            rpcCallMethod.invoke(null, "alipay.antmember.forest.h5.queryNextAction",
                    jsonArray.toString(), "", true, null, null, false, curH5PageImpl, 0, "", false, -1);

            rpcCallMethod.invoke(null, "params:alipay.antmember.forest.h5.pageQueryDynamics",
                    jsonArray.toString(), "", true, null, null, false, curH5PageImpl, 0, "", false, -1);


        } catch (Exception e) {
            LogUtil.e("rpcCall_CanCollectEnergy 出错：" + e.getMessage());
        }
    }


    /**
     * 获取分页好友信息
     *
     * @param loader 2.下拉好友，有分页pageSizem，startPoint
     *               rpcCall: params:alipay.antmember.forest.h5.queryEnergyRanking,[{"av":"5","ct":"android","pageSize":20,"startPoint":"21"}],,true,{},null,false,com.alipay.mobile.nebulacore.core.H5PageImpl@d20514,0,,false,-1
     */
    private static void rpcCall_FriendRankList(ClassLoader loader) {

        try {
            Method rpcCallMethod = getRpcCallMethod(loader);
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject();
            json.put("av", "5");
            json.put("ct", "android");
            json.put("pageSize", 20);
            //从第一页开始加载
            json.put("startPoint", ((pageCount++) * 20 + 1) + "");
            jsonArray.put(json);
            LogUtil.d("rpcCall_FriendRankList 参数：" + jsonArray);


            rpcCallMethod.invoke(null, "alipay.antmember.forest.h5.queryEnergyRanking", jsonArray.toString(),
                    "", true, null, null, false, curH5PageImpl, 0, "", false, -1);

        } catch (Exception e) {
            LogUtil.e(" rpcCall_FriendRankList error : " + e.getMessage());
        }

    }

    /**
     * 利用反射获取对应H5PageImpl对象，最后调用rpcCall方法发送命令请求
     *
     * @param loader
     * @return
     */
    private static Method getRpcCallMethod(ClassLoader loader) {
        try {
            Field af = curH5Fragment.getClass().getDeclaredField("a");
            af.setAccessible(true);
            Object viewHolder = af.get(curH5Fragment);

            Field hf = viewHolder.getClass().getDeclaredField("h");
            hf.setAccessible(true);
            curH5PageImpl = hf.get(viewHolder);



            Class<?> h5PageClazz = loader.loadClass("com.alipay.mobile.h5container.api.H5Page");
            Class<?> jsonClazz = loader.loadClass("com.alibaba.fastjson.JSONObject");
            Class<?> rpcClazz = loader.loadClass("com.alipay.mobile.nebulabiz.rpc.H5RpcUtil");
            if (curH5PageImpl != null) {

                // curH5PageImpl 下有一个 H5WebView  f
//                Field ff = curH5PageImpl.getClass().getDeclaredField("f");
//                ff.setAccessible(true);
//                curH5WebView = ff.get(curH5PageImpl);  //保存当前webview


//                try {
//                    Field cf = curH5PageImpl.getClass().getDeclaredField("c");
//                    cf.setAccessible(true);
//                    activity = cf.get(curH5PageImpl);  //保存当前webview
//                } catch (Exception e) {
//                    LogUtil.d("获取activity对象失败 " + e.getMessage());
//                }

                // public static com.alipay.mobile.nebulabiz.rpc.H5Response
                // rpcCall(java.lang.String r17, java.lang.String r18, java.lang.String r19, boolean r20,
                // com.alibaba.fastjson.JSONObject r21, java.lang.String r22, boolean r23, com.alipay.mobile.h5container.api.H5Page r24,
                // int r25, java.lang.String r26, boolean r27, int r28) {

                Method callM = rpcClazz.getMethod("rpcCall", String.class, String.class, String.class, boolean.class,
                        jsonClazz, String.class, boolean.class, h5PageClazz,
                        int.class, String.class, boolean.class, int.class);



                return callM;
            }


        } catch (Exception e) {
            LogUtil.e("getRpcCallMethod error " + e.getMessage());
        }
        return null;
    }

    public static void autoGetCanCollectBubbleIdList(ClassLoader loader, String response) {
        try {
            FriendDetailBean friendDetailBean = new Gson().fromJson(response, FriendDetailBean.class);
            LogUtil.d("friendDetailBean " + friendDetailBean.getBubbles().size() + ",response = " + response);
            //遍历能力，看哪些是可以收取的
            for (FriendDetailBean.BubblesBean bubblesBean : friendDetailBean.getBubbles()) {

                if ("AVAILABLE".equalsIgnoreCase(bubblesBean.getCollectStatus()) || bubblesBean.isCanHelpCollect()) {
                    long id = bubblesBean.getId();
                    CollectionBean collectionBean = new CollectionBean(bubblesBean.getUserId(), id);
                    if (!mCanCollectionIdList.contains(collectionBean)) {
                        LogUtil.d("能量id已经获取到 " + id);
                        mCanCollectionIdList.add(collectionBean);

                    }

                    LogUtil.d("调用收取能量接口 " + collectionBean.toString());
                    rpcCall_CollectEnergy(loader, collectionBean.userId, collectionBean.bubbleIds);

                }
            }
        } catch (Exception e) {
            LogUtil.e("autoGetCanCollectBubbleIdList解析失败 " + e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * 解析好友信息，循环吧所有有能量的好友信息解析完
     *
     * @param response
     * @return
     */
    private static boolean parseFriendBankDataResponse(ClassLoader loader,String response) {
        try {
            FriendRankBean friendRankBean = new Gson().fromJson(response, FriendRankBean.class);
            LogUtil.d("本次解析好友数量 " + friendRankBean.getFriendRanking().size());
            if (friendRankBean.getFriendRanking().size() == 0) {
                //没有好友了，说明到达最后一页
                return false;
            }
            for (FriendRankBean.FriendRankingBean friendRankingBean : friendRankBean.getFriendRanking()) {
                if (friendRankingBean.isCanCollectEnergy()) {
                    String userId = friendRankingBean.getUserId();
                    LogUtil.d("好友有能量可以收取 userId = " +userId);

                    if (!mFriendsRankUseridList.contains(userId)) {
                        mFriendsRankUseridList.add(userId);
                    }

                    //直接获取好友能量信息
                    rpcCall_CanCollectEnergy(loader, userId);
                }
            }
        } catch (Exception e) {
            LogUtil.e("parseFriendBankDataResponse error " + e.getMessage());
            return false;
        }
        return true;
    }

    private static void finishWork() {
        LogUtil.d("finishWork");
        LogUtil.d("总共搜集了 "+ mCollectionCount + " g 能量") ;





    }

    /**
     * 收取能量请求
     *
     * @param loader
     * @param userId
     * @param bubbleId rpcCall: params:alipay.antmember.forest.h5.collectEnergy,
     *                 [{"av":"5","bubbleIds":[118863443],"ct":"android","userId":2088702958481141}],,true,{},null,false,com.alipay.mobile.nebulacore.core.H5PageImpl@c2fd6ef,0,,false,-1
     */
    public static void rpcCall_CollectEnergy(final ClassLoader loader, String userId, Long bubbleId) {
        try {
            Method rpcCallMethod = getRpcCallMethod(loader);
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject();
            json.put("av", "5");
            json.put("bubbleIds", bubbleId);
            json.put("ct", "android");
            json.put("userId", userId);
            jsonArray.put(json);
            LogUtil.d("rpcCall_CollectEnergy 参数：" + jsonArray);

            rpcCallMethod.invoke(null, "alipay.antmember.forest.h5.collectEnergy",
                    jsonArray.toString(),
                    "", true, null, null, false, curH5PageImpl, 0, "", false, -1);

        } catch (Exception e) {
            LogUtil.e(" rpcCall_CollectEnergy error : " + e.getMessage());
        }
    }

    /**
     * 是否是收取能量的数据
     * @return
     */
    public static boolean isCollectResult(String response) {
        try {
            CollectionResultBean collectionResultBean = new Gson().fromJson(response, CollectionResultBean.class);
            if (collectionResultBean.getFailedBubbleIds() == null ||
                    collectionResultBean.getTreeEnergy() == null){
                return false;
            }
        } catch (Exception e) {
            LogUtil.e("格式化失败 isCollectResult " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 计算收集了多少能量
     * @param response
     */
    public static void calcuCollectionResult(String response) {
        LogUtil.d("收能量结果解析");
        CollectionResultBean collectionResultBean = new Gson().fromJson(response, CollectionResultBean.class);
        if (collectionResultBean.getResultCode().equalsIgnoreCase("SUCCESS") || collectionResultBean.isSuccess()){
            for (CollectionResultBean.BubblesBean bubblesBean : collectionResultBean.getBubbles()) {
                int collectedEnergy = bubblesBean.getCollectedEnergy();
                LogUtil.d("成功收集能量 "+ collectedEnergy);
                mCollectionCount += collectedEnergy;

            }
        }else {
            LogUtil.e("收取能量失败");
        }

    }


    private static void toast(ClassLoader loader,String test){

        try {
            Class<?> h5Utils = loader.loadClass("com.alipay.mobile.nebula.util.H5Utils");
            Method getContext = h5Utils.getMethod("getContext");
            getContext.setAccessible(true);
            Object result = getContext.invoke(null);

            Looper.prepare();
            Toast.makeText((Context)result,test,Toast.LENGTH_LONG).show();
            Looper.loop();

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("toast 失败 "+ e.getMessage());
        }
    }



    //stealingAnimal
    
}
