package com.details.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @Author zlp
 * @Description:
 * @Date 16:03 2020/9/16
 */
@Aspect
public class TestAspect {

    @Before("execution(* com.details.aop.AopTest.*(..))")
    public void before(){

        System.out.println("切面前置操作");

    }


    @After("execution(* com.details.aop.AopTest.*(..))")
    public void after(){

        System.out.println("切面后置操作");

    }


}
