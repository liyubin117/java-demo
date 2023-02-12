package org.rick.coder;

import java.util.Arrays;

/**
 * 有序数组，两数之和等于目标数，返回两值索引
 * 用双指针，O(N)
 */
public class SortedArrayTowIntSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(get(new int[]{1,4,5,7,9}, 12)));
        System.out.println(Arrays.toString(get(new int[]{1,3,5,6,9}, 12)));
    }

    private static int[] get(int[] numbers, int target) {
        int i=0,j=numbers.length-1;
        while (j > i) {
            int left = numbers[i];
            int right = numbers[j];
            int mid = left + right;
            if (mid == target) return new int[] {i, j};
            if (mid < target) i++;
            else j--;
        }
        return null;
    }
}
