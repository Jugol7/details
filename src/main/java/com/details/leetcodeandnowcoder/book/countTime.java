package com.details.leetcodeandnowcoder.book;

import java.util.Iterator;
import java.util.List;

/**
 * 数据结构与算法分析第三版java
 * 练习3.1
 * 给定一个表L和另一个表P，它们包含以升序排列的整数。操作printLots（L,p）将打印L中的那些由P所指定的元素。
 * 例如，如果P=1,3,4,6，那么，L中位于第1，第3，第4,和第6个位置上的元素被打印出来。写出过程printLots（L,p）。
 * 只可使用 public 型的Collections API容器操作。该过程的运行时间是多少？
 *
 * @author zlp
 * @date 2020/7/18 11:37
 */
public class countTime {

    public static void printLots(List<Integer> l, List<Integer> p) {

        //使用迭代器API
        Iterator<Integer> iteratorl = l.iterator();
        Iterator<Integer> iteratorp = p.iterator();

        //当拿到p中的元素之后，对应去找l中的索引
        int iteml = iteratorl.next();
        int itemp = iteratorp.next();
        int index = 0;
        while (iteratorl.hasNext() && iteratorp.hasNext()) {
            if (itemp == index++) {
                System.out.println(iteml);
                iteml = iteratorl.next();
            } else {
                itemp = iteratorp.next();
            }
        }
    }


}
