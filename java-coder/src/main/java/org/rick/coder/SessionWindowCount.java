package org.rick.coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yubin Li
 * 用队列先进先出的特性计算会话窗口元素个数
 */
public class SessionWindowCount {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(determine(new int[]{1,10,3001,3002,4000,5000,6000}, 3000)));
    }

    private static Integer[] determine(int[] input, int session) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i: input) {
            while (queue.size() > 0 && i - queue.peek() > session) {
                queue.poll();
            }
            queue.add(i);
            result.add(queue.size());
        }
        return result.toArray(new Integer[0]);
    }
}
