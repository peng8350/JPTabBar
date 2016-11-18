package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-15.
 */
public class JumpAnimater implements Animatable {

    @Override
    public void playAnimate(View target,int Duration) {
        ViewHelper.setPivotX(target,target.getLayoutParams().width/2);
        ViewHelper.setPivotY(target,target.getLayoutParams().height/2);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(

                ObjectAnimator.ofFloat(target, "translationY", -10).setDuration(Duration/4),
                ObjectAnimator.ofFloat(target, "translationY", 0).setDuration(Duration/6),
                ObjectAnimator.ofFloat(target, "translationY", -20).setDuration(Duration/4),
                ObjectAnimator.ofFloat(target, "translationY", 0).setDuration(Duration/6)
        );
        set.start();
    }

    @Override
    public void onPageAnimate(View target,float offset) {

    }

    @Override
    public boolean isNeedPageAnimate() {
        return false;
    }

}
