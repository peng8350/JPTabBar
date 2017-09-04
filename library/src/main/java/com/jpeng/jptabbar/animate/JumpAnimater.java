package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现跳跃图标的动画类
 */
public class JumpAnimater extends BouncingAnimater{


    @Override
    public void onPressDown(View v, boolean selected) {
        super.onPressDown(v,selected);
        getSpring().setEndValue(0.3f);
    }

    @Override
    public void onTouchOut(View v, boolean selected) {
        super.onTouchOut(v,selected);
        getSpring().setEndValue(selected?1f:0f);
    }

    @Override
    public void onSelectChanged(View v,boolean selected) {
        super.onSelectChanged(v,selected);
        getSpring().setEndValue(selected?1f:0f);

    }

    @Override
    public void onPageAnimate(View v,float offset) {
        setPlaying(false);

        ViewHelper.setTranslationY(mTarget, offset * -7);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if (isPlaying()) {
            ViewHelper.setTranslationY(target, -currentValue * 7);
        }
    }
}
