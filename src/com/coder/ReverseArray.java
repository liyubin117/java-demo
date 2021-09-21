package com.coder;

import java.util.Arrays;

// 旋转数组
public class ReverseArray {
    public static void main(String[] args) {
        int[] input = {1,2,3,4,5,6};
        for (int i : solve(6, 2, input)) System.out.print(i + "\t");
//        Arrays.stream(solve(6, 2, input)).forEach(x -> System.out.print());
    }

    /**
     * @param n int整型 数组长度
     * @param m int整型 右移距离
     * @param a int整型一维数组 给定数组
     * @return int整型一维数组
     */
    public static int[] solve (int n, int m, int[] a) {
        int[] result = new int[n];
        for (int i = 0; i < a.length; i++) {
            if (i < m) {
                result[i] = a[n - m + i];
                continue;
            }
            result[i] = a[i - m];
        }
        return result;
    }
}
