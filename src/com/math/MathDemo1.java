package com.math;

public class MathDemo1 {

	public static void main(String[] args) {
		double d=100;
		double d2=150;
		System.out.println(d+"的平方根："+Math.sqrt(d));
		System.out.println(d+" "+d2+"较大值："+Math.max(d, d2));
		
		System.out.println("3的2次方："+Math.pow(3, 2));
		System.out.println("四舍五入到整数："+Math.round(33.4));
		System.out.println("33.4 ceil："+Math.ceil(33.4));
		System.out.println("33.4 floor："+Math.floor(33.4));
	}

}
