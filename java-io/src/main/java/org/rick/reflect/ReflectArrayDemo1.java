package org.rick.reflect;

import java.lang.reflect.Array;

public class ReflectArrayDemo1 {

	public static void main(String[] args) {
		int[] arr={1,2,3};
		Class<?> c=arr.getClass().getComponentType();
		System.out.println("类型："+c.getName());
		System.out.println("长度："+Array.getLength(arr));
		System.out.println("第2个值是："+Array.get(arr,1));

	}

}
