package com.details.generic;

/***
 * 由此可得出，我们可以得到无限长度的 元组  只需要增加参数类型就可
 * @author zlp
 * @date 15:14 2020/4/19
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B>{
    //这种方式比使用 private set/get  更加安全，更加简洁明了
    final C c;

    public ThreeTuple(A a, B b, C c) {
        super(a,b);
        this.c = c;
    }
    public String toString(){
        return ""+a+","+b+","+c;
    }
}
