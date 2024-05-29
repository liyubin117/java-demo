package org.rick.coder.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DpTest {
    @Test
    public void testZeroOnePackage() {
        ZeroOnePackage.execute();
    }

    /**
     * 题目描述
     * 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的空间，并且具有不同的价值。
     * 小明的行李空间为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料只能选择一次，并且只有选与不选两种选择，不能进行切割。
     *
     * 输入描述
     * 第一行包含两个正整数，第一个整数 M 代表研究材料的种类，第二个正整数 N，代表小明的行李空间。
     * 第二行包含 M 个正整数，代表每种研究材料的所占空间。
     * 第三行包含 M 个正整数，代表每种研究材料的价值。
     *
     * 输出描述
     * 输出一个整数，代表小明能够携带的研究材料的最大价值。
     */
    static class ZeroOnePackage {

        public static void execute() {
            // 背包容量 N
            // 物品种类 M
            Scanner sc = new Scanner(System.in);

            int M = sc.nextInt();
            int N = sc.nextInt();

            int[] values = new int[M];
            int[] weights = new int[M];

            for(int i = 0; i < M;i++) {
                weights[i] = sc.nextInt();
            }


            for(int i = 0; i < M;i++) {
                values[i] = sc.nextInt();
            }

            int[][] dp = new int[M][N+1];

            // 初始化
            for(int j = weights[0]; j <= N; j++) {
                dp[0][j] = values[0];
            }

            // 先物品
            for(int i = 1; i < M; i++) {
                // 后背包
                for(int j = 0; j <= N; j++) {
                    if(weights[i] > j) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weights[i]] + values[i]);
                    }
                }
            }
            System.out.println(dp[M-1][N]);
        }
    }

    @Test
    public void test139() {
        new Solution139().wordBreak("leetcode", Arrays.asList("leet", "code"));
    }

    class Solution139 {
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int j = 1; j <= s.length(); j++) {
                for (int i = 0; i < j; i++) {
//                    String str = s.substring(i, j);
//                    dp[j] = dp[i] && wordDict.contains(str);
                    if (dp[i] && wordDict.contains(s.substring(i, j))) {
                        dp[j] = true;
                    }
                }
            }
            return dp[s.length()];
        }
    }
}
