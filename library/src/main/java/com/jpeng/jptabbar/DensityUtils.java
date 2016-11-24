package com.jpeng.jptabbar;

import android.content.Context;
import android.support.annotation.NonNull;
/**
 * Created by jpeng on 16-11-14.
 * 密度工具类
 */
public class DensityUtils {




    public static int dp2sp(Context context, float dipValue) {
        float pxValue = dp2px(context, dipValue);
        return px2sp(context, pxValue);
    }

    public static int sp2dp(@NonNull Context context, float spValue) {
        float pxValue = sp2px(context, spValue);
        return px2dp(context, pxValue);
    }

    public static int px2dp(@NonNull Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
    }

    public static int dp2px(@NonNull Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale);
    }

    public static int px2sp(@NonNull Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale);
    }

    public static int sp2px(@NonNull Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale);
    }



}