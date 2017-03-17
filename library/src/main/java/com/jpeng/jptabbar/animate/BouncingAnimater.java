package com.jpeng.jptabbar.animate;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

/**
 * Created by jpeng on 16-11-20.
 * 实现弹性的动画父类
 */
public abstract class BouncingAnimater {
    //动画对象
    private Spring mSpring;
    //是否正在播放动画
    private boolean mPlaying;
    //对应的监听器

    public Spring getSpring() {
        return mSpring;
    }


    public boolean isPlaying(){
        return mPlaying;
    }

    public void setPlaying(boolean play){
        this.mPlaying = play;
        if (!play&&mSpring!=null){
            mSpring.setAtRest();
        }
    }

    public Spring buildSpring(double qcTension,double qcFriction){
        SpringSystem mSystem = SpringSystem.create();
        mSpring = mSystem.createSpring();
        mSpring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(qcTension,qcFriction));
        return mSpring;
    };
}
