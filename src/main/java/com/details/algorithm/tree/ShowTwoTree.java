package com.details.algorithm.tree;

import com.details.algorithm.TreeNode;

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
        headSort(treeNode);


    }

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




}