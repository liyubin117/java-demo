package org.rick;

import org.junit.Test;
import org.rick.structure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.rick.structure.TreeNode.traverseMidOrder;

public class TreeNodeTest {
    @Test
    public void test1() {
        TreeNode right = new TreeNode(2);
        TreeNode root = new TreeNode(1, null, right);
        List<Integer> result = new ArrayList<>();
        traverseMidOrder(root, result);
        System.out.println(Arrays.toString(result.toArray()));

        TreeNode left = new TreeNode(12);
        TreeNode root2 = new TreeNode(11, left, null);
        result.clear();
        traverseMidOrder(root2, result);
        System.out.println(Arrays.toString(result.toArray()));
    }


}
