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

    /**
     * 这个方法主要用来拦截Tab选中的事件
     * 返回true,tab将不会被选中,onTabSelect也不会被回调
     * 默认返回false
     * @param index 点击选中的tab下标
     * @return 布尔值
     */
    boolean onInterruptSelect(int index);

}
