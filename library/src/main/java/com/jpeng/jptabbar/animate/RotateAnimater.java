package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

import static android.R.attr.value;

/**
 * Created by jpeng on 16-11-15.
 * 实现旋转的动画类
 */
public class RotateAnimater extends BouncingAnimater{

    @Override
    public void playAnimate(boolean selected) {
        setPlaying(true);
        getSpring().setEndValue(selected?360f:0f);

    }

    @Override
    public void onPageAnimate(float offset) {
        setPlaying(false);
        ViewHelper.setRotation(mTarget, offset*360);

    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if(isPlaying())
            ViewHelper.setRotation(target, currentValue);
    }
}
