package org.rick.structure;

import coder.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉树
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(2);
        TreeNode root = new TreeNode(1, null, right);
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        System.out.println(Arrays.toString(result.toArray()));

        TreeNode left = new TreeNode(12);
        TreeNode root2 = new TreeNode(11, left, null);
        result.clear();
        traverse(root2, result);
        System.out.println(Arrays.toString(result.toArray()));
    }

    //中序遍历
    private static void traverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            list.add(null);
            return;
        }
        traverse(root.left, list);
        list.add(root.val);
        traverse(root.right, list);
    }

}
