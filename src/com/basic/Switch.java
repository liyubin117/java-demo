package com.basic;
import java.util.Scanner;
public class Switch {
	public static void main(String args[]){
		/*“=”称为赋值运算符，用于对变量赋值。关于赋值运算符，除了将右边的表达式计算出来赋给左边以外还具备如下特点：赋值表达式本身也有值，其本身之值即为所赋之值。*/
		int num=10,index;
		System.out.println(num);
		System.out.println(num%3);
		/*字符串拼接*/
		int a=100;
		String  msg="a"+a;
		System.out.println(msg);
		msg=" "+100+200;  /*用 “”括起来后，从左向右计算，100变成了String型，其他参与运算的int型也隐式转换为String型，再进行拼接*/
		System.out.println(msg);
		msg=100+200+" ";  /*用 “”括起来后，从左向右计算，100变成了String型，其他参与运算的int型也隐式转换为String型，再进行拼接*/
		System.out.println(msg);		
		/*三目表达式*/
		int b=100,c=200;
		int flag=b>c?1:-1;
		System.out.println(flag);		
		/*条件三目运算符的嵌套*/
		int d=-3;
		String r=d>0?"正数":(d==0?"0":"负数");
		System.out.println(r);		

		//定义输入
		Scanner console = new Scanner(System.in);
//		System.out.println("请输入单价（￥）:");
//		double unitPrice = console.nextDouble();
//		System.out.println("请输入数量:");
//		double amount = console.nextDouble();
//		System.out.println("请输入金额（￥）:");
//		double money = console.nextDouble();
//		console.close();
//
//		//计算商品总价
//		double totalPrice = unitPrice * amount;
//		//计算找零
//		double change = money - totalPrice;
//
//		//输出
//		//当if语句块中只包含一条语句时，可以省略“{}”，但是if语句块也只能作用于它下面的一条语句。
//		if (change>0) {
//			System.out.println("应收金额为:" + totalPrice +"，找零为:" + change);
//		}else if(change==0){
//			System.out.println("应收金额为:" + totalPrice +"，不用找零");			
//		}else{
//			System.out.println("应收金额为:" + totalPrice +"，还需收:" + (-change));						
//		}
		
		// switch-case可以根据一个整数值的不同取值，从不同的程序入口开始执行。
		int m;
		System.out.println("请输入值：");
		m=console.nextInt();
		switch(m){
			case 1:
				System.out.println("输入的值为1");
				break;
			case 2:
				System.out.println("输入的值为2");
				System.out.println("输入的值为2");	
				break;
			case 3:
				System.out.println("输入的值为3");	
				break;
			default:
				System.out.println("输入的值是其他值");
		}
	}
}
