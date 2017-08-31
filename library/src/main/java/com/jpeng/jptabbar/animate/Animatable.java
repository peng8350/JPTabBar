package com.jpeng.jptabbar.animate;

/**
 * Created by jpeng on 16-11-14.
 */
public interface Animatable {

    /**
     * 点击播放动画
     */
    public void playAnimate(boolean selected);

    /**
     * 页面切换时播放动画
     */
    public void onPageAnimate(float offset);

    /**
     * 是否需要页面切换动画
     */
    public boolean isNeedPageAnimate();




}
