package com.jpeng.jptabbar.animate;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现旋转的动画类
 */
public class RotateAnimater extends BouncingAnimater implements Animatable {

    @Override
    public void playAnimate(final View target, boolean selected) {
        setPlaying(true);
        buildSpring();
        getSpring().addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                if(isPlaying())
                ViewHelper.setRotation(target, value);
            }
        });
        getSpring().setCurrentValue(selected?0f:360f);
        getSpring().setEndValue(selected?360f:0f);

    }

    @Override
    public void onPageAnimate(View target,float offset) {
        setPlaying(false);
        ViewHelper.setRotation(target, offset*360);

    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }



}
