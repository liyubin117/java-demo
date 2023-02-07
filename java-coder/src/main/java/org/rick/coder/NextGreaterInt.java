package org.rick.coder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Yubin Li
 * 栈
 * 比较一个数组num1每个元素在另一个数组num2往右的更大一个数，若没有则返回-1，num1是num2子数组
 */
public class NextGreaterInt {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getResult(new Integer[]{4, 2, 1}, new Integer[]{5, 8, 1, 3, 2, 0, 1, 2, 6})));
    }

    private static Integer[] getResult(Integer[] num1, Integer[] num2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i: num2) {
            while (!stack.isEmpty() && stack.peek() < i) {
                    map.putIfAbsent(stack.pop(), i);
            }
            stack.push(i);
        }
        Integer[] result = new Integer[num1.length];
        for (int i=0; i<num1.length; i++) {
            result[i] = map.getOrDefault(num1[i], -1);
        }
        return result;
    }
}
