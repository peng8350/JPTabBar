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

/**
 * Created by jpeng on 16-11-14.
 */
public class Tab3Pager extends Fragment implements RadioGroup.OnCheckedChangeListener{
    JPTabBar mTabBar;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab3,null);
        init(layout);
        return layout;
    }

    /**
     * 初始化
     */
    private void init(View layout) {
        mTabBar = ((MainActivity)getActivity()).getTabbar();

        ((RadioGroup)layout.findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(this);
        ((RadioGroup)layout.findViewById(R.id.radioGroup2)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.radioButton1:
                mTabBar.setPageAnimateEnable(true);
                break;

            case R.id.radioButton2:
                mTabBar.setPageAnimateEnable(false);
                break;

            case R.id.radioButton3:
                mTabBar.setGradientEnable(true);
                break;
            case R.id.radioButton4:
                mTabBar.setGradientEnable(false);
                break;
        }
    }
}
