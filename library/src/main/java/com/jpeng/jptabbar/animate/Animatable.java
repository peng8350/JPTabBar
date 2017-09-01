package com.jpeng.jptabbar.animate;

import android.view.View;

/**
 * Created by jpeng on 16-11-14.
 */
public interface Animatable {

    /**
     * 按下View动画行为
     */
    public void onPressDown(View v,boolean selected);

    /**
     * 松开时,在View外发生的动作行为
     */
    public void onTouchOut(View v,boolean selected);

    /**
     * 动画行为
     * 选中状态改变时回调的方法
     */
    public void playAnimate(View v, boolean selected);

    /**
     * 页面切换动画
     */
    public void onPageAnimate(View v,float offset);

    /**
     * 是否需要页面切换动画
     */
    public boolean isNeedPageAnimate();




}
