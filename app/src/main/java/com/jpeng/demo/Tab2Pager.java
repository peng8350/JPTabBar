package com.jpeng.demo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.animate.*;

/**
 * Created by jpeng on 16-11-14.
 */
public class Tab2Pager extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mGroup;

    private JPTabBar mTabBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =inflater.inflate(R.layout.tab2,null);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mTabBar = ((MainActivity)getActivity()).getTabbar();
        mGroup = (RadioGroup) layout.findViewById(R.id.radioGroup);
        mGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.radioButton1:
                mTabBar.setCustomAnimate(new ScaleAnimater());
                break;
            case R.id.radioButton2:
                mTabBar.setCustomAnimate(new JumpAnimater());
                break;
            case R.id.radioButton3:
                mTabBar.setCustomAnimate(new FlipAnimater());
                break;
            case R.id.radioButton4:
                mTabBar.setCustomAnimate(new RotateAnimater());
                break;
        }
    }

}
