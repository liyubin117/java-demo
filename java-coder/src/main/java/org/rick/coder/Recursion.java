package org.rick.coder;

import org.rick.structure.ListNode;
import org.rick.structure.TreeNode;

import static org.junit.Assert.assertEquals;

// 递归
public class Recursion {
    public static void main(String[] args) {
        assertEquals(8, fib(6));
        assertEquals(8, fibDp(6));

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(5);
        ListNode three = new ListNode(8);
        ListNode four = new ListNode(3);
        ListNode five = new ListNode(9);
        one.next = two; two.next = three;
        four.next = five;
        ListNode actual = mergeTwoLists(one, four);
        one.next = four; four.next = two; two.next = three; three.next = five;
        assertEquals(one, actual);

    }

    //509 斐波那契
    //递归 O(2^N) O(N)
    private static int fib(int n) {
        if (n < 2) return n == 1 ? 1 : 0;
        return fib(n - 1) + fib(n - 2);
    }
    //DP O(N) O(1)
    private static int fibDp(int n) {
        if (n < 3) {
            return n < 1 ? 0 : 1;
        }
        int pre = 1, cur = 1, result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre + cur;
            pre = cur;
            cur = result;
        }
        return result;
    }

    //21 合并两个有序链表
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //100 相同的树
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;
        if(p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
