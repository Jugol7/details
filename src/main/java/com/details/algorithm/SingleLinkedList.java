package com.details.algorithm;

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
    public static SingleNode reverseLinked(SingleNode singleNode){
        if(singleNode.next == null){
            return singleNode;
        }
        SingleNode singleNode1 = reverseLinked(singleNode.next);
        singleNode.next.setNext(singleNode);
        singleNode.setNext(null);
        return singleNode1;
    }
}

@Data
class SingleNode{
    int val;
    SingleNode next;
}