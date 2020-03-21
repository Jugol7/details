package com.details.leetcode;

/***
 * 对称二叉树
 * @author zlp
 * @date 17:43 2020/3/21
 */
public class SymmetricBinaryTree {

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return hasLeftAndRight(root);
    }

    //是否同时有左右节点
    public boolean hasLeftAndRight(TreeNode node){
        if(node == null){
            return true;
        }
        if(node.left == null && node.right == null){
            return true;
        }
        return (hasLeftAndRight(node.left) && hasLeftAndRight(node.right));
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
