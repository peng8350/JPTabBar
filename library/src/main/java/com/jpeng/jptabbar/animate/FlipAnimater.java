package com.jpeng.jptabbar.animate;

import android.view.View;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 */
public class FlipAnimater implements Animatable{
    @Override
    public void playAnimate(View target,int Duration) {
        ViewHelper.setPivotX(target,target.getLayoutParams().width/2);
        ViewHelper.setPivotY(target,target.getLayoutParams().height/2);
        AnimatorSet set = new AnimatorSet();

        set.playTogether(
                ObjectAnimator.ofFloat(target,"alpha",0f,1f),
                ObjectAnimator.ofFloat(target,"rotationY",0f,360f)
        );

        set.setInterpolator(new LinearInterpolator());

        set.setDuration(Duration).start();
    }

    @Override
    public void onPageAnimate(View target,float offset) {
        ViewHelper.setRotationY(target,offset*180);
    }

    @Override
    public boolean isNeedPageAnimate() {
        return true ;
    }


}
