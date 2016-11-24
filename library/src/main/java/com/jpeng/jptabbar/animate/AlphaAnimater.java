package com.jpeng.jptabbar.animate;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-14.
 * Alpha动画类
 */
public class AlphaAnimater extends BouncingAnimater implements Animatable {
    @Override
    public void playAnimate(final View target, final boolean selected) {
        final Spring spring = buildSpring();
        spring.addListener(new SimpleSpringListener(){
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                if(selected)
                ViewHelper.setAlpha(target, value);
            }
        });
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

}
