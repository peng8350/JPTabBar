package com.jpeng.jptabbar;

/**
 * Author jpeng
 * Date: 16-11-15
 * E-mail:peng8350@gmail.com
 * 徽章消失回调监听者
 */
public interface BadgeDismissListener {
    /**
     * TabItem徽章消失的回调
     */
    void onDismiss(int position);
}
