package com.jpeng.jptabbar;

/**
 * Author jpeng
 * Date: 16-11-13
 * E-mail:peng8350@gmail.com
 * 点击回调监听
 */
public interface OnTabSelectListener {
    /**
     *  用户每次点击不同的Tab将会触发这个方法
     * @param index
     * 当前选择的TAB的索引值
     */
    void onTabSelect(int index);

}
