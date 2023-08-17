package org.rick.structure;

import java.util.List;

/**
 * 二叉树
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
        left=null;
        right=null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 中序遍历
     */
    public static void traverseMidOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            list.add(null);
            return;
        }
        traverseMidOrder(root.left, list);
        list.add(root.val);
        traverseMidOrder(root.right, list);
    }
}
