package com.jpeng.jptabbar;

/**
 * Author jpeng
 * Date: 16-11-13 下午1:41
 * E-mail:peng8350@gmail.com
 * Tab异常类
 */
public class TabException extends NullPointerException {
    public TabException() {
        super();
    }

    public TabException(String detailMessage) {
        super(detailMessage);
    }
}
