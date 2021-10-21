package org.rick.basic;
import java.util.Scanner;

public class Switch {
	public static void main(String args[]){
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
