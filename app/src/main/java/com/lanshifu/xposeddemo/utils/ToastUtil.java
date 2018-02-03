package com.lanshifu.xposeddemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lanxiaobin on 2017/9/7.
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showShortToast(Context context,CharSequence hint) {
        showToast(context, hint, Toast.LENGTH_SHORT);
    }



    private static void showToast(Context context, CharSequence hint, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, hint, duration);
        } else {
            mToast.setText(hint);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

}
