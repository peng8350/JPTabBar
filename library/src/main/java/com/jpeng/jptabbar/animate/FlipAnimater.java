package com.jpeng.jptabbar.animate;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现翻转的动画类
 */
public class FlipAnimater extends BouncingAnimater implements Animatable{

    @Override
    public void playAnimate(final View target, boolean selected) {
        setPlaying(true);
        buildSpring();
        getSpring().addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                if(isPlaying())
                ViewHelper.setRotationY(target, value*180);
            }
        });
        getSpring().setCurrentValue(selected?0f:1f);
        getSpring().setEndValue(selected?1f:0f);
    }

    @Override
    public void onPageAnimate(View target,float offset) {
        setPlaying(false);
        ViewHelper.setRotationY(target, 180*offset);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true ;
    }

}
