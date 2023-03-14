package com.details.leetcodeandnowcoder.linkedlist;

import com.details.leetcodeandnowcoder.vo.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 有 100个数字排成一排，现在从第 1个数字开始数数，数到第7个数字时，将其取出，
 * 然后从下一个数字开始继续数数，数到第7个时再次取出，
 * 依次类推，当数到最后一个时回到最开始，接着继续数，直到这 100 个数字全部拿出来为止，
 * 最后要求按取出的失后顺序打印取出的数字，请编写算法实现
 * <p>
 * 类似 com.details.leetcodeandnowcoder.linkedlist.JosephRing
 *
 * @author ：zlp
 * @date ：2023/3/14 22:57
 * @version: 1.0
 */
public class FindSeven {

    public static void main(String[] args) {

    }


    boolean A(ListNode head){
        List<Integer> list = new ArrayList<>();
        list.add(head.getVal());
        while(head.getNext() != null){
            if(list.contains(head.getNext().getVal())){
                return true;
            }
            list.add(head.getVal());
            head = head.getNext();
        }
        return false;
    }

    /**
     * 超出时间限制
     */
    void solutionA() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        int flag = 0;
        int num;
        while (list.size() > 1) {
            ListIterator<Integer> it = list.listIterator();
            while (it.hasNext()) {
                num = it.next();
                flag++;
                if (flag < 7 && num == list.get(list.size() - 1)) {
                    flag = 0;
                    break;
                }
                if (flag == 7) {
                    it.remove();
                    System.out.println(num);
                    flag = 0;
                }
            }
        }
    }
}
