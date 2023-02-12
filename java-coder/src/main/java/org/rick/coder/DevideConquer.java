package org.rick.coder;

import static org.junit.Assert.assertEquals;

// 分治法
public class DevideConquer {
    public static void main(String[] args) {
        assertEquals(23, MaxSubArray.maxSubArray(new int[]{5,4,-1,7,8}));
    }

    /**
     * 53 最大连续子数和
     * 给一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     */
    static class MaxSubArray {
        static int maxSubArray(int[] nums) {
            return getMax(nums, 0, nums.length - 1);
        }
        private static int getMax(int[] nums, int left, int right) {
            if (left == right) return nums[left];
            int mid = (left + right) / 2;
            int leftMax = getMax(nums, left, mid);
            int rightMax = getMax(nums, mid+1, right);
            int crossMax = getCrossMax(nums, left, right);
            return Math.max(Math.max(leftMax, rightMax), crossMax);
        }
        private static int getCrossMax(int[] nums, int left, int right) {
            int mid = (left + right) / 2;
            int leftMax = nums[mid], leftSum = 0;
            for (int i = mid; i >= left; i--) {
                leftSum += nums[i];
                leftMax = Math.max(leftMax, leftSum);
            }
            int rightMax = nums[mid + 1], rightSum = 0;
            for (int i = mid + 1; i <= right ; i++) {
                rightSum += nums[i];
                rightMax = Math.max(rightMax, rightSum);
            }
            return leftMax + rightMax;
        }
    }

}
