package com.details.leetcodeandnowcoder.tree;

import com.details.leetcodeandnowcoder.algorithm.TreeNode;


/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 */
public class FindLeftValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.setLeft(new TreeNode(1));
        root.setRight(new TreeNode(3));
        root.getRight().setLeft(new TreeNode(7));
        FindLeftValue findLeftValue = new FindLeftValue();
        int bottomLeftValue = findLeftValue.findBottomLeftValue(root);
        System.out.println(bottomLeftValue);
    }

    /**
     * 指定root不是null
     *
     * @param root
     * @return
     */
    private int max = -1;
    private int value = 0;

    public int findBottomLeftValue(TreeNode root) {
        get(root, 0);
        return value;
    }

    private void get(TreeNode node, int num) {
        if (node == null) {
            return;
        }
        if (num > max) {
            //num 与 max 比较 是为了找到最深的那层，如果不进行比较，那么会在回溯的时候，将本来的值有改变了
            max = num;
            value = node.getVal();
        }
        get(node.getLeft(), num + 1);
        get(node.getRight(), num + 1);

    }

}
