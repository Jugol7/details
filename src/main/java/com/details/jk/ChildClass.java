package com.details.jk;

import java.lang.reflect.Field;


/**
 * 代码块执行顺序总结：
 * 父类的静态成员变量—-> 父类静态代码块—-> 子类静态成员变量—-> 子类静态代码块—> 父类非静态变量—-> 父类非静态代码块—-> 父类构造方法—-> 子类非静态变量—-> 子类非静态代码块—-> 子类构造方法。
 *
 * 代码块注意点：
 * 1. 在类第一次调用时，静态代码块只执行这一次。
 * 2. 静态代码块和静态方法只能调用静态变量，因为它们执行时非静态变量还没初始化；
 * 3. 非静态代码块和非静态方法可以调用任何 (静态 + 非静态) 变量。
 *
 * @author zlp
 * @date 2022/04/30
 */
public class ChildClass extends ParentClass {

    public int age;

    private String name;

    // 静态代码块
    static {
        System.out.println("ChildClass static");
    }

    // 构造代码块
    {
        System.out.println("ChildClass block");
    }

    // 构造函数
    public ChildClass() {
        System.out.println("ChildClass constructor");
    }

    //main 方法
    public static void main(String[] args) {

        System.out.println("ChildClass start main");
        try {
            Class classType = Class.forName("com.details.jk.ChildClass", false,
                    ClassLoader.getSystemClassLoader());
            classType.newInstance();
            Field[] fields = classType.getFields();
            for (Field field : fields) {
                System.out.print(field.getName()+",");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}

class ParentClass {

    static {
        System.out.println("ParentClass static");
    }

    {
        System.out.println("ParentClass block");
    }

    public ParentClass() {
        System.out.println("ParentClass constructor");
    }
}