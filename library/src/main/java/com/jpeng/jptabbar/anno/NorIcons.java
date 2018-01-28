package com.jpeng.jptabbar.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author jpeng
 * Date: 16-11-13
 * E-mail:peng8350@gmail.com
 * 没有选中图标互借
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NorIcons {
}
