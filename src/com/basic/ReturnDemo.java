package com.basic;
//说明函数将会在第一个成功执行的return处结束，并返回当前值
public class ReturnDemo {
	private static int count=0;
	public static int fun(int a){
		for(int i=0;i<10;i++){
			if(i==a){
				return count;
			}
			count++;
			if(i==a+1){
				return count;
			}
		}
		return count;
	}
	public static void main(String[] args){
		
		System.out.println(fun(5));
	}
}
