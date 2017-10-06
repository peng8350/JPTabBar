package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 * 实现旋转的动画类
 */
public class RotateAnimater implements Animatable{


    @Override
    public void onPressDown(View v, boolean selected) {
//        getSpring().setEndValue(180f);
    }

    @Override
    public void onTouchOut(View v, boolean selected) {
//        getSpring().setEndValue(selected?360f:0f);
    }

    @Override
    public void onSelectChanged(View v,boolean selected) {
//        getSpring().setEndValue(selected?360f:0f);

    }

    @Override
    public void onPageAnimate(View v,float offset) {
        ViewHelper.setRotation(v, offset*360);

    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }


}
