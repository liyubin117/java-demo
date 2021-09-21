package com.coder;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Yubin Li
 * @date 2021/9/21 16:24
 * 三数之和。n个元素的数组里存在3个元素，使合等于指定值，找出所有符合要求且不重复的三元组
 **/
public class ThreeIntSum {
    @Test
    public void test() {
        execute(new int[]{-1,0,1,2,-1,-4}, 2);
    }

    //O(n^2)，先对原数组排序，然后双指针法（一层循环固定一个元素，循环内部利用双指针找出剩下的两个元素），注意去重
    private void execute(int[] input, int demandResult) {
        int[] sortedInput = Arrays.stream(input).sorted().toArray();
        int n = sortedInput.length;
        for (int i=0; i<n-2; i++) {
            if (i>0 && sortedInput[i] == sortedInput[i-1]) continue;  //如果此次固定的元素与上一次相同，则不用迭代了，因为就算迭代找到的结果也一样
            int l = i+1;
            int r = n-1;
            while (l<r) {
                int mid = sortedInput[i] + sortedInput[l] + sortedInput[r];
                if (mid < demandResult) {
                    l++;
                } else if (mid > demandResult) {
                    r--;
                } else {
                    System.out.printf("%d\t%d\t%d\n", sortedInput[i], sortedInput[l], sortedInput[r]);
                    while (l<r && sortedInput[l]==sortedInput[l+1]) l++; //去重
                    while (l<r && sortedInput[r]==sortedInput[r-1]) r--; //去重
                    l++;
                    r--;
                }
            }

        }
    }
}
