package com.details.leetcodeandnowcoder;

/***
 * 平衡二叉树
 * 定义：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 高度差即为深度差
 * @author zlp
 * @date 17:44 2020/3/21
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {

    }

    public int getHight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        //一层层往下递归，知道没有，那么此节点的最深就出来
        return 1 + Math.max(getHight(node.left),getHight(node.right));
    }

    public boolean isBalance(TreeNode root){
        if(root == null){
            return true;
        }
        //每个节点都是要，节点的节点也是要的
        return Math.abs(getHight(root.left) - getHight(root.right)) < 2 && isBalance(root.left) && isBalance(root.right);
    }

}
