package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现跳跃图标的动画类
 */
public class JumpAnimater extends BouncingAnimater{


    @Override
    public void playAnimate(boolean selected) {
        setPlaying(true);
        getSpring().setEndValue(selected?1f:0f);

    }

    @Override
    public void onPageAnimate(float offset) {
        setPlaying(false);

        ViewHelper.setTranslationY(mTarget, offset * -7);
        ViewHelper.setRotationY(mTarget, offset * 180);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if (isPlaying()) {
            ViewHelper.setTranslationY(target, -currentValue * 7);
            ViewHelper.setRotationY(target, currentValue * 180);
        }
    }
}
