package com.details.algorithm.tree;

import com.details.algorithm.TreeNode;

import java.util.*;

/**
 * @author zlp
 * @date 2020/5/13 23:09
 */
public class ShowTwoTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(6);
        treeNode.setLeft(new TreeNode(4));
        treeNode.setRight(new TreeNode(7));
        treeNode.getLeft().setLeft(new TreeNode(1));
        treeNode.getLeft().setRight(new TreeNode(2));
        treeNode.getRight().setLeft(new TreeNode(9));
        treeNode.getRight().setRight(new TreeNode(21));
        midSort(treeNode);
        System.out.println();
        midSortNoRecursive(treeNode);
        behindSortNoRecursive(treeNode);

    }
//  ----------------递归版本----------------------
    /**
     * 前序遍历根左右
     *
     * @param treeNode
     */
    public static void headSort(TreeNode treeNode) {

        if (treeNode != null) {
            System.out.print(treeNode.getVal() + "----");
            headSort(treeNode.getLeft());
            headSort(treeNode.getRight());
        }

    }

    /**
     * 中序遍历 左根右
     *
     * @param treeNode
     */
    public static void midSort(TreeNode treeNode) {

        if (treeNode != null) {
            midSort(treeNode.getLeft());
            System.out.print(treeNode.getVal() + "----");
            midSort(treeNode.getRight());
        }
    }

    /**
     * 后序遍历 左右根
     *
     * @param treeNode
     */
    public static void behindSort(TreeNode treeNode) {

        if (treeNode != null) {
            behindSort(treeNode.getLeft());
            behindSort(treeNode.getRight());
            System.out.print(treeNode.getVal() + "----");
        }
    }


//  ----------------非递归版本----------------------

    /**
     * 前序
     * @param treeNode
     */
    public static void headSortNoRecursive(TreeNode treeNode){
        Stack<TreeNode> stack = new Stack<>();
        while(treeNode != null || !stack.isEmpty()){
            if(treeNode != null){
                System.out.print(treeNode.getVal()+"----");
                stack.push(treeNode);
                treeNode = treeNode.getLeft();
            }else {
                TreeNode pop = stack.pop();
                treeNode = pop.getRight();
            }
        }
    }

    /**
     * 中序
     * @param treeNode
     */
    public static void midSortNoRecursive(TreeNode treeNode){
        Stack<TreeNode> stack = new Stack<>();
        while(treeNode != null || !stack.isEmpty()){
            if(treeNode != null){
                stack.push(treeNode);
                treeNode = treeNode.getLeft();
            }else {
                TreeNode pop = stack.pop();
                System.out.print(pop.getVal()+"----");
                treeNode = pop.getRight();
            }
        }
    }

    /**
     * 后序为 左右根
     * 前序为 根左右——> 根右左——>左右根
     * @param treeNode
     */
    public static void behindSortNoRecursive(TreeNode treeNode){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> linkedList = new LinkedList<>();
        while(treeNode != null || !stack.isEmpty()){
            if(treeNode != null){
                linkedList.add(treeNode.getVal());
                stack.push(treeNode);
                treeNode = treeNode.getRight();
            }else {
                TreeNode pop = stack.pop();
                treeNode = pop.getLeft();
            }
        }
        reveserList(linkedList);
    }

    public static List<Integer> reveserList(List<Integer> linkedList){
        Iterator<Integer> iterator = linkedList.iterator();
        LinkedList<Integer> l = new LinkedList<>();
        while (iterator.hasNext()) {
            l.addFirst(iterator.next());
        }
        System.out.println(l.size());
        Iterator<Integer> iterator1 = l.iterator();
        while(iterator1.hasNext()){
            System.out.print(iterator1.next()+"----");
        }
        return l;
    }
}