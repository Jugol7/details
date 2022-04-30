package com.details.jk;

/**
 * 一：强引用
 *
 * 只要强引用存在，垃圾回收器将永远不会回收被引用的对象，哪怕内存不足时，JVM 也会直接抛出 OutOfMemoryError，不会去回收。
 * 如果想中断强引用与对象之间的联系，可以显示的将强引用赋值为 null，这样一来，JVM 就可以适时的回收对象了。
 *
 * 二：软引用
 *
 * 软引用是用来描述一些非必需但仍有用的对象。在内存足够的时候，软引用对象不会被回收，只有在内存不足时，系统则会回收软引用对象，
 * 如果回收了软引用对象之后仍然没有足够的内存，才会抛出内存溢出异常。这种特性常常被用来实现缓存技术，比如网页缓存，图片缓存等。
 *
 * 在 JDK1.2 之后，用 java.lang.ref.SoftReference 类来表示软引用。
 *
 * 三：弱引用
 *
 * 弱引用的引用强度比软引用要更弱一些，无论内存是否足够，只要 JVM 开始进行垃圾回收，那些被弱引用关联的对象都会被回收。
 * 在 JDK1.2 之后，用 java.lang.ref.WeakReference 来表示弱引用。
 *
 * 四：虚引用
 *
 * 虚引用是最弱的一种引用关系，如果一个对象仅持有虚引用，那么它就和没有任何引用一样，它随时可能会被回收，
 * 在 JDK1.2 之后，用 PhantomReference 类来表示，通过查看这个类的源码，发现它只有一个构造函数和一个 get() 方法，
 * 而且它的 get() 方法仅仅是返回一个 null，也就是说将永远无法通过虚引用来获取对象，虚引用必须要和 ReferenceQueue 引用队列一起使用。
 *
 * @author zlp
 * @date 2022/04/30
 */
public class StringTest {

    /**
     * System.out.println(s1 == s2);//false，因为一个是堆内存中的 String 对象一个是常量池中的 String 对象
     * System.out.println(s3 == s2);//true，因为两个都是常量池中的 String 对象
     * String str3 = "str" + "ing";// 常量池中的对象
     * String str4 = str1 + str2; // 在堆上创建的新的对象
     * String str5 = "string";// 常量池中的对象
     * System.out.println(str3 == str4);//false
     * System.out.println(str3 == str5);//true
     * System.out.println(str4 == str5);//false
     * @param args
     */
    public static void main(String[] args) {
        String s1 = new String("程序员");
        String s2 = s1.intern();
        String s3 = "程序员";
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);

        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        String str5 = "string";
        System.out.println(str3 == str4);
        System.out.println(str3 == str5);
        System.out.println(str4 == str5);
        System.out.println(null instanceof Object);
        Integer i =0;
        System.out.println(i instanceof Object);
        double num = 2.4;
                System.out.println("Math.floor(" + num + ") = " + Math.floor(num));
        System.out.println("Math.ceil(" + num + ") = " + Math.ceil(num));
        System.out.println("Math.rint(" + num + ") = " + Math.rint(num));
        System.out.println("Math.round(" + num + ") = " + Math.round(num));
    }

    /**
     * s1 在创建对象的同时，在字符串池中也创建了其对象的引用。
     * 由于 s2 也是利用字面量创建，所以会先去字符串池中寻找是否有相等的字符串，
     * 显然 s1 已经帮他创建好了，它可以直接使用其引用。
     * 那么 s1 和 s2 所指向的都是同一个地址，
     * 所以 s1==s2。s3 是一个字符串拼接操作，参与拼接的部分都是字面量，
     * 编译器会进行优化，在编译时 s3 就变成“Hello”了，
     * 所以 s1==s3。s4 虽然也是拼接，
     * 但“lo”是通过 new 关键字创建的，在编译期无法知道它的地址，
     * 所以不能像 s3 一样优化。所以必须要等到运行时才能确定，
     * 必然新对象的地址和前面的不同，同理，s9 由两个变量拼接，
     * 编译期也不知道他们的具体位置，不会做出优化。
     * s5 是 new 出来的，在堆中的地址肯定和 s4 不同。
     * s6 利用 intern() 方法得到了 s5 在字符串池的引用，
     * 并不是 s5 本身的地址。
     * 由于它们在字符串池的引用都指向同一个“Hello”对象，自然 s1==s6。
     */
    public void test(){
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "Hel" + "lo";
        String s4 = "Hel" + new String("lo");
        String s5 = new String("Hello");
        String s6 = s5.intern();
        String s7 = "H";
        String s8 = "ello";
        String s9 = s7 + s8;

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s1 == s9);
        System.out.println(s4 == s5);
        System.out.println(s1 == s6);
    }
}
