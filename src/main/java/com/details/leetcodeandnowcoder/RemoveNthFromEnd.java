package com.details.leetcodeandnowcoder;

import lombok.extern.slf4j.Slf4j;

/**
 * 栓除给定链表的倒数第N元素
 * 思路，将head备份返回
 * 用两个节点去遍历一次，总是使得两节点相隔N个节点。
 *
 * @author zlp
 * @date 18:04  2019/11/27
 */
@Slf4j
public class RemoveNthFromEnd {


    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode firstBak = first;
        ListNode firstParam = first;
        for (int i = 2; i < 3; i++) {
            first.next = new ListNode(i);
            first = first.next;
        }
        while (firstBak != null) {
            log.debug("------------" + firstBak.val);
            firstBak = firstBak.next;
        }
        removeNthFromEnd(firstParam, 2);
        log.debug("------after remove------");
        while (firstParam != null) {
            log.debug("------------" + firstParam.val);
            firstParam = firstParam.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        boolean flag = true;
        ListNode node1 = head;
        ListNode node2 = head;
        int i = 0;
        while (flag) {
            //如果存在下一个节点，那就遍历
            if (null == node1.next) {
                if(node1 == head){
                    head = null;
                }
                if (node2.next != null) {
                    if (i+1 == n) {
                        node2 = node2.next;
                        head = node2;
                        return head;
                    }
                    node2.next = node2.next.next;
                }
                flag = false;
            }
            node1 = node1.next;
            //当第一个节点遍历了index次之后，将第二个节点开始遍历，保证两节点之间相隔index个值
            if (i >= n) {
                node2 = node2.next;
            }
            i++;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
