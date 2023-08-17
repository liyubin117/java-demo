package org.rick;

import org.junit.Test;
import org.rick.structure.MyLinkedList;
import org.rick.structure.MyList;

public class MyListTest {
    @Test
    public void testLink() {
        MyList<String> list = new MyLinkedList<>();
        // 添加元素
        list.add("a");
        list.addFirst("b");
        list.addLast("c");
        // 打印列表
        list.printLinkList();
        // 头插元素
        list.addFirst("d");
        // 删除元素
        list.remove("b");
        // 打印列表
        list.printLinkList();
    }
}
