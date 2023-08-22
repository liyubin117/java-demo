package org.rick.coder;

import static org.junit.Assert.assertEquals;

public class BinaryCalc {
    public static void main(String[] args) throws Exception {
        assertEquals(2, useIntegerToBinaryString(9));
        assertEquals(2, useBitCalc(9));
        System.out.println(binaryGap(22));
    }

    /**
     * 输入整数，输出二进制形式的值及1的个数
     */
    private static int useIntegerToBinaryString(int n) {
        char[] numChars = Integer.toBinaryString(n).toCharArray();
        int countNum = 0;
        for (char numChar : numChars) {
            if (numChar == '1') {
                countNum = countNum + 1;
            }
        }
        return countNum;
    }

    private static int useBitCalc(int n) {
        int result = 0;
        while (n != 0) {
            n = (n - 1) & n;
            result++;
        }
        return result;
    }

    /**
     * 868 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0
     * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3
     */
    private static int binaryGap(int n) {
        int result = 0, cur = Integer.MIN_VALUE;
        String str = Integer.toBinaryString(n);
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '0') cur++;
            else {
                result = Math.max(result, ++cur);
                cur = 0;
            }
        }
        return result;
    }
}
