package com.basic;

public class Symbol {

	public static void main(String[] args) {
		// 溢出
		Integer max = Integer.MAX_VALUE;
		Integer min = Integer.MIN_VALUE;
		System.out.println(max - min);
		System.out.println(min - max);
		System.out.println(max>min);
		System.out.println(max-min>0);
		
		//位运算
		System.out.println("9>>1："+(9>>1)+" 10<<1："+(10<<1));

		/*
		 * “=”称为赋值运算符，用于对变量赋值。关于赋值运算符，除了将右边的表达式计算出来赋给左边以外还具备如下特点：赋值表达式本身也有值，
		 * 其本身之值即为所赋之值。
		 */
		int num = 10, index;
		System.out.println(num);
		System.out.println(num % 3);
		/* 字符串拼接 */
		int a = 100;
		String msg = "a" + a;
		System.out.println(msg);
		msg = " " + 100 + 200; /*
								 * 用 “”括起来后，从左向右计算，100变成了String型，
								 * 其他参与运算的int型也隐式转换为String型，再进行拼接
								 */
		System.out.println(msg);
		msg = 100 + 200 + " "; /*
								 * 用 “”括起来后，从左向右计算，100变成了String型，
								 * 其他参与运算的int型也隐式转换为String型，再进行拼接
								 */
		System.out.println(msg);
		/* 三目表达式 */
		int b = 100, c = 200;
		int flag = b > c ? 1 : -1;
		System.out.println(flag);
		/* 条件三目运算符的嵌套 */
		int d = -3;
		String r = d > 0 ? "正数" : (d == 0 ? "0" : "负数");
		System.out.println(r);

	}

}
