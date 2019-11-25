package com.details.leetcode;

import com.details.linkedlist.NoSingleLinkedList;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author zlp
 * @date 19:09  2019/11/20
 */
@Slf4j
public class AddTwoNumbers {

    private static final Logger logger = LoggerFactory.getLogger(AddTwoNumbers.class);

    public static void main(String[] args) {
        addTwoNumbers();
    }

    public static void addTwoNumbers() {
        NoSingleLinkedList<Integer> noSingleLinkedList1 = new NoSingleLinkedList<>();
        noSingleLinkedList1.addEleLast(2);
        noSingleLinkedList1.addEleLast(4);
        noSingleLinkedList1.addEleLast(3);
        NoSingleLinkedList<Integer> noSingleLinkedList2 = new NoSingleLinkedList<>();
        noSingleLinkedList2.addEleLast(8);
        noSingleLinkedList2.addEleLast(6);
        noSingleLinkedList2.addEleLast(4);
        NoSingleLinkedList<Integer> result = new NoSingleLinkedList<>();
        int[] arr = new int[noSingleLinkedList1.getSize()];
        for (int i = 0; i < noSingleLinkedList1.getSize(); i++) {
            String s = String.valueOf(Math.addExact(noSingleLinkedList1.getEle(i), noSingleLinkedList2.getEle(i)));
//            char charAt = s.charAt(s.length() - 1);
//            result.addEleFirst(Integer.valueOf(String.valueOf(charAt)));
            arr[i] = Integer.valueOf(s);
        }
        int[] ints = delLinked(arr);
        for (int i = 0; i < arr.length; i++) {
            result.addEleFirst(arr[i]);
        }
        System.out.println(result.toString());
    }

    /**
     * 首先的想法，但是自定义的双向链表并不能满足要求
     * 拿到第一个值，判断element为几位数
     * 通过这个拿下一个
     * 如果是多位数，那就改变前一个的element，递归调用一下，防止上一个加1之后又变成多位数
     * 目前只支持和为两位数。
     *
     * @param arr
     * @return
     */
    public static int[] delLinked(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            logger.debug("--------第"+(i+1)+"个---------------"+arr[i]);
            if (arr[i] > 9) {
                String s = String.valueOf(arr[i]);
                arr[i] = Integer.valueOf(s) % 10;
                if (i > 0) {
                    arr[i - 1] = arr[i - 1] + 1;
                    if (arr[i - 1] > 9) {
                        arr[i - 1] = arr[i - 1] + 1;
                        delLinked(arr);
                    }
                }
            }
        }
        return arr;
    }
/**
 * leetcode解答
 */
  /*  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }*/

}
