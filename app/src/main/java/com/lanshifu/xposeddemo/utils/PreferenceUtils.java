package com.lanshifu.xposeddemo.utils;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by lanshifu on 2018/10/1.
 */

public class PreferenceUtils {
    public static final String AUTO_ALL ="auto_all";
    public static final String REPLY_CONTENT ="reply_content";
    public static final String REPLY_ORDER ="reply_order";
    public static final String REPLY_ROBOT ="reply_robot";
    public static final String REPLY_DESCRIBE ="reply_describe";

    public static final String ZHIFUBAO_OPEN ="zhifubao_open";

    private static XSharedPreferences intance = null;

    public static XSharedPreferences getIntance(){
        if (intance == null){
            intance = new XSharedPreferences("com.lanshifu.xposeddemo","config");
            intance.makeWorldReadable();
        }else {
            intance.reload();
        }
        return intance;
    }

    public static boolean openAll(){
        return getIntance().getBoolean(AUTO_ALL,false);
    }

    public static String replyContent(){
        return getIntance().getString(REPLY_CONTENT,"请设置自动回复内容");
    }


    public static boolean isZhifubaoOpen(){
        return getIntance().getBoolean(ZHIFUBAO_OPEN,true);
    }


}
