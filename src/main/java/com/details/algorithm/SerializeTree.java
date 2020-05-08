package com.details.algorithm;

import io.swagger.models.auth.In;

import java.util.*;

/***
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * @author zlp
 * @date 18:35 2020/5/7
 */
public class SerializeTree {

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        a.add(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(null);
        Map<String,String> map = new HashMap<>();
        map.put(null,null);
        map.put(null,null);
        Set<String> set = new HashSet<>();
        set.add(null);

        SerializeTree serializeTree = new SerializeTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String serialize = serializeTree.serialize(root);
        System.out.println(serialize);
    }

    private ArrayList<Integer> arrayList = new ArrayList<>();

    /**
     * 序列化
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            arrayList.add(null);
        }else {
            arrayList.add(root.val);
            serialize(root.left);
            serialize(root.right);
        }
        return arrayList.toString();
    }

    /**
     * 反序列化
     *
     * @param data
     * @return
     */
//    public TreeNode deserialize(String data) {
//        TreeNode node;
//        return node;
//    }

}
