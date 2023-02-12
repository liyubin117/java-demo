package org.rick.coder;

import java.util.HashSet;
import java.util.Set;

/**
 * 202 判断是否是快乐数
 * 快乐数 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 */
public class HappyNum {
    public static void main(String[] args) {
        System.out.println(isHappy(2));
    }

    //递归
    private static boolean isHappy(int n) {
        int sum = n;
        Set<Integer> sets = new HashSet<>();
        while (sum != 1) {
            if (!sets.add(sum)) return false; //注意要加入判断无限循环的情况
            sum = calc(sum);
        }
        return true;
    }

    private static int calc(int n) {
        if (n < 10) return n * n;
        return (n % 10) * (n % 10) + calc(n / 10);
    }
}
