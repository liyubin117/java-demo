package org.rick.coder;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PlusOne {
    public static void main(String[] args) {
        assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
                plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        assertArrayEquals(new int[]{1, 0, 0, 0, 0},
                plusOne(new int[]{9, 9, 9, 9}));
    }

    private static int[] plusOne(int[] digits) {
        int carray = 1; //一开始是1,相当于是把进位与要加的1等价
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] = digits[i] + carray;
                carray = 0;
            } else {
                if (carray == 1) {
                    digits[i] = 0;
                }
            }
        }
        if (carray == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        return digits;
    }
}
