package com.collectionmap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ArrayDequeDemo {

	public static void main(String[] args) {
		//ArrayDeque实现了Deque接口，可作为队列、栈、双端队列使用
		//作为队列使用
		Queue<String> queue = new ArrayDeque<>();

		queue.offer("a");
		queue.offer("b");
		queue.offer("c");

		while(queue.peek()!=null){
		    System.out.print(queue.poll() +" ");    
		}
		System.out.println();
		
		//作为栈使用
		Deque<String> stack = new ArrayDeque<>();

		stack.push("a");
		stack.push("b");
		stack.push("c");

		while(stack.peek()!=null){
		    System.out.print(stack.pop()+" ");    
		}
		
		//作为双端队列使用
		Deque<String> deque = new ArrayDeque<>();

		deque.addFirst("a");
		deque.offerLast("b");
		deque.addLast("c");
		deque.addFirst("d");

		System.out.println(deque.getFirst());
		System.out.println(deque.peekLast());
		System.out.println(deque.removeFirst());
		System.out.println(deque.pollLast()); 
	}

}
