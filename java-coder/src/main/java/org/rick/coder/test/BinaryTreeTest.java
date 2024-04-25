package org.rick.coder.test;

import org.junit.Test;
import org.rick.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BinaryTreeTest {

    @Test
    public void testBinaryTreePaths() {
        TreeNode left = new TreeNode(2, null, new TreeNode(5));
        TreeNode root = new TreeNode(1, left, new TreeNode(3));
        System.out.println(binaryTreePaths(root));
    }

    // 257 二叉树的所有路径 easy
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        recurse(root, new ArrayList<String>(), result);
        return result;
    }
    private void recurse(TreeNode node, List<String> path, List<String> result) {
        path.add(String.valueOf(node.val)); //中序遍历
        if (node.left == null && node.right == null) result.add(String.join("->", path)); //遇到叶节点
        if (node.left != null) {
            recurse(node.left, path, result);
            path.remove(path.size() - 1); //回溯
        }
        if (node.right != null) {
            recurse(node.right, path, result);
            path.remove(path.size() - 1); //回溯
        }
    }

    @Test
    public void testHasPathSum() {
        TreeNode a = new TreeNode(7);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(11, a, b);
        TreeNode e = new TreeNode(4, null, c);
        TreeNode f = new TreeNode(4, d, null);
        TreeNode g = new TreeNode(13);
        TreeNode h = new TreeNode(8, g, e);
        TreeNode root = new TreeNode(5, f, h);
        assertTrue(hasPathSum(root, 22));
    }

    // 112 路径总和 easy
    private int sum = 0;
    private boolean flag = false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return recurse(root, targetSum);
    }
    private boolean recurse(TreeNode node, int targetSum) {
        sum += node.val;
        if (node.left == null && node.right == null && sum == targetSum) flag = true;
        if (node.left != null) {
            hasPathSum(node.left, targetSum);
            sum -= node.left.val;
        }
        if (node.right != null) {
            hasPathSum(node.right, targetSum);
            sum -= node.right.val;
        }
        return flag;
    }
}
