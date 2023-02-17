package org.rick.coder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

/**
 * 输入整数，输出二进制形式的值及1的个数
 */
public class BinaryOneCount {
    public static void main(String[] args) throws Exception {
        assertEquals(2, useIntegerToBinaryString(9));
        assertEquals(2, useBitCalc(9));
    }

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
}
