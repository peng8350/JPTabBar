package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jpeng on 16-11-14.
 */
public class ScaleAnimater implements Animatable {

    @Override
    public void playAnimate(View target, int Duration) {

        ViewHelper.setPivotX(target,target.getLayoutParams().width/2);
        ViewHelper.setPivotY(target,target.getLayoutParams().height/2);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(target,"scaleX",0.2f,1f).setDuration(Duration),
                ObjectAnimator.ofFloat(target,"scaleY",0.2f,1f).setDuration(Duration),
                ObjectAnimator.ofFloat(target,"alpha",0.3f,1f).setDuration(Duration)
        );

        set.start();

    }

}
