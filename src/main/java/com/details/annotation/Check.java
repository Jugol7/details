package com.details.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/***
 * 参数基本校验
 * @author zlp
 * @date 14:10 2020/1/13
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
public @interface Check {
    String[] value();
}
