package com.details.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/***
 * 94 树的中序遍历
 * @author zlp
 * @date 16:02 2020/3/24
 */
@Slf4j
public class MidTree {

    private List<Integer> list = new ArrayList<>();

    public void main(String[] args) {
    }

    /**
     * 中序遍历  左根右
     *
     *              if (root.left != null) {
     *                 inorderTraversal(root.left);
     *             }
     *             list.add(root.val);
     *             if (root.right != null) {
     *                 inorderTraversal(root.right);
     *             }
     * @param root
     */
    private List<Integer> midTree(TreeNode root){
        log.info("进入方法，参数为："+root);
        if(root == null){
            log.error("参数为空！");
            return list;
        }
        if(root.left == null){
            list.add(root.val);
            if(root.right != null){
                midTree(root.right);
            }
        }else {
            midTree(root.left);
            //左边全部遍历完之后，将root的值放进去
            list.add(root.val);
        }
        return list;
    }


    public List<Integer> lastTree(TreeNode root){
        if(root == null){
            log.info("进入方法，参数为："+root);
            return list;
        }
        if(root.left != null) {
            lastTree(root.left);
        }
        if(root.right != null){
            lastTree(root.right);
        }
        list.add(root.val);
        return list;
    }


}
