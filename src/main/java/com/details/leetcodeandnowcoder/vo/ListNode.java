package com.details.leetcodeandnowcoder.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zlp
 * @Date 2020/5/28 18:44
 **/
@Setter
@Getter
public class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
