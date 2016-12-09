package com.useful;

import java.util.Arrays;

public class ArraysDemo1 {

	public static void main(String[] args) {
		int[] is={1,-1,9,10,8,5,200,100};
		Arrays.sort(is);
		System.out.println(Arrays.toString(is));
		
		//必须先排序，才能采用二分法查找
		int pos=Arrays.binarySearch(is, 5);
		System.out.println("5的位置："+pos);
		
		Arrays.fill(is, 3);
		System.out.println(Arrays.toString(is));
	}

}
