package com.details.leetcodeandnowcoder.algorithm;

import lombok.Data;


/***
 * 单链反转
 * @author zlp
 * @date 16:57 2020/3/26
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        SingleNode node1 = new SingleNode();
        SingleNode node2 = new SingleNode();
        SingleNode node3 = new SingleNode();
        SingleNode node4 = new SingleNode();
        node1.setVal(1);
        node2.setVal(2);
        node3.setVal(3);
        node4.setVal(4);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        SingleNode bak = node1;
        while(bak != null){
            System.out.print(bak.getVal());
            bak = bak.next;
        }
        SingleNode singleNode = reverseLinked(node1);
        System.out.println();
        System.out.println("-------------------------------------------------");
        while(singleNode != null){
            System.out.print(singleNode.getVal());
            singleNode = singleNode.next;
        }


    }

    /**
     * 递归实现
     * @param singleNode
     * @return
     */
    public static SingleNode reverseLinked(SingleNode singleNode){
        //是否到达最后一个节点
        if(singleNode.next == null){
            return singleNode;
        }
        //调用递归
        SingleNode singleNode1 = reverseLinked(singleNode.next);
        //此时的singleNode是后一个节点，将后一个节点的next指向当前节点，完成反转
        singleNode.next.setNext(singleNode);
        //将当前节点的next置为null
        singleNode.setNext(null);
        return singleNode1;
    }

    /**
     * 非递归实现
     * @param current
     * @return
     */
    public static SingleNode reverse(SingleNode current) {
        //用于反转指向
        SingleNode previous = null;
        //用于下次循环
        SingleNode next = null;
        while (current != null) {
            //存储下一节点
            next = current.next;
            //将当前节点的下一个指向pre
            current.next = previous;        //反转
            //pre指向当前节点，
            previous = current;
            //更新遍历节点，进行下一次
            current = next;
        }
        return previous;
    }
}

@Data
class SingleNode{
    int val;
    SingleNode next;
}