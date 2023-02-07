package org.rick;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 栈，后进先出
 */
public class StackTest {
    @Test
    public void test1() {
        Stack<Integer> stack = new Stack<>();
        Arrays.asList(4,2,3,1).forEach(stack::push);
        System.out.println("top element: " + stack.peek());
        System.out.println("the second element: " + stack.get(1));
        System.out.println("the first element: " + stack.firstElement());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
