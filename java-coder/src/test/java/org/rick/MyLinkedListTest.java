package org.rick;

import org.junit.Before;
import org.junit.Test;
import org.rick.structure.ListNode;
import org.rick.structure.MyLinkedList;

import static org.junit.Assert.assertEquals;

public class MyLinkedListTest {
    private MyLinkedList list;

    @Before
    public void init() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        head.next = second;
        second.next = third;
        list = new MyLinkedList(head);
    }

    @Test
    public void testGet() {
        assertEquals(list.get(1), 2);
        assertEquals(list.get(2), 3);
        assertEquals(list.get(3), -1);
    }

    @Test
    public void testAddAtHead() {
        list.addAtHead(100);
        assertEquals(list.get(0), 100);
        assertEquals(list.get(1), 1);
    }

    @Test
    public void testAddAtTail() {
        list.addAtTail(1000);
        assertEquals(list.get(3), 1000);
    }

    @Test
    public void testAddAtIndex() {
        list.addAtIndex(0, 12);
        assertEquals(list.get(0), 12);
        assertEquals(list.get(1), 1);
        list.addAtIndex(2, 23);
        assertEquals(list.get(2), 23);
        assertEquals(list.get(3), 2);
    }

    @Test
    public void testDeleteAtIndex() {
        list.deleteAtIndex(0);
        assertEquals(list.get(0), 2);
        list.deleteAtIndex(1);
        assertEquals(list.get(0), 2);
    }
}
