package com.jpeng.jptabbar.animate;

import android.view.View;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 */
public class RotateAnimater implements Animatable {
    @Override
    public void playAnimate(View target,int Duration) {

        ViewHelper.setPivotX(target,target.getLayoutParams().width/2);
        ViewHelper.setPivotY(target,target.getLayoutParams().height/2+target.getPaddingTop()/2);
        AnimatorSet set = new AnimatorSet();

        set.playSequentially(

                ObjectAnimator.ofFloat(target,"rotationX",0,180).setDuration(Duration/2),
                ObjectAnimator.ofFloat(target,"alpha",0.99f,1f).setDuration(150),
                ObjectAnimator.ofFloat(target,"rotationX",180,360).setDuration(Duration/2)
        );
        LinearInterpolator ll = new LinearInterpolator();
        set.setInterpolator(ll);
        set.start();
    }

    @Override
    public void onPageAnimate(View target,float offset) {
        ViewHelper.setRotation(target,offset*360);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true;
    }
}
