package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

import static android.R.attr.value;

/**
 * Created by jpeng on 16-11-14.
 * 实现图标缩放动画者
 */
public class ScaleAnimater extends BouncingAnimater{

    @Override
    public void playAnimate(final View target, boolean selected) {
        setPlaying(true);
        getSpring().setEndValue(selected?0.2f:0f);
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


    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if (isPlaying()) {
            ViewHelper.setScaleY(target, value+1);
            ViewHelper.setScaleX(target, value+1);
        }
    }
}
