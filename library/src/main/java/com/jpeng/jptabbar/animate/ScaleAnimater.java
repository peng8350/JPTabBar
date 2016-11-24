package com.jpeng.jptabbar.animate;

import android.view.View;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-14.
 * 实现图标缩放动画者
 */
public class ScaleAnimater extends BouncingAnimater implements Animatable {

    @Override
    public void playAnimate(final View target, boolean selected) {
        setPlaying(true);
        buildSpring();
        getSpring().addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                if (isPlaying()) {
                    ViewHelper.setScaleY(target, value);
                    ViewHelper.setScaleX(target, value);
                }
            }
        });
        getSpring().setCurrentValue(selected?1f:1.2f);
        getSpring().setEndValue(selected?1.2f:1f);
    }

    @Override
    public void onPageAnimate(View target,float offset){
        setPlaying(false);
        ViewHelper.setScaleX(target, offset*0.2f+1f);
        ViewHelper.setScaleY(target, offset*0.2f+1f);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


}
