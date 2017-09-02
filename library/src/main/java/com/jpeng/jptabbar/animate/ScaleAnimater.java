package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-14.
 * 实现图标缩放动画者
 */
public class ScaleAnimater extends BouncingAnimater{


    @Override
    public void onPressDown(View v, boolean selected) {
        super.onPressDown(v,selected);
        getSpring().setEndValue(0.1f);
    }

    @Override
    public void onTouchOut(View v, boolean selected) {
        super.onTouchOut(v,selected);
        getSpring().setEndValue(selected?0.2f:0f);
    }

    @Override
    public void onSelectChanged(View v,boolean selected) {
        super.onSelectChanged(v,selected);
        getSpring().setEndValue(selected?0.2f:0f);
    }

    @Override
    public void onPageAnimate(View v,float offset){
        setPlaying(false);
        ViewHelper.setScaleX(mTarget, offset*0.2f+1f);
        ViewHelper.setScaleY(mTarget, offset*0.2f+1f);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


    @Override
    public void onSpringUpdate(View target, float currentValue) {
        if (isPlaying()) {
            ViewHelper.setScaleY(target, currentValue+1);
            ViewHelper.setScaleX(target, currentValue+1);
        }
    }
}
