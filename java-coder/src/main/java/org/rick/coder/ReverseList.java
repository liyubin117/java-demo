package org.rick.coder;

import org.rick.coder.CycleListDetermine.ListNode;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

//206 反转链表
public class ReverseList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = null;
        System.out.println(reverseList1(head));
        System.out.println(reverseList2(head));
        System.out.println(reverseList3(head));

    }

    //栈 O(N) O(N)
    private static ListNode reverseList1(ListNode head) {
        if (head == null) return head;
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        ListNode result = new ListNode(stack.pop());
        ListNode cur = result;
        while (!stack.isEmpty()) {
            cur.next = new ListNode(stack.pop());
            cur = cur.next;
        }
        return result;
    }

    //链表 O(N) O(1)
    private static ListNode reverseList2(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    private static ListNode reverseList3(ListNode head) {
        ListNode cur = head;
        if (cur == null || cur.next == null) return cur;
        ListNode newNode = reverseList3(cur.next);
        cur.next.next = cur;
        cur.next = null;
        return newNode;
    }
}
