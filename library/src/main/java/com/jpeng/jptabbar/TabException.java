package com.jpeng.jptabbar;

/**
 * Created by jpeng on 16-11-13.
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
