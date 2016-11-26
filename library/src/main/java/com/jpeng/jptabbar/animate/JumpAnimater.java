package com.jpeng.jptabbar.animate;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现跳跃图标的动画类
 */
public class JumpAnimater extends BouncingAnimater implements Animatable {


    @Override
    public void playAnimate(final View target, final boolean selected) {
        setPlaying(true);
        buildSpring();
        getSpring().addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                if (isPlaying()) {
                    ViewHelper.setTranslationY(target, -value * 7);
                    ViewHelper.setRotationY(target, value * 180);
                }

            }
        });
        getSpring().setCurrentValue(selected?0f:1);
        getSpring().setEndValue(selected?1f:0f);

    }

    @Override
    public void onPageAnimate(final View target, float offset) {
        setPlaying(false);

        ViewHelper.setTranslationY(target, offset * -7);
        ViewHelper.setRotationY(target, offset * 180);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


}
