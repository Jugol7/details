package com.details.abstracttest;

public class TestImplimpl implements TestImpl {
    @Override
    public void method() {
        int s = TestImpl.s;
    }
}
