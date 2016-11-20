package com.jpeng.jptabbar.animate;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-14.
 */
public class AlphaAnimater implements Animatable {
    @Override
    public void playAnimate(View target,boolean selected) {
        Spring spring = buildSpring(target);
        spring.setCurrentValue(0f);
        spring.setEndValue(1f);
    }

    @Override
    public void onPageAnimate(View target,float offset) {
    }

    @Override
    public boolean isNeedPageAnimate() {
        return false;
    }

    public Spring buildSpring(final View target){
        SpringSystem mSystem = SpringSystem.create();
        Spring spring = mSystem.createSpring();
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(30, 2));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                ViewHelper.setAlpha(target, value);
                ViewHelper.setAlpha(target, value);
            }
        });
        return spring;
    }
}
