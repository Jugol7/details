package com.details.abstracttest;

import java.io.Serializable;

public interface TestImpl extends Cloneable, Serializable {

    /**
     * 在接口中默认的都是静态常量
     */
    public static final int M = 1;
    static int s =1;
    int q = 1;

    void method();
}
