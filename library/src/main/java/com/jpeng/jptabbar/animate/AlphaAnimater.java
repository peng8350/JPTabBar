package com.jpeng.jptabbar.animate;

import android.view.View;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by jpeng on 16-11-14.
 */
public class AlphaAnimater implements Animatable {
    @Override
    public void playAnimate(View target,int Duration) {
        ObjectAnimator.ofFloat(target,"alpha",0f,1f).setDuration(Duration).start();
    }

}
