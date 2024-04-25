package org.rick.coder.test;

import org.junit.Test;

public class ArrayTest {
    @Test
    public void test31() {
        new Solution31().nextPermutation(new int[]{2, 3, 1});
    }

    //31 下一个排列 middle
    class Solution31 {
        //先从右往左找出第一个递增的元素，此为较小值，位置记为i，再从右往左找出第一个比它大的元素即较大值，位置记为j，调换位置，再将i之后的元素按字典序排序
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }
}
