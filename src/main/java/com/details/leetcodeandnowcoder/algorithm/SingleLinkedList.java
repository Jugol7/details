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
        SingleNode singleNode = reverse(node1);
        System.out.println();
        System.out.println("-------------------------------------------------");
        while(singleNode != null){
            System.out.print(singleNode.getVal());
            singleNode = singleNode.next;
        }


    }
    public static SingleNode reverseLinked(SingleNode singleNode){
        if(singleNode.next == null){
            return singleNode;
        }
        SingleNode singleNode1 = reverseLinked(singleNode.next);
        singleNode.next.setNext(singleNode);
        singleNode.setNext(null);
        return singleNode1;
    }

    //非递归实现
    public static SingleNode reverse(SingleNode current) {
        SingleNode previous = null;
        SingleNode next = null;
        while (current != null) {
            //存储下一节点
            next = current.next;
            //将当前节点的下一个只想pre
            current.next = previous;        //反转
            //更新遍历节点，pre指向当前节点，
            previous = current;
            //进行下一次
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