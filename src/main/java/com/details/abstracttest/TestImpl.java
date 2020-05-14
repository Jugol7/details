package com.details.abstracttest;

import java.io.Serializable;

public interface TestImpl extends Cloneable, Serializable {

    public static final int M = 1;
    static int s =1;
    int q = 1;

    void method();
}
