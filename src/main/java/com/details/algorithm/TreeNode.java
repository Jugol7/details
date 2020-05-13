package com.details.algorithm;

import lombok.Data;

/***
 * @author zlp
 * @date 18:37 2020/5/7
 */
@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
}
