package org.rick.algorithm;

import org.junit.Test;

public class Sort {
    @Test
    public void testBubbleSort() {
        int[] array={10,8,20,15,21};
        int[] arr=Sort.bubbleSort(array);
        for (int i:arr)
            System.out.print(i+" ");
    }

	/**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;
    }
}
