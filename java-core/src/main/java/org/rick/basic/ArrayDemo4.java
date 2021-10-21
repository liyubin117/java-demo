package org.rick.basic;

public class ArrayDemo4 {

	public static void main(String[] args) {
		Object[] array1=new Object[3];
		array1[1]=new Object[2];
		Object[] array2=(Object[])array1[1];
		array2[1]=new Object[3];
		Object[] array3=(Object[])array2[1];
		array3[1]=new int[5];
		
		for(int i=0;i<array3.length;i++){
			array3[i]=i*3+1;
		}
		
		
		
		System.out.println( ((Object[])((Object[])array1[1])[1])[2] );
		
	}

}
