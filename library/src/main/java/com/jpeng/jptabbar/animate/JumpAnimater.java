package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

import static android.R.attr.value;

/**
 * Created by jpeng on 16-11-15.
 * 实现跳跃图标的动画类
 */
public class JumpAnimater extends BouncingAnimater{


    @Override
    public void playAnimate(final View target, final boolean selected) {
        setPlaying(true);
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


    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if (isPlaying()) {
            ViewHelper.setTranslationY(target, -value * 7);
            ViewHelper.setRotationY(target, value * 180);
        }
    }
}
