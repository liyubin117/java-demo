package org.rick.coder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//连续子串和
public class SubSeqMaxSum {
    public static void main(String[] args) throws IOException {
        /**
         * 动态规划
         * 状态转移方程：dp[i] = max(dp[i-1] + nums[i], nums[i])，其中dp[i]表示以索引i为结束点基准的子数组的最大值。
         * 初始状态：dp[0]= nums[0]
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = {-1, 2, 3, -2, 6, -3};
        int currMaxSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currMaxSum = Math.max(currMaxSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currMaxSum);
        }
        System.out.println(maxSum);
    }
}
