package org.rick.structure;

public class LeetcodeMyLinkedList {
    private static final ListNode DUMMY_HEAD = new ListNode(-100);
    public LeetcodeMyLinkedList(ListNode head) {
    }

    public int get(int index) {
        ListNode cur = DUMMY_HEAD;
        while (index-- >= 0 && cur != null) {
            cur = cur.next;
        }
        return cur == null ? -1 : cur.val;
    }

    public void addAtHead(int val) {
        ListNode addedNode = new ListNode(val);
        addedNode.next = DUMMY_HEAD.next;
        DUMMY_HEAD.next = addedNode;
    }

    public void addAtTail(int val) {
        ListNode cur = DUMMY_HEAD;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(val);
    }

    public void addAtIndex(int index, int val) {
        ListNode cur = DUMMY_HEAD;
        while (--index >= 0) {
            cur = cur.next;
        }
        ListNode addedNode = new ListNode(val);
        addedNode.next = cur.next;
        cur.next = addedNode;
    }

    public void deleteAtIndex(int index) {
        ListNode cur = DUMMY_HEAD;
        while (--index >= 0) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
    }
}
