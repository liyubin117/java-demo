package com.coder;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yubin Li
 * @date 2021/9/21 16:09
 * 两数之和
 **/
public class TwoIntSum {
    @Test
    public void test1() {
        execute(new int[]{2,7,11,15}, 26);
    }

    //O(n)，空间换时间，使用哈希映射，键是缺的那个数，值是那个数的索引
    private void execute(int[] input, int demandSum) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i=0; i<input.length; i++) {
            if (hashMap.containsKey(demandSum - input[i])) {
                System.out.println(hashMap.get(demandSum - input[i]) + "\t" + i);
                return;
            } else {
                hashMap.put(input[i], i);
            }
        }
    }
}
