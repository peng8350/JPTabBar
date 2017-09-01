package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现翻转的动画类
 */
public class FlipAnimater extends BouncingAnimater{

    @Override
    public void onPressDown(View v) {

    }

    @Override
    public void onTouchOut(View v) {

    }

    @Override
    public void playAnimate(View v,boolean selected) {
        setPlaying(true);
        getSpring().setEndValue(selected?1f:0f);
    }

    @Override
    public void onPageAnimate(View v,float offset) {
        setPlaying(false);
        ViewHelper.setRotationY(v, 180*offset);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true ;
    }

    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if(isPlaying())
            ViewHelper.setRotationY(target, currentValue*180);
    }
}
