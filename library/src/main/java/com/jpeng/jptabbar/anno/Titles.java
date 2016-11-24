package com.jpeng.jptabbar.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jpeng on 16-11-13.
 *  标题注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Titles {

}
