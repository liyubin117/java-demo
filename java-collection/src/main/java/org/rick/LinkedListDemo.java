package org.rick;

import java.util.*;

public class LinkedListDemo {

	public static void main(String[] args) {
		//把LinkedList作为队列使用
		Queue<String> queue = new LinkedList<>();
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		while(queue.peek()!=null){
		    System.out.println(queue.poll());    
		}
		//当无元素时，element会抛出异常
		//queue.element();
		//当无元素时，peek不会抛出异常，只会返回null
		System.out.println(queue.peek());
		
		//把LinkedList作为栈使用
		Deque<String> stack = new LinkedList<>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		while(stack.peek()!=null){
		    System.out.println(stack.pop());    
		}
		//反向遍历
		LinkedList<String> ll = new LinkedList<String>(Arrays.asList("a","b","c"));
		Iterator<String> i=ll.descendingIterator();
		while(i.hasNext()){
			System.out.print(i.next());
		}
		System.out.println();
		
		//访问元素
		//根据索引
		System.out.println(ll.get(1));
		//根据内容查找索引
		System.out.println(ll.indexOf("c"));
		
		//插入元素
		ll.add("newString");
		ll.add(1, "ha");
		System.out.println(Arrays.deepToString(ll.toArray()));
		
		//删除元素
		ll.remove(1);
		System.out.println(Arrays.deepToString(ll.toArray()));

		
	}

}
