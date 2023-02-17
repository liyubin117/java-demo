package org.rick.coder;

import coder.ListNode;

public class LastKnode {
    public static void main(String[] args) {

    }

    //获取链表倒数第k个节点
    private static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (k-- > 0) {
            if (fast == null) return null;
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //19 删除链表的倒数第n个结点
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        while (n-- > 0) {
            if (fast == null) break;
            fast = fast.next;
        }
        while (fast != null) {
            if (fast.next == null) {
                slow.next = slow.next.next;
                return head;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return head.next;
    }
}
