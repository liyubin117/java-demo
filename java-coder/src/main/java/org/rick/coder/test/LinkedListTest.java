package org.rick.coder.test;

import org.junit.Test;
import org.rick.structure.ListNode;

public class LinkedListTest {

    @Test
    public void test25() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        System.out.println(new Solution25().reverseKGroup(one, 2));
    }

    class Solution25 {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                //剩余数量小于k的话，则不需要反转。
                if (tail == null) {
                    return head;
                }
                tail = tail.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverse(head, tail);
            //下一轮的开始的地方就是tail
            head.next = reverseKGroup(tail, k);

            return newHead;
        }

        // 翻转链表
        private ListNode reverse(ListNode head, ListNode tail) {
            ListNode pre = null, cur = head, tmp;
            while (cur != tail) {
                tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            return pre;
        }
    }
}
