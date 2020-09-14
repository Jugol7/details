package com.details.keyword;

/**
 * 文档：final关键字.md
 * 链接：http://note.youdao.com/noteshare?id=244fc02143fee95801b198be26275149&sub=6B305A3F9160448BAD0C50E583E7322D
 * @author zlp
 * @date 2020/8/19 20:48
 */
public class TestFinal {

    //final修饰必须初始化，要么在构造函数中初始化。
    private final int num;

    private final String string;

    private final TestStatic testStatic = new TestStatic();

    public TestFinal(){
        num = 1;
        string = "1";
        testStatic.setS("1");
        System.out.println(testStatic.getS());
    }

    public void method(){
        //引用类型的指向不能改变，但是引用对象中的属性可以。
        testStatic.setS("2");
        System.out.println(testStatic.getS());
    }

    public static void main(String[] args){
//        TestFinal testFinal = new TestFinal();
//        testFinal.method();

        //static 变量时类加载时就初始阿虎，至此一份，而final不是，不同的对象当然不一样
        TestStatic demo1 = new TestStatic();
        TestStatic demo2 = new TestStatic();
        System.out.println("final修饰的  i=" + demo1.i);
        System.out.println("static修饰的 t=" + demo1.t);
        System.out.println("final修饰的  i=" + demo2.i);
        System.out.println("static修饰的 t=" + demo2.t);

        System.out.println("t+1= "+ ++demo2.t );


    }

}
