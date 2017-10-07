package com.jpeng.jptabbar.animate;

import android.view.View;

/**
 * Created by jpeng on 16-11-14.
 */
public interface Animatable {
    /**
     * 按下View动画行为
     */
    void onPressDown(View v,boolean selected);

    /**
     * 松开时,手指触摸在View外发生的动作行为
     */
    void onTouchOut(View v,boolean selected);

    /**
     * 当选中状态发生改变时
     */
    void onSelectChanged(View v, boolean selected);

    /**
     * 页面切换动画
     */
    void onPageAnimate(View v,float offset);

    /**
     * 是否需要页面切换动画
     */
    boolean isNeedPageAnimate();

}
