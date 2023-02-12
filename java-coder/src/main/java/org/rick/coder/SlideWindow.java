package org.rick.coder;

import static org.junit.Assert.assertEquals;

public class SlideWindow {
    public static void main(String[] args) {
        assertEquals(2, minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }

    //209 长度最小的子数组，若没有则返回0
    private static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int result = Integer.MAX_VALUE, i = 0, j = 0, total = 0;
        while (j < nums.length) {
            total = total + nums[j];
            j++;
            while (total >= target) {
                result = Math.min(result, j - i);
                total = total - nums[i];
                i++;
            }
        }
        if (result == Integer.MAX_VALUE) return 0;
        return result;
    }
}
