package com.algorithm;

import org.junit.Test;

public class TestSort {
    @Test
    public void testBubbleSort() {
    	int[] array={10,8,20,15,21};
    	int[] arr=Sort.bubbleSort(array);
    	for (int i:arr)
    		System.out.print(i+" ");
    }

}
