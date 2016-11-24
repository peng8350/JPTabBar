package com.jpeng.jptabbar;

import android.view.View;

/**
 * Created by jpeng on 16-11-13.
 * 点击回调监听
 */
public interface OnTabSelectListener {
    /**
     *  用户每次点击不同的Tab将会触发这个方法
     * @param index
     * 当前选择的TAB的索引值
     */
    void onTabSelect(int index);

    /**
     * 用户点击中间的图标
     */
    void onClickMiddle(View middleBtn);



}
