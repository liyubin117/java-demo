package org.rick.coder;

import static org.junit.Assert.assertEquals;

/**
 * 169 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素
 */
public class MajorityElement {
    public static void main(String[] args) {
        assertEquals(1, getMajorityElement(new int[]{1,1,2,2,1}));
    }

    //摩尔投票
    private static int getMajorityElement(int[] nums) {
        int count = 0, result = 0;
        for (int i:nums) {
            if (count == 0) result = i;
            count += result == i ? 1 : -1;
        }
        return result;
    }
}
