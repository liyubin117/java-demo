package org.rick.coder;

/**
 * 判断一个链表是否环形链表
 * 快慢指针
 */
public class CycleListDetermine {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = head;
        System.out.println(determine(head));
        third.next = null;
        System.out.println(determine(head));
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                builder.append(cur.val + ",");
                cur = cur.next;
            }
            return builder.toString();
        }
    }

    private static boolean determine(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
